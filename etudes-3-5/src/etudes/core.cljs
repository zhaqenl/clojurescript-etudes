(ns etudes.core)

(def pocket-depths
  [[], [2 2 1 2 2 1], [3 1 2 3 2 3],
  [3 1 3 2 1 2], [3 2 3 2 2 1], [2 3 1 2 1 1],
  [3 1 3 2 3 2], [3 3 2 1 3 1], [4 3 3 2 3 3],
  [3 1 1 3 2 2], [4 3 4 3 2 3], [2 3 1 3 2 2],
  [1 2 1 1 3 2], [1 2 2 3 2 3], [1 3 2 1 3 3], [],
  [3 2 3 1 1 2], [2 2 1 1 3 2], [2 1 1 1 1 2],
  [3 3 2 1 1 3], [3 1 3 2 3 2], [3 3 1 2 3 3],
  [1 2 2 3 3 3], [2 2 3 2 3 3], [2 2 2 4 3 4],
  [3 4 3 3 3 4], [1 1 2 3 1 2], [2 2 3 2 1 3],
  [3 4 2 4 4 3], [3 3 2 1 2 3], [2 2 2 2 3 3],
  [3 2 3 2 3 2]])

(defn alert
  "Alert teeth number which needs repairing."
  [vector-of-vectors]
  (let [contains-4? (fn [vect] (contains? (set vect) 4))
        bool-vector (map contains-4? vector-of-vectors)
        bool-indexed (map-indexed (fn [index value] [(inc index) value]) bool-vector)
        true-filtered (filter (fn [arg] (contains? (set arg) true)) bool-indexed)
        number-mapped (map first true-filtered)]
    (into [] number-mapped)))
