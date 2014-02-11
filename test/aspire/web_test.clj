(ns aspire.web-test
  (:require [aspire.web :as a-web]
            [clojure.test :refer :all]
            [ring.mock.request :as rmr]
            [aspire.web-test-config :refer :all]
            [datomic.api :refer [db q] :as d]
            [aspire.db-config :as a-db]))

;; web-test-config makes the mock ring requests to the resources
;; defined in web.clj. The resulting vars are available for testing here.
(use-fixtures :once web-test-config) 

#_(deftest resource-api-test
  (def keyed-headers
    (into {}
      (for [[k v] (:headers api-response)]
        [(keyword k) v])))
  (is (= (:status api-response) 200))
  (is (= (:Content-Type keyed-headers) "application/edn;charset=UTF-8" )))
 
;; Disabling this temporarily; see my comments in web_test_config. --moquist
#_(deftest resource-app-test
    (is (= (:status onboarding-response) 200)))

;; sample datomic acess test 
(deftest sample-datomic-test
  ;; sample test query from datomic getting started
  (def results (d/q '[:find ?c :where [?c :community/name]] (d/db a-db/conn)))
  (is (> (count results) 0 )))

