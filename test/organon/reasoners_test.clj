(ns organon.reasoners-test
  (:require [clojure.test :refer :all]
            [organon.reasoners :refer :all]))

(deftest prover-naive-test-basic
  (testing "Basic tests that the naive prover returns results TODO."
           (is (empty? (prover-naive [a] (not a))))
           (is (seq (prover-naive [a b] (or a b))))
           (is (seq (prover-naive [a b] (and a b))))
           (is (seq (prover-naive [a b] (implies a b))))
           (is (seq (prover-naive [a] (or a (not a)))))
           (is (empty? (prover-naive [a] (and a (not a)))))))