(ns crawlselection.game.util)

(defn uuid []
  #?(:clj  (java.util.UUID/randomUUID)
     :cljs (random-uuid)))

(def id uuid)