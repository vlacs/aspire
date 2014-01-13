(ns aspire.data.user
  ^{:author "Jon Doane <jdoane@vlacs.org"
    :doc "This library knows how to get user data."}
  (:require [aspire.sqldb :refer [select! update!]]
            [honeysql.core :refer [param]]))

(def sis-user-table "mdl_sis_user")
(def sis-user-fields [:id
                      :sis_user_idstr
                      :sis_user_id
                      :username
                      ;;; Anything that needs the user's password should get
                      ;;; checked on the spot. Let's not let hashed passwords
                      ;;; float around the system. -jdoane 20140111
                      ;;; :password
                      :privilege
                      :lastname
                      :firstname
                      :email
                      :timemodified
                      :timecreated
                      :istest])

(def select-user-sql
  {:select sis-user-fields
   :from sis-user-table})

(def valid-user-sql
  (assoc select-user-sql :where [:and
                          [:= :username (param :username)]
                          [:= :password (param :password)]]))

(def user-by-id-sql
  (assoc select-user-sql :where [:= :id (param :id)]))

(defn get-valid-user
  "Get user when username and password match."
  [username password]
  (if-let [result (select! valid-user-sql :username username
                           :password password)]
    result
    nil))
