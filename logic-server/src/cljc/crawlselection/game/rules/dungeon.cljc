(ns crawlselection.game.rules.dungeon
  (:require [crawlselection.game.engine.piece :as piece]))

(defn entrance []
  (merge
    (piece/new-piece)
    {::name "Entrance"}))

(defn treasure-vault []
  (merge
    (piece/new-piece)
    {::name "Treasure Vault"}))

(defn empty-room []
  (merge
    (piece/new-piece)
    {::name "Empty room"}))

(defn library []
  (merge
    (piece/new-piece)
    {::name "Library"}))

(defn trapped-hallway []
  (merge
    (piece/new-piece)
    {::name "Trapped Hallway"}))

(defn armory []
  (merge
    (piece/new-piece)
    {::name "Armory"}))

(defn kitchen []
  (merge
    (piece/new-piece)
    {::name "Kitchen"}))

(defn locked-room []
  (merge
    (piece/new-piece)
    {::name "Locked Room"}))

(def basic-tiles
  (shuffle (vec (concat (repeatedly 7 empty-room)
                        (repeatedly 4 library)
                        (repeatedly 7 trapped-hallway)
                        (repeatedly 5 armory)
                        (repeatedly 4 kitchen)
                        (repeatedly 7 locked-room)
                        (repeatedly 1 entrance)
                        (repeatedly 1 treasure-vault)))))

(defn generate-floor [width, height, tileset]
  {::width width
   ::height height
   ::tiles (->> tileset
                (partition width)
                (map vec)
                vec)})


(defn basic-dungeon [width, height, tileset]
  {::floors [(generate-floor width height tileset)]})
