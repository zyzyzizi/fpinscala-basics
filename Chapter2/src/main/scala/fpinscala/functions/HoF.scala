package fpinscala.functions

import fpinscala.utils.Utils.*

object Recursions {

  def factorial(n: Int): BigInt =
    if n <= 0 then 1
    else n * factorial(n - 1)

  def factorialTR(n: Int): BigInt =
    @annotation.tailrec
    def go(n: Int, acc: BigInt): BigInt =
      if n <= 0 then acc
      else go(n - 1, n * acc)
    go(n, 1)

  def fibonacci(n: Int): Int =
    if n <= 1 then n
    else fibonacci(n - 1) + fibonacci(n - 2)

  // Exercise 2.1
  // Write a recursive function to get the nth Fibonacci number.
  def fibonacciTR(n: Int): Int =
    @annotation.tailrec
    def go(n: Int, prev: Int, current: Int): Int =
      if n <= 1 then current
      else go(n - 1, current, prev + current)

    go(n, 0, 1)

}

object recursionDemo1 extends App {
  import Recursions.*

  // for (i <- 1 to 20) {
  //   println(s"$i: ${factorial(i)}")
  // }

  // for i <- 1 to 20 do println(s"$i: ${factorialTR(i)}")
  // end for

  // for i <- 1 to 45 do println(s"$i: ${fibonacci(i)}")
  // end for

  // for i <- 1 to 45 do println(s"$i: ${fibonacciTR(i)}")
  // end for
}

/*
 * Higher-order functions (HOFs)
 */

object RecursionDemo2 {
  import Recursions.*

  def abs(n: Int): Int =
    if n < 0 then -n
    else n

  def formatAbs(x: Int) =
    val msg = "The absolute value of %d is %d."
    msg.format(x, abs(x))

  def formatFactorial(n: Int) =
    val msg = "The factorial of %d is %d."
    msg.format(n, factorial(n))

  def formatResult(name: String, n: Int, f: Int => BigInt) =
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))

  /* @main */
  def printAbsAndFactorial: Unit =
    delim()
    println(formatAbs(-42))
    println(formatFactorial(7))
    delim()

    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))
    delim()
}
