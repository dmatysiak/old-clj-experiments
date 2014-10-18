(ns organon.state-machine
  (:require [clojure.lang.PersistentQueue :as pq]))
;; State machine = graph + starting state + stream

;;
;; Simple round-robin scheduler
;;
;; Items in each queue 
(defrecord SchedQueue [queue visits])

;; This map takes the form {[<priority>: '(<SchedQueue>*)]*}
(def rrscheduler (ref {}))

(defn push-node
  "Add new object/node to the scheduler."
  [obj priority]
  (dosync
   (if (not (priority @rrscheduler))
     (alter rrscheduler assoc priority (->SchedQueue pq/EMPTY 0)))
   (alter rrscheduler conj obj)))

;; (defn pop-node
;;   "Remove next node from scheduler."
;;   [
