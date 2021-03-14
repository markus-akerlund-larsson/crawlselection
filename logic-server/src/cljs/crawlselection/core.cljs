(ns crawlselection.core
  (:require
    [reagent.session :as session]
    [crawlselection.routes :as routes]
    [reitit.frontend :as reitit]
    [crawlselection.game.events :as game-events]
    [clerk.core :as clerk]
    [accountant.core :as accountant]
    [crawlselection.routes :as routes]
    [crawlselection.components.navigation :as nav]
    [crawlselection.pages.home :as home-page]
    [crawlselection.pages.about :as about-page]
    [crawlselection.pages.items :as items-page]
    [crawlselection.pages.item :as item-page]
    [crawlselection.pages.game :as game-page]
    [reagent.core :as reagent :refer [atom]]))

;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home-page/render
    :about #'about-page/render
    :items #'items-page/render
    :item #'item-page/render
    :game #'game-page/render))



;; -------------------------
;; Page mounting component

(defonce app-state (reagent/atom {}))

(defn event-handler [event]
  (swap! app-state (game-events/events event)))

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div
       [nav/navigation-bar
        [:index "Home"]
        [:about "About"]
        [:game "Game"]]
       [page app-state event-handler]
       [:footer
        [:p "version ?"]]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path routes/router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (reagent/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))

    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path routes/router path)))})
  (accountant/dispatch-current!)
  (mount-root))
