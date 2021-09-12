(ns aoc-eitan.day3)


(defn tree?
  "pre: takes a forest an x and y
  post: returns true if the forest has a tree in [(mod x width) y], false otherwise"
  [forest x y]
  (let [width (count (first forest))]
    (= \#
       (nth
        (nth forest y)
        (mod x width)))))

(defn inside?
  [forest [_ row]]
  (< row (count forest)))

(defn slope->pos
  "pre: takes a slope and a forest
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



