(ns scroll.core-test
  (:require [clojure.test :refer :all]
            [scroll.core :refer :all]))

(deftest all-in-one-with-spam
    (is (= (html [:div]) "<div></div>"))
    (is (= (html [:div "Hello!"]) "<div>Hello!</div>"))
    (is (= (html [:div {:class "header"}]) "<div class=\"header\"></div>"))
    (is (= (html [:div {:class "header" :data-spam "eggs"}]) "<div class=\"header\" data-spam=\"eggs\"></div>"))
    (is (= (html [:input {:type "checkbox" :checked true}]) "<input type=\"checkbox\" checked>"))
    (is (= (html [:input {:type "checkbox" :checked false}]) "<input type=\"checkbox\">"))    
    (is (= (html [:br]) "<br>"))
    (is (= (html [:br "hello"]) "<br>"))
    (is (= (html [:!DOCTYPE {:html true}]) "<!DOCTYPE html>"))
    (is (= (html [:br "hello"]) "<br>"))
    (is (= (html [:span (sanitize "\"<>")]) "<span>&quot;&lt;&gt;</span>"))
    (is (= (html [:div {:class "header" :data-spam "eggs"} "Want some spam?"]
                 "spam"
                 " and spam"
                 [:p "and spam"]
                 "and spam with spam")
           "<div class=\"header\" data-spam=\"eggs\">Want some spam?</div>spam and spam<p>and spam</p>and spam with spam"))    
)
