(ns organon.core)


;;
;; Protocols
;;
(defprotocol Functor
  (fmap [ftr fn])
  (<$ [ftr obj]))

(defprotocol Applicative
  (pure [ftr fn])
  (<*> [ftr obj])
  (*> [ftr obj])
  (<* [ftr obj]))


;;
;; Records
;;
(defrecord Tree [value children])


;;
;; Protocol Implementations
;;
(extend-protocol Functor
  Tree
  (fmap [ftr fn]
    (Tree. (fn (:value ftr))
           (let [children (:children ftr)]
             (if (some? children)
               (map #(fmap % fn) children)))))
  (<$ [ftr obj]
    (Tree. obj
           (let [children (:children ftr)]
             (if (some? children)
               (map #(<$ % obj) children))))))

(extend-protocol Functor
  clojure.lang.PersistentVector
  (fmap [ftr fn]
    (vec (map fn ftr)))
  (<$ [ftr obj]
    (vec (repeat (count ftr) obj))))

(extend-protocol Functor
  clojure.lang.PersistentList
  (fmap [ftr fn]
    (map fn ftr))
  (<$ [ftr obj]
    (repeat (count ftr) obj)))
