(ns crawlselection.util)

(defn foo-cljc [x]
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn normalize [component]
  (if (map? (second component))
    component
    (into [(first component) {}] (rest component))))

(defn render [component]
  "Allows 'reagent components' in server-side hiccup code"
  (cond
    (fn? component)
    (recur (component))

    (not (coll? component))
    component

    (coll? (first component))
    (map #(render %) component)

    (keyword? (first component))
    (let [[tag opts & body] (normalize component)]
      (->> body
           (map #(render %))
           (into [tag opts])))

    (fn? (first component))
    (recur (apply (first component) (rest component)))))