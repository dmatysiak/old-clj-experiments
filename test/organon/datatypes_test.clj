(ns organon.datatypes-test
  (:require [clojure.test :refer :all])
  (:require [organon.datatype :as dt])
  (:require [organon.datatypes :refer :all]))


(deftest datatype-def-test
  (testing "Test data type definition."
    (is (= 0 1))))
