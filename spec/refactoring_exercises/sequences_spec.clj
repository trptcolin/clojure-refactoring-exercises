(ns refactoring-exercises.sequences-spec
  (:require [speclj.core :refer :all]
            [refactoring-exercises.sequences :refer :all]))

(describe "factorial"
  (it "multiplies a single number"
    (should= 1 (factorial 1)))

  (it "multiplies two numbers"
    (should= 2 (factorial 2)))

  (it "multiplies lots of numbers"
    (should= 3628800 (factorial 10)))

  (it "multiplies maybe too many numbers"
    (should-not-throw (factorial 1000N))))

(describe "first-letters"
  (it "gets the first letters"
    (should= [\H \t \w \g \o]
             (first-letters ["Hi" "there" "what's" "going" "on"]))))

(describe "character-range"
  (it "produces a range of characters, given two inputs"
    (should= [\a \b \c \d \e \f]
             (character-range \a \f)))

  (it "is empty when the characters are backwards"
    (should= [] (character-range \z \y)))

  (it "has 1 element when the characters are the same"
    (should= [\x] (character-range \x \x))))
