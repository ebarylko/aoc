(ns aoc-eitan.day2)

(defn valid-password
  "pre: takes a string with the password policy and the password
  post: returns the password if the letter passed repeats between [min-r max-r], otherwise nil"
  [[min-r max-r ltr pwd]]
  (let [freq (frequencies pwd) ]
    (when (<= min-r (freq ltr 0) max-r) pwd)))

(defn valid-password-by-pos
  "pre: takes a vector with pos-1, pos-2, ltr, and password.
  post: returns the password if the letter is at pos-1 or pos-2, otherwise nil"
  [[p1 p2 ltr pwd]]
  (let [a (= ltr (nth pwd (dec p1) nil))
        b (= ltr (nth pwd (dec p2) nil))]
    (cond
      (and a b) nil
      (or a b) pwd)))

(defn parse-pwd-rule
  "pre: takes a string that describes a pwd rule as `min-max ltr: pwd`
  post: returns a vector with the [min max ltr pwd]"
  [rule]
  (let [[_ min max ltr pwd]  (re-matches #"(\d+)-(\d+) (\w): (\w+)" rule)]
    [(Integer/parseInt min) (Integer/parseInt max) (first ltr) pwd]
    ))

(defn count-valid-password
  "pre: takes a collection of strings containing the password policy and password
  post: returns the number of valid passwords"
  [coll]
  (->> coll
       (map parse-pwd-rule)
       (keep valid-password)
       (count)))

(defn count-valid-password-pos
  "pre: takes a collection of strings containing the password policy and password
  post: returns the number of valid passwords"
  [coll]
  (->> coll
       (map parse-pwd-rule)
       (keep valid-password-by-pos)
       (count)))
