(ns cleric.game
  (:require [capra.core :as ca]
            [clojure.java.io :as io]
            [clojure.string :as s]))

(defn split-paths 
  [path]
  (let [parts (s/split path (re-pattern (str "[\\." java.io.File/separator "]")))]
    [(s/join java.io.File/separator (reverse (rest (reverse (rest parts)))))
     path]))

(defn create-image-resources
  [& args]
  ;; on v, load images into memory
  (into {} (map (fn [[k v]] {k (ca/path->Image v)}) (partition 2 args))))

(defn load-all-image-resource 
  [path]
  (let [base-dir (io/file path)]
    (when (.exists base-dir)
      (let [files (filter #(s/ends-with? % "png")
                          (map #(.getPath %) (file-seq base-dir)))
            flat (flatten (map split-paths files))]
        (apply create-image-resources flat)))))

(defn create-board
  ([width height]
   (create-board width height {}))
  ([width height image-res]
   {:resources image-res
    :tile-size 100
    :terrain (for [x (range 0 width)]
               (for [y (range 0 height)]
                 {:terrain "terrain/grass1"
                  :movement 2}))}))