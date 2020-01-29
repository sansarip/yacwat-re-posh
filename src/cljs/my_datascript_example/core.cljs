(ns ^:figwheel-hooks my-datascript-example.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [my-datascript-example.events :as events]
   [my-datascript-example.routes :refer [init-routes!]]
   [my-datascript-example.views :as views]
   [my-datascript-example.config :as config]))

(defn dev-setup []
  (when js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (init-routes!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
