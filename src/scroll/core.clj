(ns scroll.core
  (:require [clojure.string :as -s]))

(defn sanitize [text] (-s/replace text #"\"|<|>" {"\"" "&quot;" "<" "&lt;" ">" "&gt;"}))
  
(defn html [& elements]
  (def void-tags #{:!DOCTYPE :area :base :br :col :command :embed :hr :img :input :keygen :link :meta :param :source :track :wbr})

  (defn render-inner [i]
    (if (seq? i) (-s/join (map html i)) ""))   

  (defn render-attrs [a]
    (defn render-attr [[a v]]
      (cond
        (string? v) (str " " (name a) \= \" v \")
        :else (if v (str " " (name a)))))
      (-s/join (map render-attr a)))  

  (defn render-html [tag a i]
    (let [t (name tag)]
      (if (contains? void-tags tag)
        (str \< t a \>)
        (str \< t a \> i \<\/ t \>))))

  (defn render-tag [e]
    (let [tag (first e)
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
