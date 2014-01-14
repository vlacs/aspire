(ns aspire.security
  (:require [digest :refer [md5]]
            [aspire.data.user :refer [get-valid-user]]
            [aspire.util :refer [keywords->ns]]))

(def active-roles
  (set (keywords->ns 'aspire.data.user
                     :admin
                     :adult-ed
                     :guardian
                     :student
                     :teacher)))

(defn md5-credential-fn
  "This credential fn checks the database for the
  user instead of a clojure entity"
  [user]
  (get-valid-user (:username user) (md5 (:password user))))

