(ns my-datascript-example.views
  (:require
    [my-datascript-example.routes :refer [router-component]]
    ;; global styles
    [my-datascript-example.styles]))

;; good place to put a header and footer - before and after the router component, respectively
(defn main-panel []
  (router-component))
