(ns aoc-eitan.day3)

(defn check-tree
  "pre: takes an x and y coordinate of the forest, the forest, and a count of the trees encountered
  post: if the x and y coordinate at the forest contains '#', then increment the value of the tree count, otherwise add nothing."
  [coll tree-count x y]
  (cond-> tree-count
    (= \# (nth (nth coll y) x)) inc))

(defn tree?
  "pre: takes a forest an x and y
  post: returns true if the forest has a tree in [(mod x width) y], false otherwise"
  [forest x y]
  (let [width (count (first forest))]
    (= \#
       (nth
        (nth forest y)
        (mod x width)))))

(defn tree-count
  "pre: takes a vector of strings, the x-coordinate of the slope and the y-coordinate of the slope
  post: returns the amount of trees encountered by applying a slope of -1/3 repeatedly until crossing the final row"
  [coll x-slope y-slope]
  (loop [tree 0 x 0 y 0]
    (if (>= y (count coll))
      tree
      (recur (check-tree coll tree x y)
             (mod (+ x x-slope) (count (first coll)))
             (+ y y-slope)))))

(defn inside?
  [forest [_ row]]
  (< row (count forest)))

(defn slope->pos
  "pre: takes a slop and a forest
  post: a infinite lazy collection of all the positions starting from [0 0] and adding the slope repeatedly"
  [slope]
  (iterate #(map + slope %) [0 0]))


(defn tree-count
  [forest x y]
  (->> (slope->pos [x y])
       (take-while (partial inside? forest))
       (filter #(apply tree? forest %))
       count))


(defn many-slopes
  "pre: takes a collection of slopes and a forest
  post: returns a collection of the trees encountered under the different slopes"
  [slopes forest]
  (->> slopes
       (map #(apply tree-count forest %))
       (apply *)))



