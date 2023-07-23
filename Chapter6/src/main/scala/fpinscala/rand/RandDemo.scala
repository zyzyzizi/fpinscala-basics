package fpinscala.rand

import fpinscala.utils.Utils._
import fpinscala.rng.SimpleRNG
import Rand.*

object RandDemo /* extends App */ {

  delim()
  label("unit(42)")
  println(unit(42)(SimpleRNG(42)))
  delim()

  label("map(unit(42))(_ + 1)")
  println(map(unit(42))(_ + 1)(SimpleRNG(42)))
  delim()

  label("doubleViaMap(SimpleRNG(42))")
  println(doubleViaMap(SimpleRNG(42)))
  delim()

  /*
   * Combining State Actions Tests
   */
  label("map2(unit(1), unit(7))(_ + _)")
  println(map2(unit(1), unit(7))(_ + _)(SimpleRNG(42)))
  label("both(unit(1), unit(7))")
  println(both(unit(1), unit(7))(SimpleRNG(42)))
  label("randIntDouble(SimpleRNG(42))")
  println(randIntDouble(SimpleRNG(42)))
  label("randDoubleInt(SimpleRNG(42))")
  println(randDoubleInt(SimpleRNG(42)))
  delim()

  label("sequence(List(int, int, int))(SimpleRNG(42))")
  println(sequence(List(int, int, int))(SimpleRNG(42)))
  label("intsViaSequence(3)(SimpleRNG(42))")
  println(intsViaSequence(3)(SimpleRNG(42)))
  delim()

  /*
   * Neting State Actions Tests
   */

  label("nonNegativeLessThan_Manual(100)(SimpleRNG(42))")
  println(nonNegativeLessThan_Manual(100)(SimpleRNG(42)))
  delim()

  label("nonNegativeLessThan(100)(SimpleRNG(42))")
  println(nonNegativeLessThan(100)(SimpleRNG(42)))
  delim()
}
