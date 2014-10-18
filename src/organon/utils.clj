(ns organon.utils
  "Utility functions.")

;;
;; Vector
;;
(defn conjv
  "Conj that returns a vector instead of a list."
  [s e]
  (vec (conj (vec s) e)))

(defn consv
  "Cons that returns a vector instead of a list."
  [e s]
  (vec (cons e (vec s))))

(defn concatv
  "Concat that returns a vector instead of a list."
  [& s]
  (vec (apply concat (vec s))))

;;
;; Misc. sequence
;;
(defn nth-rest
  "Consumes a sequence and returns a list with the nth element and a vector
   containing all remaining elements."
  ([n seq]
     (list (nth seq n) (nth-rest n seq true)))
  ([n seq only-rest?]
     (concatv (take n seq) 
              (drop (+ n 1) seq))))

;;
;; Debugging
;;
(defmacro dbugpr
  "A function for debugging that returns the last value of the expressions
   given to it."
  [annot expr & other]
  `(let [val# ~expr]
     (do
       (println "DEBUG: " ~annot)
       (println "-EXPR: " '~expr)
       (println "-VALU: " val#)
       (println "-TYPE: " (type val#))
       (if (seq ~other) 
         (println "-OTHR: " '~@other))
       (println)
       val#)))

;;
;; Logic and evaluation
;;
(defn implies
  "Logical implication."
  [p q]
  (or (not p) q))

(defn eval-expr-fn
  "Function for evaluating given expression using given values for the given
   vars."
  [vars vals expr]
  (eval 
   `(let [~(vec (cons 'implies vars)) ~(vec (cons implies vals))]
      ~expr)))

(defmacro eval-expr
  "Macro for evaluating given expression using given values for the given
   vars."
  [vars vals expr]
  `(eval-expr-fn '~vars '~vals '~expr))