#!/usr/bin/env bb
(ns day01
  (:require
    [clojure.string :as str]))


(def input
  (->> "input/2021/day01.txt"
       slurp
       str/split-lines
       (mapv parse-long)))


(defn part1
  [input]
  (->> input
       (partition 2 1)
       (filter #(apply < %))
       count))


(defn part2
  [input]
  (->> input
       (partition 3 1)
       (mapv #(apply + %))
       part1))


(println (str "Part 1: " (part1 input)))
(println (str "Part 2: " (part2 input)))
