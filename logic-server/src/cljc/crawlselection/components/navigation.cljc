(ns crawlselection.components.navigation
  (:require
    [crawlselection.routes :as routes]))


(defn navigation-bar [& entries]
  [:header
   [:p (interpose " | " (map (fn [[tag text]] [:a {:href (routes/path-for tag)} text]) entries))]])