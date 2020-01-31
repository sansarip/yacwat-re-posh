(ns my-datascript-example.events
  (:require
    [day8.re-frame.tracing :refer-macros [fn-traced]]
    [my-datascript-example.db :as db]
    [my-datascript-example.effects :as effects]
    [re-frame.core :as re-frame]
    [reitit.frontend.controllers :as rfc]
    [re-posh.core :as re-posh]))

(re-frame/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(re-posh/reg-event-ds
  ::initialize-ds
  (fn-traced [_ _]
             db/default-ds))

(re-frame/reg-event-db
  ::set-ds
  (fn-traced [db [_ ds]]
             (assoc db :ds ds)))

(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_db [_ & route]]
             ;; See `navigate` effect in routes.cljs
             {::effects/navigate! route}))

(re-frame/reg-event-db
  ::navigated
  (fn-traced [db [_ new-match]]
             (let [old-match (:current-route db)
                   controllers (rfc/apply-controllers (:controllers old-match) new-match)]
               (assoc db :current-route (assoc new-match :controllers controllers)))))
