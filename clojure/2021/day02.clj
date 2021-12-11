#!/usr/bin/env bb
(ns day02
  (:require [clojure.string :as str]))

(def input (->> "input/2021/day02.txt"
                slurp
                str/split-lines))

(defn parse [line]
  (let [[_ direction unit-str] (re-find #"(\w+) (\d+)" line)
        unit (parse-long unit-str)]
    (case direction
      "up"      {:depth   (- unit)}
      "down"    {:depth      unit}
      "forward" {:horizontal unit}
      nil)))

(defn part1 [input]
  (let [sum (->> input
                 (map parse)
                 (apply merge-with +))
        depth      (get sum :depth 0)
        horizontal (get sum :horizontal 0)]
    (* depth horizontal)))

(prn (str "Part 1: " (part1 input)))
