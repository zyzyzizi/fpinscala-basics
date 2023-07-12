package fpinscala.chapter6.rng

object RngDemo /* extends App */ {
  import fpinscala.utils.Utils.*
  import RNG.*

  delim()
  println(int(SimpleRNG(42)))
  delim()

  println(randomPair(SimpleRNG(-7)))
  delim()

  println(nonNegativeInt(SimpleRNG(-7)))
  delim()

  println(double(SimpleRNG(42)))
  delim()

  println(intDouble(SimpleRNG(42)))
  println(doubleInt(SimpleRNG(42)))
  println(double3(SimpleRNG(42)))
  delim()

  println(ints(5)(SimpleRNG(42)))
  delim()
}
