(ns etudes.food-map)

(def file
  "Get command line xml file input as string"
  (aget process.argv 2))

(def node-xml-lite
  (js/require "node-xml-lite"))

(def js-object
  "Return JS object from file"
  (node-xml-lite.parseFileSync file))

(def clj-map
  "Convert JS Object to Clojure map"
  (js->clj js-object :keywordize-keys true))

(defn strip-map
  "Convert clj-map to nested list and strip off unneeded data"
  [thing]
  (cond
    (and (map? thing)
         (.endsWith (:name thing) "name")) (strip-map (:childs thing))
    (and (vector? thing)
         (= (count thing) 1)) (thing 0)
    (vector? thing) (map strip-map thing)
    (map? thing) (strip-map (:childs thing))))

(def foods
  "Get lists of foods with their condiments"
  (map rest (strip-map clj-map)))

(def condiments
  "Get all unique condiments"
  (set (flatten (map rest foods))))

(def condiment-food-pairs
  "Get list of [condiment food] vectors"
  (for [condiment condiments
        food foods
        :when (contains? (set food) condiment)]
    [condiment [(first food)]]))

(def condiment-food-map
  "Convert condiment-food-pairs to a map list"
  (map (fn [arg] (apply hash-map arg)) condiment-food-pairs))

(def merged-condiment-food
  "Merge the condiment’s food partners inside a vector"
  (reduce (partial merge-with into) condiment-food-map))
