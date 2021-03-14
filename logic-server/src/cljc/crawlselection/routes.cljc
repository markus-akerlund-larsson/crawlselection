(ns crawlselection.routes
  (:require
    #?(:cljs [reitit.frontend :as reitit]
       :clj [reitit.core :as reitit])
    ))

(def routes
  [["/" :index]
   ["/items"
    ["" :items]
    ["/:item-id" :item]]
   ["/about" :about]
   ["/game" :game]])

(def router
  (reitit/router
    routes))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))