(ns geometry.logic)

(def example {:statement {:and {:statement {:a true} :conclusion {:b true}}
  {:statement {:b true} :conclusion {:c true}}} :conclusion {:statement {:a true} {:b true}}})

(def exampleL '((:and ((:a true)
                       (:b true)) ((:b true)
                                   (:c true)))
                ((:a true)
                 (:c true))))

(defprotocol LogicAtom
  (not [this])
  (? [this given]))

(defprotocol LogicStatement
  (compose? [this thing])
  (? [this given])
  (converse [this])
  (inverse [this])
  (contrapositive [this])
  (converse-inverse? [this])
  (not [this]))

(defrecord NotAtom [thing]
  LogicAtom
  (not [this]
    (Atom. thing))
  (? [this given]
    (? (not this) given)))

(defrecord Atom [thing]
  LogicAtom
  (not [this]
    (NotAtom. thing)
  (? [this given])
    "Not implemented")

(defrecord If [statement conclusion]
  LogicStatement
  (compose? [this thing]
    "Not implemented")
  (? [this given]
    "Not implemented")
  (converse [this]
    (If. conclusion statement))
  (inverse [this]
    (If. (not conclusion) (not statement)))
  (contrapositive [this]
    (If. (not statement) (not conclusion)))
  (converse-inverse [this]
    :nnt)
  (not [this]
    (If. statement (not conclusion))))
