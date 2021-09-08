(ns aoc-eitan.day2-test
  (:require [clojure.test :refer :all]
            [aoc-eitan.day2 :as d2]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.test :as t :refer [deftest is]]))

(def input-file (io/resource "day2_input.txt"))

(def input
  (clojure.string/split-lines (slurp input-file)))

(def sample
  ["1-3 a: abcae"
   "2-6 e: eereee"
   "1-9 j: jjjbncjjj"
   "4-5 t: ttttt"
   "2-5 s: hrjt"])


(deftest test-part-a
  (testing "Sample given"
    (is (= 4 (d2/count-valid-password sample))))
  (testing "input given"
    (is (= 460 (d2/count-valid-password input)))))

(deftest test-part-b
  (testing "Sample given"
    (is (= 1 (d2/count-valid-password-pos sample))))
  (testing "input given"
    (is (= 251 (d2/count-valid-password-pos input)))))
