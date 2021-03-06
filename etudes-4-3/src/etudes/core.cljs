(ns etudes.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as str]
            [cljs-node-io.core :as io :refer [slurp]]))

;;--------------------------------------------------------------------------------------------------
;; String functions
;;--------------------------------------------------------------------------------------------------

(defn newline-split
  [str]
  (str/split str "\n"))

(defn tab-split
  [str]
  (str/split str "\t"))

(defn space->hyphen
  "Replace space occurrence with hypher (for keyword conversion of strings with spaces)"
  [str]
  (str/replace str " " "-"))

;;--------------------------------------------------------------------------------------------------
;; Process CSV file
;;--------------------------------------------------------------------------------------------------

(def string-csv
  "Get commandline argument then return contents as whitepsace-delimited string"
  (slurp (aget process.argv 2)))

(def entries-by-row
  "Rows are separated by a newline"
  (newline-split string-csv))

(def separated-by-field
  "Separate tab-delimited fields and their respective entries"
  (into [] (map tab-split entries-by-row)))

(def process-csv-entries
  "Return vector with 2 elements--row headings as keywords and their respective entries"
  (let [heading-keywords (map keyword (map space->hyphen (separated-by-field 0)))
        case-vectors (rest separated-by-field)]
    [heading-keywords case-vectors]))

(def map-heading-entry
  "Return vector of heading-and-entry maps"
  (let [processed process-csv-entries]
    (into [] (map (fn [entry] (zipmap (processed 0) entry)) (processed 1)))))

;;--------------------------------------------------------------------------------------------------
;; Helper functions
;;--------------------------------------------------------------------------------------------------

(defn unique-column-values
  "Return vector of unique column values"
  [processed-csv column]
  (let [raw-values (map (fn [arg] (column arg)) processed-csv)
        unique-values (into [] (sort (set raw-values)))]
    [raw-values unique-values]))

(defn count-occurrence
  "Count occurrences of element inside sequence"
  [element sequence]
  (let [same-word? (fn [word] (= word element))
        filtered (filter same-word? sequence)]
    (count filtered)))

(defn map-count
  "Count occurences of each unique-column-values"
  [unique-values raw-values]
  (map (fn [arg] (count-occurrence arg raw-values)) unique-values))

;;--------------------------------------------------------------------------------------------------
;; Main function
;;--------------------------------------------------------------------------------------------------

(defn frequency-table
  "Return a vector with 2 elements--unique column values, respective counts, and the total"
  [processed-csv column]
  (let [[raw-values unique-values] (unique-column-values processed-csv column)
        counted-map (into [] (map-count unique-values raw-values))]
    [unique-values counted-map (apply + counted-map)]))
