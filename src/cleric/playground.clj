(ns cleric.playground
  (:require [cleric.display :refer :all]
            [cleric.game :as g]
            [strigui.core :as ui]))

(defn -main 
  []
  (let [image-res (g/load-all-image-resource "resources")
        game-board (g/create-board 10 10 image-res)]
    (ui/swap-widgets! #(-> %
                           (ui/add-window "wnd-cleric" 50 50 1000 1050 "Cleric" {})
                           (add-terrain "wnd-cleric" game-board)))))