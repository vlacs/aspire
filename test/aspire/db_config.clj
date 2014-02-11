(ns aspire.db-config
  ( :require  [datomic.api :as d]))

(def uri "datomic:mem://aspire-test")
(def test-db (d/create-database uri))
(def conn (d/connect uri))

(def schema-tx (read-string (slurp "test/seattle-schema.dtm")))
(def data-tx (read-string (slurp "test/seattle-data0.dtm")))

(defn setup-test-db!
  []
  (d/transact conn schema-tx)
  (d/transact conn data-tx))

(defn teardown-test-db!
  []
  ;; is calling release necessary / desirable?
  (d/release conn)
  (d/delete-database uri))


