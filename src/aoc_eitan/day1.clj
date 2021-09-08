(ns aoc-eitan.day1)
                                        ; partition: helps seperate numbers into sequences of two items each, does not guarantee the two numbers are in any of the sequences
; take one number from the list and check if the sum of any of the other numbers is = to 2020, this requires for every item to check the entire length of the string

(defn to-target
  "pre: takes a target, a set of numbers and a number
  post: returns the pair <num, target - num> if target minus num exists in the set, otherwise nil"
  [target coll num]
  (when (coll (- target num))
    [(coll (- target num)) num]))


(defn sum-to-target
  "pre: takes a collection of numbers
  :post: returns the two numbers that when summed is 2020"
  [target nbrs]
  (first (remove nil? (map (partial to-target target (set nbrs)) nbrs))))


(defn multiply-2020-sum
  "pre: takes a collection of numbers
  post: returns the product of the two numbers that sum to 2020"
  [nbrs]
  (apply * (sum-to-target 2020 nbrs)))

(defn sum-to-2020-trio
  "pre: takes a collection and a number
  post: returns the number and the other two numbers that sum up to 2020 - number, if cannot be found returns nil"
  [coll num]
  (if-let [pair (sum-to-target (- 2020 num) coll)]
    (conj pair num)))
(sum-to-2020-trio #{234 1020 500 520} 1000)

(defn multiply-2020-sum-trio
  "pre: takes a collection of distinct numbers
  post: returns the trio of numbers in the collection that sum to 2020"
  [coll]
  (->> coll
       (map #(sum-to-2020-trio (set coll) %))
       (remove nil?)
       (first)
  (apply *)))

(multiply-2020-sum-trio [1000 1100 1010 10 987 3489])

(to-target 2020 #{2000 1900 10 10} 20)

(sum-to-target 20 #{1900 15 5})
