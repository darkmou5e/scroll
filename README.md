# scroll

A Hiccup-like micro library for rendering HTML. It's only 42 lines of code. Works
with Clojure and probably with ClojureScript (I don't test it).

## Usage

It is similar to Hiccup. 

```clojure
user=> (use 'scroll.core)
nil
user=> (html [:div {:class "header" :data-spam "eggs"}]) 
"<div class=\"header\" data-spam=\"eggs\"></div>"
```

For more examples see [tests](https://github.com/mouzzz/scroll/blob/master/test/scroll/core_test.clj).

## License

Copyright © 2015 Anton Bezrukov

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
