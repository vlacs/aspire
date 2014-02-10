(ns aspire.db-config
  ( :require [datomic.api :as d]))

(def uri "datomic:mem://aspire-test")
(def test-db (d/create-database uri))
(def conn (d/connect uri))

(def schema-tx (read-string (slurp "test/seattle-schema.dtm")))
(def data-tx (read-string (slurp "test/seattle-data0.dtm")))

(defn setup-test-db!
  []
  (d/transact conn schema-tx)
  (d/transact conn data-tx))


;; sample test query from datomic gettin started
;;(def results (d/q '[:find ?c :where [?c :community/name]] (d/db conn)))
;;(count results)


