(ns koans.14-recursion
  (:require [koan-engine.core :refer :all]))

(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

; (is-even? 0) => true
; (is-even? 1) => (not (is-even? 0)) => (not true) => false
; (is-even? 2) => (not (is-even? 1)) => (not false) => true
; (is-even? 3) => (not (is-even? 2)) => (not true) => false
; (is-even? 4) => (not (is-even? 3)) => (not false) => true
; .....

(defn is-even-bigint? [n]
  (loop [n   n
         acc true]
    (if (= n 0)
      acc
      (recur (dec n) (not acc)))))

; using tail-recursion here
; (recur (dec 100003N) (not true)
; (recur 100002N false)
; (recur 100001N true)
; (recur 100000N false)
; .....
; (recur 1N true)
; (recur 0N false) => false

(defn recursive-reverse [coll]
  (if (= (count coll) 1)
    (apply vector coll)
    (conj (recursive-reverse (rest coll)) (first coll)))
  )

(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))
    ))

(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even-bigint? 100003N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet it becomes more difficult the more steps you take"
  (= '(6 5 4 3 2) (recursive-reverse [2 3 4 5 6]))

  "Simple things may appear simple."
  (= 1 (factorial 1))

  "They may require other simple steps."
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
