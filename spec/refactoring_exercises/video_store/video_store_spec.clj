(ns refactoring-exercises.video-store.video-store-spec
  (:require [refactoring-exercises.video-store.customer :as customer]
            [refactoring-exercises.video-store.movie :as movie]
            [refactoring-exercises.video-store.rental :as rental]
            [speclj.core :refer :all]))

(describe "video store"

  (with fred (customer/create "Fred"))

  (it "produces a statement for a single new release"
    (let [customer (-> (customer/create "Fred")
                       (customer/add-rental
                         (rental/create
                           (movie/create "The Cell" movie/NEW_RELEASE)
                           3)))]
      (should= (str "Rental Record for Fred\n"
                    "\tThe Cell\t9.0\n"
                    "You owed 9.0\n"
                    "You earned 2 frequent renter points\n")
               (customer/statement customer))))

  (it "produces a statement for two new releases"
    (let [customer (-> (customer/create "Fred")
                       (customer/add-rental
                         (rental/create
                           (movie/create "The Cell" movie/NEW_RELEASE)
                           3))
                       (customer/add-rental
                         (rental/create
                           (movie/create "The Tigger Movie" movie/NEW_RELEASE)
                           3)))]
      (should= (str "Rental Record for Fred\n"
                    "\tThe Cell\t9.0\n"
                    "\tThe Tigger Movie\t9.0\n"
                    "You owed 18.0\n"
                    "You earned 4 frequent renter points\n")
               (customer/statement customer))))

  (it "produces a statement for a single children's movie"
    (let [customer (-> (customer/create "Fred")
                       (customer/add-rental
                         (rental/create
                           (movie/create "The Tigger Movie" movie/CHILDRENS)
                           3)))]
      (should= (str "Rental Record for Fred\n"
                    "\tThe Tigger Movie\t1.5\n"
                    "You owed 1.5\n"
                    "You earned 1 frequent renter points\n")
               (customer/statement customer))))

  (it "produces a statement for multiple regular movies"
    (let [customer (-> (customer/create "Fred")
                       (customer/add-rental
                         (rental/create
                           (movie/create "Plan 9 from Outer Space" movie/REGULAR)
                           1))
                       (customer/add-rental
                         (rental/create
                           (movie/create "8 1/2" movie/REGULAR)
                           2))
                       (customer/add-rental
                         (rental/create
                           (movie/create "Eraserhead" movie/REGULAR)
                           3)))]
      (should= (str "Rental Record for Fred\n"
                    "\tPlan 9 from Outer Space\t2.0\n"
                    "\t8 1/2\t2.0\n"
                    "\tEraserhead\t3.5\n"
                    "You owed 7.5\n"
                    "You earned 3 frequent renter points\n")
               (customer/statement customer)))))
