(ns aoc-eitan.day3-test
  (:require [clojure.test :refer :all]
            [aoc-eitan.day3 :as d3]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.test :as t :refer [deftest is]]))

(def input-file (io/resource "day3_input.txt"))

(def input
  (clojure.string/split-lines (slurp input-file)))


(def sample
  ["..##......."
   "#...#...#.."
   ".#....#..#."
   "..#.#...#.#"
   ".#...##..#."
   "..#.##....."
   ".#.#.#....#"
   ".#........#"
   "#.##...#..."
   "#...##....#"
   ".#..#...#.#"])

(deftest test-part-a
  (testing "sample given"
    (is (= 7 (d3/tree-count sample 3 1))))
(testing "input given"
  (is (= 282 (d3/tree-count input 3 1)))))

(deftest test-part-b
  (testing "sample given"
    (is (= 336 (* (d3/tree-count sample 3 1) (d3/tree-count sample 1 1) (d3/tree-count sample 5 1) (d3/tree-count sample 7 1) (d3/tree-count sample 1 2)))))
  (testing "input given"
    (is (= 958815792 (* (d3/tree-count input 3 1) (d3/tree-count input 1 1) (d3/tree-count input 5 1) (d3/tree-count input 7 1) (d3/tree-count input 1 2))))) )

