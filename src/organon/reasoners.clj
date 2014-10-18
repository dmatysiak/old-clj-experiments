(ns organon.reasoners
  "A library of reasoners."
  (:require [organon.combinatorics :refer [cartesian]]
            [organon.utils :refer [eval-expr-fn]]
            [clojure.tools.trace :refer [trace-forms]]))

(defn prover-naive-fn
  "Naive prover that returns the first values of the variables that renders the
   entire given expression true."
  ([vars expr]
     (prover-naive-fn vars expr :first true?))
  ([vars expr all-or-first]
     (prover-naive-fn vars expr all-or-first true?))
  ([vars expr all-or-first tfn]
     (prover-naive-fn vars expr all-or-first tfn
                      (cartesian [true false] (count vars))
                      []))
  ([vars expr all-or-first tfn vals col]
     {:pre [(contains? #{:first :all} all-or-first)]}
     (if (seq vals)
       (if (tfn (eval-expr-fn vars (first vals) expr))
         (let [match (map #(vector %1 %2) vars vals)]
           (cond (= all-or-first :first) (into col match)
                 (= all-or-first :all)   (recur vars expr all-or-first tfn (rest vals) (into col match)))))
       col)))

(defmacro prover-naive
  "Naive prover that returns the first values of the variables that renders the
   entire given expression true."
  ([vars expr]
     `(prover-naive-fn '~vars '~expr :first true?))
  ([vars expr all-or-first]
     `(prover-naive-fn '~vars '~expr all-or-first true?))
  ([vars expr all-or-first tfn]
     `(prover-naive-fn '~vars '~expr all-or-first tfn)))     


;; Define prover protocol? 
;; 
;;

