(ns my-datascript-example.cards
  (:require [devcards.core :as dc]
            [my-datascript-example.cards.components]))

(enable-console-print!)

(defn ^:export init []
  (dc/start-devcard-ui!))
