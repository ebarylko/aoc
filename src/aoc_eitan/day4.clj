(ns aoc-eitan.day4
  (:require [clojure.string :as st]))

(defn seven-fields?
  "pre: takes a passport 
  post: returns true if there are seven  keys, otherwise false"
  [passport]
  (->> passport
       count 
       (= 7 )))

(def ppt-rules
  {:byr #(<= 1920 (read-string %) 2002)
   :iyr #(<= 2010 (read-string %) 2020)
   :eyr #(<= 2020 (read-string %) 2030)
   ; "If cm, the number must be at least 150 and at most 193."
   ; "If in, the number must be at least 59 and at most 76."
   :hgt #(when-let [[_ num un] (re-matches #"(\d+)(cm|in)" %)]
           (let [num (Integer. num)]
             (or
              (and (= un "cm") (<= 150 num 193))
              (and (= un "in") (<= 59 num 76))))
           )
   ; "hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f."
   :hcl #(re-matches #"#[\da-fA-F]{6}" %)
   ;"ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth."
   :ecl #(re-matches #"(amb|blu|brn|gry|grn|hzl|oth)" %)
   :pid #(re-matches #"\d{9}" %)
   })

(valid-field? [:iyr "2009"]) 

"byr (Birth Year) - four digits; at least 1920 and at most 2002."
"iyr (Issue Year) - four digits; at least 2010 and at most 2020."
"eyr (Expiration Year) - four digits; at least 2020 and at most 2030."
"hgt (Height) - a number followed by either cm or in:"
"If cm, the number must be at least 150 and at most 193."
"If in, the number must be at least 59 and at most 76."
"ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth."
"pid (Passport ID) - a nine-digit number, including leading zeroes"

(defn valid-field?
  "pre: Takes a pair field x value from a passport
  post: Returns the result of applying the validation function for that field to the value"
  [[k v]]
  ((ppt-rules k identity) v))



(defn valid-fields?
  "Pre: takes a Map with passport fields
  Post: returns true if the passport is valid according the fields rules, false otherwise"
  [ppt]
  (println ppt)
  (every? valid-field? ppt))


(defn parse-passport 
  "Pre: takes a collection with lines that each line contains a sequence of `field:value`
  Post: returns a Map where each `field` is a key and the value is `value`"
  [lines]
  (->> lines
       (st/join " ")
       (#(st/split % #"\s"))
       (map #(st/split % #":"))
       (map #(update % 0 keyword))
       (into {})))

(defn parse-passports
  "pre: takes a collection of lines with each line containing a sequence of 'field:value'
  post: returns a Map for each passport"
  [lines]
  (->> lines
       (partition-by empty?)
       (remove (comp empty? first))
       (map parse-passport)))


(defn valid-passport
  "pre: takes a collection of passports
  post: returns the number of valid passports which contain all fields ignoring `:cid`"
  [coll]
  (count (filter (partial seven-fields? :cid) coll)))

(defn valid-passports-b
  "pre: takes a collection of passports
  post: returns the number of valid passports which contain all fields ignoring `:cid` and every field is valid according to the passport rules"
  [coll]
  (->> coll
       (map #(dissoc % :cid))
       (filter (every-pred seven-fields? valid-fields?))
       count))
