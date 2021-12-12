#!/usr/bin/env bb
(ns day02
  (:require
    [clojure.string :as str]))


(def input
  (->> "input/2021/day02.txt"
       slurp
       str/split-lines))


(defn parse
  [line]
  (let [[_ direction unit-str] (re-find #"(\w+) (\d+)" line)
        unit (parse-long unit-str)]
    (case direction
      "up"      {:y   (- unit)}
      "down"    {:y      unit}
      "forward" {:x unit}
      nil)))


(defn- multiply
  [{:keys [y x]}]
  (when (and y x)
    (* y x)))


(defn part1
  [input]
  (->> input
       (map parse)
       (apply merge-with +)
       multiply))


(defn- move-or-aim
  [{aim :aim, y-acc :y, x-acc :x
    :or {aim 0, y-acc 0, x-acc 0}, :as acc}
   {:keys [y x]}]
  (cond
    y {:aim (+ aim y)
       :y y-acc
       :x x-acc}
    x {:aim aim
       :y (+ y-acc (* aim x))
       :x (+ x-acc x)}
    :else acc))


(defn part2
  [input]
  (->> input
       (map parse)
       (reduce move-or-aim)
       multiply))


(println (str "Part 1: " (part1 input)))
(println (str "Part 2: " (part2 input)))
