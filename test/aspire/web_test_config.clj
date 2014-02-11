(ns aspire.web-test-config
  (:require [aspire.web :as a-web]
            [clojure.test :refer :all]
            [ring.mock.request :as rmr]
            [aspire.db-config :as a-testdb]))

(defn set-up-test []
  ;; this is where we would also initialize and populate test db

  ;; the following vars containing the various responses are available to the
  ;; tests defined in web_test.clj
  ;; I'm disabling the one test in here so far, because (1) it now
  ;; needs a DB connection, but (2) as soon as I push my code I'm
  ;; going to be working on switching us to datomic, so I don't want
  ;; to invest right now in getting a SQL DB connection working for
  ;; this test. :/
  ;; After we switch to datomic, it will be super-easy to populate a
  ;; datomic:mem DB for testing.
  ;; --moquist
  (a-testdb/setup-test-db!)
  ;;(def onboarding-response (a-web/onboarding (rmr/request :get "/")))
  (def auth-required-response (a-web/app (rmr/request :get "/"))))
  
(defn teardown-test []
  (a-testdb/teardown-test-db!))

(defn web-test-config
  [web-tests]
  (set-up-test)
  (web-tests)
  (try
    (teardown-test)
    (finally (println "web test complete"))))


