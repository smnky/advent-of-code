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
      "up"      {:depth   (- unit)}
      "down"    {:depth      unit}
      "forward" {:horizontal unit}
      nil)))


(defn- multiply
  [{:keys [depth horizontal]}]
  (when (and depth horizontal)
    (* depth horizontal)))


(defn part1
  [input]
  (->> input
       (map parse)
       (apply merge-with +)
       multiply))


(defn- step-fn
  [{aim :aim, depth-acc :depth, horizontal-acc :horizontal
    :or {aim 0, depth-acc 0, horizontal-acc 0}, :as acc}
   {:keys [depth horizontal]}]
  (cond
    depth {:aim (+ aim depth)
           :depth depth-acc
           :horizontal horizontal-acc}
    horizontal {:aim aim
                :depth (+ depth-acc (* aim horizontal))
                :horizontal (+ horizontal-acc horizontal)}
    :else acc))


(defn part2
  [input]
  (->> input
       (map parse)
       (reduce step-fn)
       multiply))


(println (str "Part 1: " (part1 input)))
(println (str "Part 2: " (part2 input)))
