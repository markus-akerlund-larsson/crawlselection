(ns crawlselection.pages.home
  (:require
    [crawlselection.routes :as routes]))

(defn render []
  (fn []
    [:span.main
     [:h1 "Welcscoum to test"]
     [:ul
      [:li [:a {:href (routes/path-for :items)} "Items of crawlselection"]]
      [:li [:a {:href "/broken/link"} "Broken link"]]]]))