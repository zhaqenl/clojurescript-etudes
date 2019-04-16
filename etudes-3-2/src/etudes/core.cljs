(ns etudes.core
  (:require [clojure.browser.repl :as repl]
            [goog.dom :as dom]
            [goog.events :as events]))

(defn radians [degrees]
  "Convert given degrees to radians"
  (* (/  js/Math.PI 180) degrees))

(defn daylight [latitude-degrees julian-day]
  "Compute amount of daylight, in minutes, of a location given its latitude in degrees and the
  julian day"
  (let [latitude (radians latitude-degrees)
        P (->> (- julian-day 186)
               (* 0.0086)
               js/Math.tan
               (* 0.9671396)
               js/Math.atan
               (* 2)
               (+ 0.2163108)
               js/Math.cos
               (* 0.39795)
               js/Math.asin)
        D (->> (/ (->> (js/Math.sin P)
                       (* (js/Math.sin latitude))
                       (+ (js/Math.sin 0.01454)))
                  (->> (js/Math.cos P)
                       (* (js/Math.cos latitude))))
               js/Math.acos
               (* 7.63944)
               (- 24))]
    (* 60 D)))

(defn leap-year?
  "Return true if given year is a leap year; false otherwise"
  [year]
  (or (and (= 0 (rem year 4)) (not= 0 (rem year 100)))
      (= 0 (rem year 400))))

(def with-31
  "Collection of month ordinals which contain 31 days"
  #{1 3 5 7 8 10 12})

(defn previous-month-days
  "Compute total number of days of previous months given present month and year"
  [month year]
  (let [max (fn [month]
              (cond
                (and (= 2 month) (leap-year? year)) 29
                (= 2 month) 28
                (contains? with-31 month) 31
                true 30))]
    (apply + (map max (remove zero? (range month))))))

(defn ordinal-day
  "Compute ordinal day of given day, month, and year; return 0 if provided date is invalid"
  [day month year]
  (let [parsedMonth ((fn [month]
                       (if (and (> month 0) (< month 13)) month))
                     month)
        parsedDay ((fn [day]
                     (cond
                       (and (= 2 parsedMonth) (leap-year? year) (> day 0) (< day 30)) day
                       (and (= 2 parsedMonth) (> day 0) (< day 29)) day
                       (and (contains? with-31 parsedMonth) (> day 0) (< day 32)) day
                       (and (not= 2 parsedMonth) (> day 0) (< day 31)) day))
                   day)]
    (if (and parsedMonth parsedDay)
      (+ parsedDay (previous-month-days parsedMonth year))
      0)))

(defn compute [event]
  "Set innerHTML of element with 'result' id to output of applying daylight function"
  (let [latitude (js/parseFloat (.-value (dom/getElement "latitude")))
        [year month day] (map js/parseFloat (.split (.-value (dom/getElement "gregorian")) "-"))
        parsed-date (ordinal-day day month year)
        daylight-amount (daylight latitude parsed-date)]
    (cond
      (zero? (ordinal-day day month year)) (dom/setTextContent (dom/getElement "result") "Invalid date")
      true (dom/setTextContent (dom/getElement "result") (str (quot daylight-amount 60) " hours "
                                                              (js/parseInt (rem daylight-amount 60)) " minutes")))))

(events/listen (dom/getElement "calculate") "click" compute)
