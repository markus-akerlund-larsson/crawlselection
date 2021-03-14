(ns crawlselection.pages.item
  (:require
    [crawlselection.routes :as routes]))

(defn render []
  (fn []
    (let [item 5]
      [:span.main
       [:h1 (str "Item " item " of crawlselection")]
       [:p [:a {:href (routes/path-for :items)} "Back to the list of items"]]])))