(ns aoc-eitan.day3)

(defn check-tree
  "pre: takes an x and y coordinate of the forest, the forest, and a count of the trees encountered
  post: if the x and y coordinate at the forest contains '#', then increment the value of the tree count, otherwise add nothing."
  [coll tree-count x y]
  (cond-> tree-count
    (= \# (nth (nth coll y) x)) inc))

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

(defn many-slopes
  "pre: takes a collection of slopes and a forest
  post: returns a collection of the trees encountered under the different slopes"
  [slopes forest]
  (->> slopes
       (map #(apply tree-count forest %))
       (apply *)))



