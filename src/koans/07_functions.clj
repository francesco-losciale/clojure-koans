(ns koans.07-functions
  (:require [koan-engine.core :refer :all]))

(defn multiply-by-ten [n]
  (* 10 n))

(defn square [n] (* n n))

(meditations
  "Calling a function is like giving it a hug with parentheses"
  (= 81 (square 9))

  "Functions are usually defined before they are used"
  (= 20 (multiply-by-ten 2))

  "But they can also be defined inline"
  (= 10 ((fn [n] (* 5 n)) 2))

  "Or using an even shorter syntax"
  (= 60 (#(* 15 %) 4))

  "Even anonymous functions may take multiple arguments"
  (= 15 (#(+ %1 %2 %3) 4 5 6))

  "Arguments can also be skipped"
  (= "AACC" (#(str "AA" %2) "bb" "CC"))

  "One function can beget another"
  (= 9 (((fn [] #(+ %1 %2))) 4 5))

  "Functions can also take other functions as input"
  (= 20 ((fn [f] (f 4 5))
           #(* %1 %2)))

  "Higher-order functions take function arguments"
  (= 25 (#(%1 5)
          (fn [n] (* n n))))

  "But they are often better written using the names of functions"
  (= 25 ((fn [f] (f 5)) square)))

;Higher Order Functions
;
;A higher order function is a function that either:
;Takes one or more functions as arguments
;Returns a function as its result
;
;This is an important concept in functional programming in any language.
;Higher order functions allow us to compose functions. This means we can write small functions and combine them to create larger functions. Like putting a bunch of small LEGO bricks together to build a house.
