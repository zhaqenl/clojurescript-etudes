(ns etudes.core
  (:require [clojure.browser.repl :as repl]
            [goog.dom :as dom]
            [goog.events :as events]))

(enable-console-print!)

(println "This text is printed from src/etudes/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn parseIntVector [string]
  "Return list that only contains numbers."
  (map js/parseFloat (.split string " ")))

(defn parsed
  "Get value of input as sequence."
  []
  (parseIntVector (.trim (.-value (dom/getElement "number-sequence")))))

(defn get-mean
  "Return mean of input sequence"
  [sequence]
  (let [n (count sequence)
        summation (apply + sequence)]
    (/ summation n)))

(defn get-median
  "Return median of input sequence"
  [sequence]
  (let [n (count sequence)
        idx (quot (- n 1) 2)]
    (if (odd? n)
      (nth sequence idx)
      (get-mean [(nth sequence (/ n 2)) (nth sequence idx)]))))

(defn get-stdev
  "Return standard deviation of input sequence"
  [sequence]
  (let [n (count sequence)
        step-1 (->> (map (fn [n] (* n n)) sequence)
                    (apply +))
        step-2 (js/Math.pow (apply + sequence) 2)
        step-3 (/ step-2 n)
        step-4 (- step-1 step-3)
        step-5 (/ step-4 (- n 1))
        step-6 (js/Math.sqrt step-5)]
    step-6))

(defn output [event]
  (dom/setTextContent (dom/getElement "mean") (get-mean (parsed)))
  (dom/setTextContent (dom/getElement "median") (get-median (parsed)))
  (dom/setTextContent (dom/getElement "stdev") (get-stdev (parsed))))

(events/listen (dom/getElement "number-sequence") "input" output)
