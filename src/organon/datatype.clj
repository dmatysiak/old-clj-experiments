(ns organon.datatype
  "Data type library."
  (import 'organon.Interop))

(def data-types 
  "Data type storage."
  (ref {})) 

(defrecord Datum [value type]) ;; Concrete

(defn def-datatype
  "Function for defining new data types."
  [name genus conversions]
  (dosync
   (if (not (:name @data-types))
     (commute data-types merge {name {:genus genus
                                      :conversions (apply hash-map conversions)}}))))

(defn genus?
  "Predicate that checks whether the first argument has the second argument as
  its genus."
  [dtype genus]
  (loop [dt-genus (:genus dtype)]
    (if dt-genus
      (if (= dt-genus genus)
        true
        (recur (dtgenus @data-types))))))

(defn convert
  "Coverts given datum to the indicated type, if possible."
  [datum to-type]
  (if-let [convfn (to-type (:conversions ((:type datum) @data-types)))]
    (Datum. (convfn datum) to-type)))

(defmacro dfn
  "A convenience macro for unpacking the value inside a Datum for conversion."
  [bnd & body]
  (let [datumvar (first bnd)
        valuevar (second bnd)]
    `(fn [~datumvar] 
       (let [~valuevar (:value ~datumvar)]
         (do ~@body)))))

(def -main
  [&args]
  (let [t (.organon.Interop)]
    (do 
      (println "Getting sum: "(.getSum t))
      (.setNums 1 3)
      (println "Getting sum: "(.getSum t)))))
