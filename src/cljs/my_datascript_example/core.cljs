(ns ^:figwheel-hooks my-datascript-example.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [re-posh.core :refer [connect!] :as re-posh]
    [datascript.core :refer [create-conn conn-from-db empty-db]]
    [datascript.transit :as dt]
    [my-datascript-example.events :as events]
    [my-datascript-example.routes :refer [init-routes!]]
    [my-datascript-example.views :as views]
    [my-datascript-example.config :as config]))

(defn dev-setup [ds]
  (when js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    (re-frame/dispatch-sync [::events/set-ds ds]))
  ds)

(defn init-db [ts]
  (let [ds (connect! (if ts
                       (conn-from-db (dt/read-transit-str ts))
                       (create-conn)))]
    (if-not ts (re-posh/dispatch-sync [::events/initialize-ds]))
    (re-frame/dispatch-sync [::events/initialize-db])
    ds))

(defn persist-ds [ds]
  (js/setInterval
    #(aset js/sessionStorage "last-state" (dt/write-transit-str @ds))
    5000)
  ds)

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (init-routes!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (-> (aget js/sessionStorage "last-state")
      init-db
      dev-setup
      persist-ds)
  (mount-root))
