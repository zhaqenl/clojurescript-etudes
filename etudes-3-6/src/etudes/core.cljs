(ns etudes.core
    (:require ))

(enable-console-print!)

(println "This text is printed from src/etudes/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn good-tooth
  "Generate a sequence of numbers corresponding to a healthy tooth."
  []
  (into [] (repeatedly 6 #(inc (rand-int 3)))))

(defn bad-tooth
  "Generate a sequence of numbers corresponding to a unhealthy tooth."
  []
  (into [] (repeatedly 6 #(inc (rand-int 4)))))

(defn generate-pockets
  "Generate vectors corresponding to provided legend"
  [legend good-percent]
  (into [] (first (repeatedly 1 #(for [leg legend] (if (= "F" leg) [] (if (>= good-percent (rand 1))
                                                                        (good-tooth)
                                                                        (bad-tooth))))))))
