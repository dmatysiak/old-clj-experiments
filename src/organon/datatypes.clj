(ns organon.datatypes
  "Data type definitions."
  ;; Local
  (:require [organon.datatype :refer :all]))

;;
;; Data types definitions
;;

;; Temperature
(def-datatype :Temperature
  nil
  {})

(def-datatype :Celsius
  :Temperature
  [:Fahrenheit (dfn [d n] (+ (* n (/ 9 5)) 32))
   :Kelvin (dfn [d n] (+ n 273.15))])

(def-datatype :Fahrenheit
  :Temperature
  [:Celsius (dfn [d n] (* (/ 5 9) (- n 32)))
   :Kelvin (dfn [d n] (+ (convert d :Celsius) 273.15))])

(def-datatype :Kelvin
  :Temperature
  [:Celsius (dfn [d n] (- n 273.15))
   :Fahrenheit (dfn [d n] (- (convert d :Celsius) 273.15))])

;; Length
;;...
