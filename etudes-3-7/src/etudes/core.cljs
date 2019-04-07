(ns etudes.core
  (:require [clojure.browser.repl :as repl]
            [goog.dom :as dom]
            [goog.events :as events]))

(enable-console-print!)

(println "This text is printed from src/etudes/core.cljs. Go ahead and
edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

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

(defn time-pretty-print
  "Print minute argument to string with hours and minutes"
  [minutes]
  (str (quot minutes 60) " hours " (js/parseInt (rem minutes 60)) " minute(s)"))

(defn get-mean
  "Return mean of input sequence"
  [sequence]
  (let [n (count sequence)
        summation (apply + sequence)]
        (/ summation n)))

(defn get-latitude
  "Return latitude value based on selected radio button"
  []
  (if (.-checked (dom/getElement "userSpecified"))
    (js/parseFloat (.-value (dom/getElement "latitude")))
    (js/parseFloat (.-value (dom/getElement "cityMenu")))))

(def id-month-map 
  "Map which correspond to id's and their numbers, respectively"
  {"m1" 1,
   "m2" 2,
   "m3" 3,
   "m4" 4,
   "m5" 5,
   "m6" 6,
   "m7" 7,
   "m8" 8,
   "m9" 9,
   "m10" 10,
   "m11" 11,
   "m12" 12})

(def month-ids
  "Contains month ids from document having 'td' tag"
  (map key id-month-map))

(defn max-days
  "Get max amount of days of a given month"
  [month]
  (cond
    (= 2 month) 28
    (contains? (set [1 3 5 7 8 10 12]) month) 31
    true 30))

(defn previous-month-days
  "Compute total number of days of previous months given present month"
  [month]
  (apply + (map max-days (remove zero? (range month)))))

(defn ordinal-day
  "Compute ordinal day of given day and month; return 0 if provided date is invalid"
  [month day]
  (+ day (previous-month-days month)))

(defn daylight-month
  "Compute average amount of daylight (in minutes) in a month"
  [month]
  (let [days (->> (max-days month)
                  inc
                  range
                  rest)
        partial-ordinal (partial ordinal-day month)
        partial-daylight (partial daylight (get-latitude))
        ordinal-map (map partial-ordinal days)        
        daylight-month-map (map partial-daylight ordinal-map)]
    (get-mean daylight-month-map)))

(defn compute [event]
  "Set innerHTML of element with 'result' id to output of applying daylight-month function"
  (doseq [id month-ids]
    (let [daylight-minutes (daylight-month (get id-month-map id))]
      (dom/setTextContent (dom/getElement id) (time-pretty-print daylight-minutes)))))

(events/listen (dom/getElement "calculate") "click" compute)
