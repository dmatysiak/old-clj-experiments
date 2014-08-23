(ns organon.datatypes-test
  (:require [clojure.test :refer :all])
  (:require [organon.datatype :as dt])
  (:require [organon.datatypes :refer :all])
  (:import (organon.java.Interop)))


(deftest interop-test
  (testing "Test interop (delete me)"
    (let [t (Interop.)]
      (do 
        (is (= (.getSum t) 0) "Initial values")
        (.setNums t 1 3)
        (is (= (.getSum t) 4))))))


