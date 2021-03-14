(ns crawlselection.game.rules.game
  (:require [crawlselection.game.rules.dungeon :as dungeon]
            [crawlselection.game.rules.cards :as cards]))

(defn new-game []
  {
   ::map (dungeon/basic-dungeon 6 6 dungeon/basic-tiles)
   ::deck (cards/init-deck)
   ::hand []})


(defn drawpile-size [game-state]
  (-> game-state ::deck count))

(defn drawpile-empty [game-state]
  (-> game-state drawpile-size (= 0)))

(defn reshuffle [deck]
  (-> deck
      (update ::draw #(vec (concat (::discard deck))))
      (assoc ::discard [])))

(defn check-reshuffle [game-state]
  (if (drawpile-empty game-state)
    (update game-state ::deck #(conj % (-> game-state
                                           ::deck
                                           reshuffle)))
    game-state))

(defn top-deck [game-state]
  (-> game-state
      ::deck
      first))


(defn draw-top [game-state]
  (if (drawpile-empty game-state)
    game-state
    (update game-state ::hand #(conj % (top-deck game-state)))))


(defn vec-remove
  [coll pos]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))

(defn discard [game-state card])


(defn draw [game-state]
  (-> game-state
      (check-reshuffle)
      (draw-top)
      (update-in [::deck] #(-> % rest vec))))