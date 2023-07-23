package fpinscala.rand

import fpinscala.rng.*
import RNG.*

type Rand[+A] = RNG => (A, RNG) // State actions or state transitions

object Rand {

  val int: Rand[Int] = RNG.int(_)

  val nonNegativeInt: Rand[Int] = RNG.nonNegativeInt(_)

  val double: Rand[Double] = RNG.double(_)

  val intDouble: Rand[(Int, Double)] = RNG.intDouble(_)

  val doubleInt: Rand[(Double, Int)] = RNG.doubleInt(_)

  val double3: Rand[(Double, Double, Double)] = RNG.double3(_)

  def ints(count: Int): Rand[List[Int]] = RNG.ints(count)(_)

  def unit[A](a: A): Rand[A] = ???

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = ???

  def nonNegativeEven: Rand[Int] =
    map(nonNegativeInt)(i => i - (i % 2))

  // Exercise 6.5
  // Use map to reimplement double in a more elegant way.
  def doubleViaMap: Rand[Double] =
    map(nonNegativeInt)(i => i / (Int.MaxValue.toDouble + 1))

  /*
   * Combining State Actions
   */

  // Exercise 6.6
  // Write the implementation of map2 based on the following signature.
  // This function takes two actions, ra and rb, and a function f for combining
  // their results, and returns a new action that combines them:
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = ???

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] =
    map2(ra, rb)((_, _))

  val randIntDouble: Rand[(Int, Double)] = both(int, double)

  val randDoubleInt: Rand[(Double, Int)] = both(double, int)

  // Exercise 6.7
  // Hard: If you can combine two RNG transitions, you should be able
  // to combine a whole list of them. Implement sequence for combining
  // a List of transitions into a single transition. Use it to
  // reimplement the ints function you wrote before. For the latter,
  // you can use the standard library function List.fill(n)(x) to make
  // a list with x repeated n times.
  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = ???

  def intsViaSequence(count: Int): Rand[List[Int]] = ???

  /*
   * Neting State Actions
   */

  def nonNegativeLessThan_Manual(n: Int): Rand[Int] =
    rng =>
      val (i, rng2) = nonNegativeInt(rng)
      val mod = i % n
      if i + (n - 1) - mod >= 0 then (mod, rng2)
      else nonNegativeLessThan_Manual(n)(rng2)

  // Exercise 6.8
  // Implement flatMap, and then use it to implement nonNegativeLessThan.
  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = ???

  def nonNegativeLessThan(n: Int): Rand[Int] =
    flatMap(nonNegativeInt): i =>
      val mod = i % n
      if i + (n - 1) - mod >= 0 then unit(mod)
      else nonNegativeLessThan(n)

  // Exercise 6.9
  // Reimplement map and map2 in terms of flatMap. The fact that this is possible is what
  // we’re referring to when we say that flatMap is more powerful than map and map2.
  def mapViaFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] = ???

  def map2ViaFlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(
      f: (A, B) => C
  ): Rand[C] = ???
}
