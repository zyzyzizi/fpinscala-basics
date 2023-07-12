package fpinscala.chapter6.rand

object RandDemo /* extends App */ {
  import fpinscala.utils.Utils._
  import fpinscala.chapter6.rand.*
  import Rand.*
  import fpinscala.chapter6.rng.*
  import RNG.*

  delim()
  println(unit(42)(SimpleRNG(42)))
  delim()

  println(map(unit(42))(_ + 1)(SimpleRNG(42)))
  delim()

  println(doubleViaMap(SimpleRNG(42)))
  delim()

  /*
   * Combining State Actions Tests
   */
  println(map2(unit(1), unit(7))(_ + _)(SimpleRNG(42)))
  println(both(unit(1), unit(7))(SimpleRNG(42)))
  println(randIntDouble(SimpleRNG(42)))
  println(randDoubleInt(SimpleRNG(42)))
  delim()

  println(sequence(List(int, int, int))(SimpleRNG(42)))
  println(intsViaSequence(3)(SimpleRNG(42)))
  delim()

  /*
   * Neting State Actions Tests
   */

  println(nonNegativeLessThan(100)(SimpleRNG(42)))
  delim()

  println(nonNegativeLessThanViaFlatMap(100)(SimpleRNG(42)))
  delim()
}
