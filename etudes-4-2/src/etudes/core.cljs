(ns etudes.core
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [cljs.nodejs :as nodejs]
            [hiccups.runtime :as hiccupsrt]
            [etudes.food-map :as food-map]))

(nodejs/enable-util-print!)

(def express (nodejs/require "express"))

(def food-map food-map/merged-condiment-food)

(defn html5
  "Wrapper around hiccup html to add html5 boilerplate"
  [& contents]
  (html
   "<!DOCTYPE html>\n"
   contents))

(defn remove-colon
  "Return str unmodified if it does not end in ':', else remove the ':'"
  [str]
  (if (= ":" (last str))
    (butlast str)
    str))

(defn generate-food-list
  "Generate div vector for hiccup-html"
  [condiment]
  (if (not (empty? condiment))
    [:p
     [:div "The following are meals that go well with " [:em condiment] ":"
      [:div
       [:ul
        (for [meal (map remove-colon (food-map condiment))]
          [:li meal])]]]]))

(defn generate-page
  "Generate html structure as string"
  [request]
  (let [selected request.query.selected
        sorted-condiments (sort (for [k food-map] (k 0)))]
    (html5
     [:head
      [:title "Etudes 4-2"]]
     [:body
      [:p "Condiment name: "]
      [:form {:action "/"} 
       [:select
        {:id "condimentMenu" :name "selected"}
        [:option {:value ""} "Choose a condiment"]
        (for [condiment sorted-condiments]
          (if (= condiment selected)
            [:option {:value "condiment" :selected "selected"} condiment]
            [:option {:value condiment} condiment]))]
       [:input {:type "submit" :value "Submit"}]
       (generate-food-list selected)]])))

(defn send-response [request response]
  "Callback function that sends back html structure as string"
  (let [page (generate-page request)]
    (.send response page)))

(defn -main []
  (let [app (express)]
    (.get app "/" send-response)
    (.listen app 3000
             (fn []
               (println "Server started on port 3000")))))

(set! *main-cli-fn* -main)
