package fpinscala.datatypes

import fpinscala.utils.Utils.*

object HoF extends App {
  import List.*

  def sum(ints: List[Int]): Int = ints match
    case Nil         => 0
    case Cons(x, xs) => x + sum(xs)

  def product(ds: List[Double]): Double = ds match
    case Nil         => 1.0
    case Cons(x, xs) => x * product(xs)

  val as = List(1, 2, 3, 4, 5)
  val bs = List(1.0, 2.0, 3.0, 4.0, 5.0)

  delim()
  println(s"sum = ${sum(as)}")
  println(s"product = ${product(bs)}")
  delim()

  // println(foldRight(as, 0)((x, y) => x + y))
  // println(foldRight(bs, 1.0)(_ * _))
  // delim()

  // // Exercise 3.8
  // // See what happens when you pass Nil and Cons themselves to foldRight.
  // println(
  //   foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _))
  // )
  // delim()

  // println(reverse(as))
  // delim()

  // val xs = List(1, 2, 3)
  // val ys = List(4, 5, 6)
  // println(appendViaFoldRight(xs, ys))
  // delim()

  // println(concat(List(List(1, 1), List(2, 2), List(3, 3))))
  // delim()

  // println(addOne(as))
  // delim()

  // println(doubleToString(bs))
  // delim()

  // println(map(as)(_ + 1))
  // delim()

  // println(filter(as)(_ % 2 == 0))
  // delim()

  // println(flatMap(as)(i => List(i, i)))
  // delim()

  // println(concat(List(List(1, 2), Nil, List(3, 4))))
  // delim()

  // println(filterViaFlatMap(as)(_ % 2 == 0))
  // delim()

  // val ms = List(1, 2, 3, 4, 5)
  // val ns = List(11, 12, 13)
  // println(addPairwise(ms, ns))
  // delim()

  // println(zipWith(ms, ns)(_ + _))

  // delim()

  // val ks = List("hello", "yellow", "mellow")
  // println(zipWith(ms, ks)((_, _)))
  // delim()

  // println(hasSubsequence(as, List(3, 5)))
  // delim()
}
