(ns refactoring-exercises.video-store.rental)

(defn create [movie days]
  {:type :rental
   :movie movie
   :days days})

