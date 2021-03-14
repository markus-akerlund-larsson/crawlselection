(ns crawlselection.game.rules.cards
  (:require [crawlselection.game.engine.piece :as piece]))

(defn scout []
  (merge
    (piece/new-piece)
    {::name "Scout"
     ::symbols {::scout 1}}))

(defn fight []
  (merge
    (piece/new-piece)
    {::name "Fight"
     ::symbols {::fight 1}}))

(defn hide []
  (merge
    (piece/new-piece)
    {::name "Hide"
     ::symbols {::hide 1}}))

(defn explore []
  (merge
    (piece/new-piece)
    {::name "Examine"
     ::symbols {::examine 1}}))

(defn rest-prepare []
  (merge
    (piece/new-piece)
    {::name "Rest/Prepare"
     ::symbols {::rest 1
                ::prepare 1}}))

(defn vec-remove
  [coll pos]
  (vec (concat (subvec coll 0 pos) (subvec coll (inc pos)))))

(defn find-in [hand id]
  (first (filter #(= id (get % ::piece/id)) hand)))

(defn remove-from [hand card]
  (filterv #(not= (::piece/id card) (get % ::piece/id)) hand))

(defn basic-deck []
  [(scout) (scout)
   (fight) (fight)
   (hide) (hide)
   (explore) (explore)
   (rest-prepare) (rest-prepare)])

(defn init-deck []
  (shuffle (basic-deck)))

