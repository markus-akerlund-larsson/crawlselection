(ns crawlselection.game.events
  (:require [crawlselection.game.rules.game :as game]))



(defn events [event]
  (case (first event)
    ::new-game #(assoc % ::game/state (game/new-game))
    ::draw #(update % ::game/state game/draw)))

(defn handler [state event]
  ((events event) state))