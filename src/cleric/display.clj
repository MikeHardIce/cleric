(ns cleric.display
  (:require [strigui.core :as ui]))

(defn add-terrain
  [widgets window-name game-board]
  (let [res (:resources game-board)
        terrain (:terrain game-board)
        size (:tile-size game-board)]
    (loop [widgets widgets
           x 0]
      (if (seq (nth terrain x nil))
        (recur (loop [widgets widgets
                      y 0]
                 (let [tile (nth (nth terrain x nil) y nil)]
                   (if-not (seq tile)
                     widgets
                     (recur (ui/add-image widgets window-name (str "tile-" x "-" y) (:terrain tile)
                                          {:x (* x size) :y (* y size)
                                           :width size :height size :border-size 0
                                           :loaded-path (:terrain tile)
                                           :loaded-image (get res (:terrain tile) nil)
                                           :group "terrain"})
                            (inc y)))))
               (inc x))
        widgets)
      )))

(defn add-game-ui 
  [widgets window-name game-board])

(defn add-game-menu
  [widgets window-name game-board])

(defn add-main-menu
  [widgets window-name])
