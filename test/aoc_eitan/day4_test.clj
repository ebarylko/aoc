(ns aoc-eitan.day4-test
  (:require [clojure.test :refer :all]
            [aoc-eitan.day4 :as d4]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.test :as t :refer [deftest is]]))

(def input-file (io/resource "day4_input.txt"))

(def input
 (clojure.string/split-lines (slurp input-file)))

(def parsed-input
  (d4/parse-passports input))

(def sample
  ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd"
   "byr:1937 iyr:2017 cid:147 hgt:183cm"
   ""
   "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884"
   "hcl:#cfa07d byr:1929"
   ""
   "hcl:#ae17e1 iyr:2013"
   "eyr:2024"
   "ecl:brn pid:760753108 byr:1931"
   "hgt:179cm"
   ""
   "hcl:#cfa07d eyr:2025 pid:166559648"
   "iyr:2011 ecl:brn hgt:59in"])

(def parsed-sample
  (d4/parse-passports sample))

(deftest parse-passport-test
  (testing "Passing multiple lines with fields returns a passport"
    (is (= {:ecl "gry" :pid "860033327" :eyr "2020"
            :hcl "#fffffd" :byr "1937" :iyr "2017"
            :cid "147" :hgt "183cm"}
           (d4/parse-passport (take 2 sample))))))

(deftest part-a-filter-passport-test
  (testing "Valid passports are returned"
    (let [p1 {:ecl "gry" :pid "8600" :a "al" :d 1 :f 3 :t 9 :m 0 :cid 87}
          p2 {:ecl "gre" :pid "123"}]
      (is (= 1 (d4/valid-passport [p1 p2])))))

  (testing "Valid passports for sample"
    (is (= 2 (d4/valid-passport (d4/parse-passports sample)))))

  (testing "Invalid passports are filtered out"
    (is (= 239 (d4/valid-passport (d4/parse-passports input))))))

(def valid-passport
  {:eyr "2025", :cid "100", :hcl "#18171d", :ecl "amb", :hgt "170cm", :pid "186459387", :iyr "2018", :byr "1926"})

(def invalid-fields1
  {:eyr "1972", :cid "100", :hcl "#18171d", :ecl "amb", :hgt "170", :pid "186cm", :iyr "2018", :byr "1926"})


(deftest part-b-validation-on-fields
  (testing "Invalid passports"
    (is (not (d4/valid-fields? invalid-fields1))))

  (testing "valid passport"
    (is (d4/valid-fields? valid-passport)))
  (testing "Count valid sample-passport"
    (is (= 2 (d4/valid-passports-b parsed-sample))))
(testing "Count valid passports"
  (is (= 188 (d4/valid-passports-b parsed-input)))))
