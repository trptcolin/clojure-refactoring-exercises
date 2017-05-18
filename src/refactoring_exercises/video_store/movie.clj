(ns refactoring-exercises.video-store.movie)

(def REGULAR 0)
(def NEW_RELEASE 1)
(def CHILDRENS 2)

(defn create [title price-code]
  {:type :movie
   :title title
   :price-code price-code})
