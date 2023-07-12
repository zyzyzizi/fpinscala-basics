package fpinscala.chapter6.rng

trait RNG:
  def nextInt: (Int, RNG)

// linear congruential generator
case class SimpleRNG(seed: Long) extends RNG:
  def nextInt: (Int, RNG) =
    val newSeed = (seed * 0x5deece66dL + 0xbL) & 0xffffffffffffL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)

object RNG:
  def int(rng: RNG): (Int, RNG) =
    val (i, rng2) = rng.nextInt
    (i, rng2)

  // Not purely functional, because it has side effects
  def randomPair_V1(rng: RNG): (Int, Int) =
    val (i1, _) = rng.nextInt
    val (i2, _) = rng.nextInt
    (i1, i2)

  // Purely functional
  def randomPair(rng: RNG): ((Int, Int), RNG) =
    val (i1, rng2) = rng.nextInt
    val (i2, rng3) = rng2.nextInt
    ((i1, i2), rng3)

  // Exercise 6.1
  // Write a function that uses RNG.nextInt to generate a random integer between
  // 0 and Int.maxValue (inclusive). Make sure to handle the corner case when nextInt
  // returns Int.MinValue, which doesn’t have a non-negative counterpart.
  def nonNegativeInt(rng: RNG): (Int, RNG) =
    val (i, rng2) = rng.nextInt
    (if i < 0 then -(i + 1) else i, rng2)

  // Exercise 6.2
  // Write a function to generate a Double between 0 and 1, not including 1.
  // Note: You can use Int.MaxValue to obtain the maximum positive integer value,
  // and you can use x.toDouble to convert an x: Int to a Double.
  def double(rng: RNG): (Double, RNG) =
    val (i, rng2) = nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), rng2)

  // Exercise 6.3
  // Write functions to generate an (Int, Double) pair, a (Double, Int) pair,
  // and a (Double, Double, Double) 3-tuple. You should be able to reuse the
  // functions you’ve already written.
  def intDouble(rng: RNG): ((Int, Double), RNG) =
    val (i, rng2) = rng.nextInt
    val (d, rng3) = double(rng2)
    ((i, d), rng3)

  def doubleInt(rng: RNG): ((Double, Int), RNG) =
    val ((i, d), rng2) = intDouble(rng)
    ((d, i), rng2)

  def double3(rng: RNG): ((Double, Double, Double), RNG) =
    val (d1, rng2) = double(rng)
    val (d2, rng3) = double(rng2)
    val (d3, rng4) = double(rng3)
    ((d1, d2, d3), rng4)

  // Exercise 6.4
  // Write a function to generate a list of random integers.
  def ints(count: Int)(rng: RNG): (List[Int], RNG) =
    def go(count: Int, rng: RNG, xs: List[Int]): (List[Int], RNG) =
      if count <= 0 then ???
      else ???

    go(count, rng, List())

end RNG
