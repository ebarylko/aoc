(ns aoc-eitan.day1-test
  (:require [clojure.test :refer :all]
            [aoc-eitan.day1 :as d1]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.test :as t :refer [deftest is]]))



(def input-file (io/resource "day1_input.txt"))

(def input
  (->> input-file
       slurp
       clojure.string/split-lines
       (map edn/read-string)))
input

(d1/sum-to-target 2020 input)

(def sample
  [1721
   979
   366
   299
   675
   1456])

(deftest test-part-a
  (testing "Sample given"
    (is (= 514579 (d1/multiply-2020-sum sample)))
    (is (= 1014171 (d1/multiply-2020-sum input)))))

(deftest test-part-b
  (testing "Sample given"
    (is (= 46584630 (d1/multiply-2020-sum-trio input)))))
