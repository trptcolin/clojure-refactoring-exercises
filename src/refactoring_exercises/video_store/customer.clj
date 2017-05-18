(ns refactoring-exercises.video-store.customer
  (:require [refactoring-exercises.video-store.movie :as movie]))

(defn create [name]
  {:type :customer :name name})

(defn add-rental [customer rental]
  (update-in customer [:rentals] (fnil conj []) rental))

(defn statement-body [customer]
  (loop [text ""
         rentals (:rentals customer)
         total-amount 0.0
         frequent-renter-points 0]
    (if-let [rental (first rentals)]
      (let [this-amount (condp = (:price-code (:movie rental))
                          movie/REGULAR (+ 2 (if (< 2 (:days rental))
                                               (* 1.5 (- (:days rental) 2))
                                               0))
                          movie/NEW_RELEASE (* 3 (:days rental))
                          movie/CHILDRENS (+ 1.5 (if (< 3 (:days rental))
                                                   (* 1.5 (- (:days rental) 3))
                                                   0)))
            these-renter-points (if (and (< 1 (:days rental))
                                         (= movie/NEW_RELEASE
                                            (:price-code (:movie rental))))
                                  2
                                  1)
            line-item (str "\t" (:title (:movie rental)) "\t" (double this-amount) "\n")]
        (recur (str text line-item)
               (rest rentals)
               (+ total-amount this-amount)
               (+ frequent-renter-points these-renter-points)))
      {:body-text text
       :total-amount total-amount
       :frequent-renter-points frequent-renter-points})))

(defn statement-header [customer]
  (format "Rental Record for %s\n" (:name customer)))

(defn statement-footer [amount points]
  (format "You owed %.1f\nYou earned %d frequent renter points\n"
          amount
          points))

(defn statement [customer]
  (let [{:keys [body-text total-amount frequent-renter-points]}
        (statement-body customer)]
    (str (statement-header customer)
         body-text
         (statement-footer total-amount frequent-renter-points))))
