#!/usr/bin/env bb
(ns day03
  (:require
    [clojure.string :as str]))


(def input
  (->> "input/2021/day03.txt"
       slurp
       str/split-lines))


(defn part1
  [input]
  (->> input
       (apply map vector)
       (map frequencies)
       (map #(sort-by val %))
       (map keys)
       (apply map vector)
       (map #(apply str %))
       (map #(Integer/parseInt % 2))
       (apply *)))


(println (str "Part 1: " (part1 input)))
