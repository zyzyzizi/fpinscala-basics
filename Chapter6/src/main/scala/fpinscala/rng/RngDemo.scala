package fpinscala.rng

object RngDemo /* extends App */ {
  import fpinscala.utils.Utils.*
  import RNG.*

  delim()
  label("int")
  println(int(SimpleRNG(42)))
  delim()

  label("randomPair")
  println(randomPair(SimpleRNG(-7)))
  delim()

  label("nonNegativeInt")
  println(nonNegativeInt(SimpleRNG(-7)))
  delim()

  label("double")
  println(double(SimpleRNG(42)))
  delim()

  label("intDouble, doubleInt, double3")
  println(intDouble(SimpleRNG(42)))
  println(doubleInt(SimpleRNG(42)))
  println(double3(SimpleRNG(42)))
  delim()

  label("ints")
  println(ints(5)(SimpleRNG(42)))
  delim()
}
