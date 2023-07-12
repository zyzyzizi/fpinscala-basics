package fpinscala.chapter2
import fpinscala.utils.Utils.*

/*Monomorphic functions vs. Polymporphic functions
 *
 * Monomorphic functions: works only for one type.
 * Polymorphic functions: works * for any type.
 */
object PolymorphicFunctions /* extends App  */ {

  /** Monomorphic function to find an Int in an array
    */
  def findFirst_Mono(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if n >= ss.length then -1
      else if ss(n) == key then n
      else loop(n + 1)
    loop(0)
  }

  /** Polymorphic function to find an element in an array
    */
  def findFirst[A](ss: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if n >= ss.length then -1
      else if ??? then n
      else loop(n + 1)
    loop(0)
  }

  delim()
  // println(
  //   findFirst(Array(7, 9, 13), (x: Int) => x == 9)
  // ) // function liteal or anonymous function
  // delim()

  // Exercise 2.2
  // Implement isSorted, which checks whether an Array[A] is sorted according
  // to a given comparison function:
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = ???

  // println(isSorted(Array(1, 2, 3), _ > _)) // false
  // println(isSorted(Array(1, 2, 1), _ > _)) // false
  // println(isSorted(Array(3, 2, 1), _ < _)) // false
  // println(isSorted(Array(1, 2, 3), _ < _)) // true
}

/*
 * Following types to implementations
 */
object FollowingTypes /* extends App */ {

  // partially-applied function
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = ???

  // Exercise 2.3
  // Let’s look at another example, currying, which converts a function f of two arguments
  // into a function of one argument that partially applies f. Here again there’s only one
  // implementation that compiles. Write this implementation.
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = ???

  // Exercise 2.4
  // Implement uncurry, which reverses the transformation of curry. Note that since =>
  // associates to the right, A => (B => C) can be written as A => B => C.
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = ???

  // Exercise 2.5
  // Implement the higher-order function that composes two functions.
  def compose[A, B, C](f: B => C, g: A => B): A => C = ???

  // delim()
  // val add = (x: Int, y: Int) => x + y
  // val add_curried = curry(add)
  // println(add_curried(1)(2))

  // val add_uncurried = uncurry(add_curried)
  // println(add_uncurried(1, 2))
  // delim()

  // val inc = (x: Int) => x + 1
  // val square = (x: Int) => x * x
  // val f = compose(square, inc)
  // val g = inc andThen square

  // println(f(3))
  // println(g(3))
  // delim()

  // val h = square compose inc
  // println(h(3))
  // delim()
}
