(ns crawlselection.pages.game
  (:require [crawlselection.game.events :as events]
            [clojure.set :as set]
            [crawlselection.game.rules.game :as game]
            [crawlselection.game.rules.dungeon :as dungeon]
            [crawlselection.game.rules.cards :as cards]))


(defn board [state]
  (let [floor (get-in @state [::game/state
                              ::game/map
                              ::dungeon/floors
                              0])
        width (::dungeon/width floor)
        height (::dungeon/height floor)
        tiles (::dungeon/tiles floor)]
    [:div
     [:table {:class "board"}
      (map (fn [row]
             [:tr (map (fn [tile]
                         [:td {:class "tile"} (::dungeon/name tile)])
                       row)])
           tiles)]
     ])
  )

(defn card [c]
  [:div {:class "card"}
   [:h4 (::cards/name c)]
   [:p.description (->> c
            ::cards/symbols
            (map (fn [[k v]] [(name k) v]))
            (into {})
            str)]])

(defn hand [state]
  (let [hand (get-in @state [::game/state ::game/hand])]
     (map card hand)))

(defn deck [state input]
  (let [draw-deck (get-in @state [::game/state ::game/deck])]
    [:div {:class "card deck"} "Deck: " (str (count draw-deck))
     [:button {:on-click #(input [::events/draw])}
      "Draw"]]))

(defn discard [state]
  (let [discard-pile (get-in @state [::game/state ::game/discard])]
    [:div {:class "card discard"} "Discard: " (str (count discard-pile))]))


(defn game-view [state event]
  (if (::game/state @state)
    [:div
     [:div.hand-area [board state] [deck state event] [discard state]]

     [:div.hand-area  (hand state)]
     ]
    [:div
     [:button {:on-click #(event [::events/new-game])} "New game"]]))

(defn render [state event]
  (fn [] [:span.main
          [game-view state event]]))