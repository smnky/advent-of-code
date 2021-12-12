#!/usr/bin/env bb
(ns day02
  (:require
    [clojure.string :as str]))


(def input
  (->> "input/2021/day02.txt"
       slurp
       str/split-lines
       (map #(str/split % #"\s+"))
       (map #(update % 1 parse-long))))


(defn move
  [[x y] [direction amount]]
  (case direction
    "up"      [x, (- y amount)]
    "down"    [x, (+ y amount)]
    "forward" [(+ x amount), y]))


(defn part1
  [input]
  (->> input
       (reduce move [0 0])
       (apply *)))


(defn move-or-aim
  [[x y aim] [direction amount]]
  (case direction
    "up"      [x, y, (- aim amount)]
    "down"    [x, y, (+ aim amount)]
    "forward" [(+ x amount), (+ y (* aim amount)), aim]))


(defn part2
  [input]
  (->> input
       (reduce move-or-aim [0 0 0])
       butlast  ; omit aim
       (apply *)))


(println (str "Part 1: " (part1 input)))
(println (str "Part 2: " (part2 input)))
