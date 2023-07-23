package fpinscala.datatypes

import fpinscala.utils.Utils.*
import LazyList.*

object LazyListDemo1 /* extends App */:

// delim()
// println(empty[Int])
// println(cons(1, cons(2, cons(3, empty[Int]))))
// println(LazyList(1, 2, 3, 4, 5))
// delim()

// def expensive(x: Int): Int = { println(s"expensive($x)"); x }
// // val x = Cons(() => expensive(42), () => empty)
// val x = cons(expensive(42), empty)
// val h1 = x.headOption
// val h2 = x.headOption
// println(s"$h1, $h2")
// delim()

end LazyListDemo1

object LazyListDemo2 /* extends App */:
// val l1 = LazyList(1, 2, 3, 4, 5)

// delim()
// label("take(3)"); println(l1.take(3).toList)
// delim()
// // println(l1.existsViaFoldRight(_ > 3))

// label("drop(3)"); println(l1.drop(3).toList)
// delim()

// label("takeWhile(_ < 4)")
// println(l1.takeWhile(_ < 4).toList)
// delim()

end LazyListDemo2

object LazyListDemo3 /* extends App */:
// val l1 = LazyList(1, 2, 3, 4, 5)

// label("exists(_ == 3)")
// println(l1.exists(_ == 3))
// delim()

// label("foldRight(0)(_ + _)")
// println(l1.foldRight(0)(_ + _))
// delim();

// label("existsViaFoldRight(_ == 3)")
// println(l1.existsViaFoldRight(_ == 3))
// delim()

// label("forAll(_ < 10)")
// println(l1.forAll(_ < 10))
// delim()

// label("takeWhile(_ < 4) via FoldRight")
// println(l1.takeWhileViaFoldRight(_ < 4).toList)
// delim()

// label("headOption via FoldRight")
// println(l1.headOptionViaFoldRight)
// delim()

// label("map(_ + 100) via FoldRight")
// println(l1.map(_ + 100).toList)
// delim()

// label("filter(_ % 2 == 0) via FoldRight")
// println(l1.filter(_ % 2 == 0).toList)
// delim()

// label("append via FoldRight")
// println(l1.append(LazyList(101, 102, 103)).toList)
// delim()

// label("flatMap via FoldRight")
// println(l1.flatMap(i => LazyList(i, i + 100)).toList)
// delim()

// label("find")
// println(LazyList((0 to 100_000)*).find(_ == 3))
// delim()

end LazyListDemo3

object LazyListDemo4 /* extends App */:

// delim()
// label("ones")
// println(ones.take(10).toList)
// delim()

// label("continually")
// println(continually(5).take(10).toList)
// delim()

// label("from(5)")
// println(from(5).take(10).toList)
// delim()

// label("fibs")
// println(fibs.take(20).toList)
// delim()

end LazyListDemo4

object LazyListDemo5 /* extends App */:

  // delim()
  // label("unfold(0)(s => Some(s, s + 1))")
  // println(unfold(0)(s => Some(s, s + 1)).take(10).toList)
  // delim()

  // label("fibs via unfold")
  // println(fibsViaUnfold.take(20).toList)
  // delim()

  // label("from via unfold")
  // println(fromViaUnfold(5).take(10).toList)
  // delim()

  // label("ones via unfold")
  // println(onesViaUnfold.take(20).toList)
  // delim()

  // label("continually via unfold")
  // println(continuallyViaUnfold(5).take(10).toList)
  // delim()

  val l1 = LazyList(1, 2, 3, 4, 5)

  // label("map via unfold")
  // println(l1.mapViaUnfold(_ + 100).toList)
  // delim()

  // label("take via unfold")
  // println(l1.takeViaUnfold(3).toList)
  // delim()

  // label("takeWhile via unfold")
  // println(l1.takeWhileViaUnfold(_ < 4).toList)
  // delim()

  // label("zipWith via unfold")
  // println(l1.zipWithViaUnfold(LazyList(10, 20, 30, 40))(_ + _).toList)
  // delim()

  // label("zipAll")
  // println(l1.zipAll(LazyList(10, 20, 30, 40)).toList)
  // delim()

end LazyListDemo5

object LazyListDemo6 extends App:
  val l1 = LazyList(1, 2, 3, 4, 5)

// delim()
// label("Demo for isDefined")
// println((Some(1), Some(2))._2.isDefined)
// println((Some(1), None)._2.isDefined)
// delim()

// label("startsWith")
// println(l1.startsWith(LazyList(1, 2, 3)))
// println(l1.startsWith(LazyList(1, 3, 3)))
// delim()

// label("tails")
// println(l1.tails.map(_.toList).toList)
// delim()

// label("hasSubsequence")
// println(l1.hasSubsequence(LazyList(2, 3)))
// println(l1.hasSubsequence(LazyList(2, 4)))
// delim()

// label("scanRight")
// println(l1.scanRight(0)(_ + _).toList)
// delim()

end LazyListDemo6
