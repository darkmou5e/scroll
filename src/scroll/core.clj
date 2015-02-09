(ns scroll.core
  (:require [clojure.string :as -s]))

(defn html [& elements]

  (defn render-inner [i]
    (if (seq? i) (-s/join (map html i)) ""))   

  (defn render-attrs [a]
    (defn render-attr [[a v]]
      (cond
        (string? v) (str " " (name a) \= \" v \")
        :else (if v (str " " (name a)))))
      (-s/join (map render-attr a)))  

  (defn render-html [t a i] (str \< t a \> i \<\/ t \>))

  (defn render-tag [e]
    (let [tag (name (first e))
          attrs-inner (seq (rest e))]
      (if (map? (first attrs-inner))
        (render-html tag
                     (render-attrs (first attrs-inner))
                     (render-inner (rest attrs-inner)))
        (render-html tag
                     ""
                     (render-inner attrs-inner)))))

  (defn render [element]
    (cond
      (string? element) element
      (vector? element) (render-tag element)
      (seq? element) (-s/join (map render element))))

  (-s/join (map render elements)))



