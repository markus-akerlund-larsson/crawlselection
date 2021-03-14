(ns crawlselection.pages.items
  (:require
    [crawlselection.routes :as routes]))

(defn render []
  (fn []
    [:span.main
     [:h1 "The items of crawlselection"]
     [:ul (map (fn [item-id]
                 [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                  [:a {:href (routes/path-for :item {:item-id item-id})} "Item: " item-id]])
               (range 1 60))]]))