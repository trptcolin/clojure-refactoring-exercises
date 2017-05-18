# Hints

## Sequences
- `factorial`
  - See if you can separate the logic for "iterate over the group of integers from 1 to n" from "multiply two numbers together".
- `first-letters`
  - Notice that this is accumulating the results of the same operation on each element of the input sequence.
- `character-range`
  - When the size & termination of a sequence you're generating depends on the elements you're processing, `iterate` can be a very useful tool. But be careful to only take as many elements as you need! It generates an infinite sequence.

## Video Store
- Consider what happens if we need to add another type of movie, or change the amount and/or points calculations. In OOP, we'd use polymorphism to make these kinds of changes fit easier. How does polymorphism work in Clojure, and how could we use it here?
- Identify as many separable concerns as possible. Are they independently testable, or do we really need to rely on these UI-level tests?
