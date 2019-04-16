(ns etudes.core)

(defn good-tooth
  "Generate a sequence of numbers corresponding to a healthy tooth."
  []
  (into [] (repeatedly 6 #(inc (rand-int 3)))))

(defn bad-tooth
  "Generate a sequence of numbers corresponding to an unhealthy tooth."
  []
  (into [] (repeatedly 6 #(inc (rand-int 4)))))

(defn generate-pockets
  "Generate vectors corresponding to provided legend"
  [legend good-percent]
  (let [gen-tooth #(for [leg legend]
                     (if (= "F" leg)
                       []
                       (if (>= good-percent (rand 1))
                         (good-tooth)
                         (bad-tooth))))]
    (into [] (->> gen-tooth
                  (repeatedly 1)
                  first))))
