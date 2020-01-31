(ns ^:figwheel-hooks my-datascript-example.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [re-posh.core :refer [connect!] :as re-posh]
    [datascript.core :refer [create-conn]]
    [my-datascript-example.events :as events]
    [my-datascript-example.routes :refer [init-routes!]]
    [my-datascript-example.views :as views]
    [my-datascript-example.config :as config]))

(defn dev-setup [ds]
  (when js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    (re-frame/dispatch-sync [::events/set-ds ds])))

(defn init-db []
  (let [ds (create-conn)]
    (connect! ds)
    (re-posh/dispatch-sync [::events/initialize-ds])
    (re-frame/dispatch-sync [::events/initialize-db])
    ds))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (init-routes!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (init-db)
  (dev-setup ds)
  (mount-root))
