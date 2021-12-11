#!/usr/bin/env bb
(ns day01
  (:require [clojure.string :as str]))

(def input-str (slurp "input/2021/day01.txt"))

(def input (->> input-str
                clojure.string/split-lines
                (map #(Integer/parseInt %))))

(->> (map < input (rest input)) (filter identity) count println)

(def input-3 (map + input (rest input) (rest (rest input))))



(->> (map < input-3 (rest input-3)) (filter identity) count println)