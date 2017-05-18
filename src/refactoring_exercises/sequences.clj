(ns refactoring-exercises.sequences)

(defn factorial [n]
  (loop [product 1N
         i 1]
    (if (<= i n)
      (recur (* product i) (inc i))
      product)))

(defn first-letters [words]
  (loop [words words
         acc []]
    (if-let [s (first words)]
      (let [letter (first s)]
        (recur (rest words) (conj acc letter)))
      acc)))

(defn- next-character [c]
  (char (inc (int c))))

(defn character-range [first-inclusive last-inclusive]
  (loop [character first-inclusive
         letters []]
    (if (<= (compare character last-inclusive) 0)
      (recur (next-character character)
             (conj letters character))
      letters)))
