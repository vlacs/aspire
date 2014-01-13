(ns aspire.security
  (:require [digest :refer [md5]]
            [aspire.data.user :refer [get-valid-user]]))

(def roles {::admin "ADMIN"
            ::adult-ed "ADULTED"
            ::deprecated "DEPRECATED"
            ::duplicate-student "DUPLICATESTUDENT"
            ::email-only "EMAILONLY"
            ::former-admin "FORMERADMIN"
            ::former-teacher "FORMERTEACHER"
            ::guardian "GUARDIAN"
            ::inactive "INACTIVE"
            ::office-general "OFFICEGENERAL"
            ::partner-school "PartnerSchool"
            ::student "STUDENT"
            ::teacher "TEACHER"})

(defn md5-credential-fn
  "This credential fn checks the database for the
  user instead of a clojure entity"
  [user]
  (get-valid-user (:username user) (md5 (:password user))))

