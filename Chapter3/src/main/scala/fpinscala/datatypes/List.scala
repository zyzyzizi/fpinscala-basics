package fpinscala.datatypes

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List:
  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail*))

  // Exercise 3.2
  // Remove the first element of the List.
  // Use sys.error("message") to throw an exception if the List is Nil.
  def tail[A](as: List[A]): List[A] = ???

  // Exercise 3.3
  // Replace the first element of the List with a different value.
  // Use sys.error("message") to throw an exception if the List is Nil.
  def setHead[A](as: List[A], value: A): List[A] = ???
  /*
   * The Efficiency of Data Sharing
   */

  // Exercise 3.4
  // Remove the first n elements from a list.
  // Dropping n elements from an empty list should return the empty lsit.
  def drop[A](as: List[A], n: Int): List[A] =
    if n <= 0 then as
    else ???

  // Exercise 3.5
  // Remove elements from the List prefix as long as they match a predicate.
  def dropWhile[A](as: List[A], f: A => Boolean): List[A] = ???

  def append[A](a1: List[A], a2: List[A]): List[A] = ???

  // Exercise 3.6 (Oops!)
  // Implement a function, init, that returns a List consisting of all but the last element of a List.
  def init[A](as: List[A]): List[A] = ???

  /*
   * Don't Repeat Yourself
   */

  def foldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B = ???
  // Exercise 3.9
  // Compute the length of a list using foldRight.
  def length[A](as: List[A]): Int = ???

  // Exercise 3.10
  // Write another general list-recursion function, foldLeft, that is tail-recursive.
  def foldLeft[A, B](as: List[A], acc: B)(f: (B, A) => B): B = ???

  // Exercise 3.11
  // Write sum, product, and a function to compute the length of a list using foldLeft.
  def sumViaFoldLeft(as: List[Int]): Int =
    foldLeft(as, ???)(???)

  def productViaFoldLeft(as: List[Double]): Double =
    foldLeft(as, ???)(???)

  def lengthViaFoldLeft[A](as: List[A]): Int =
    foldLeft(as, ???)(???)

  // Exercise 3.12
  // Write a function that returns the reverse of a list.
  def reverse[A](as: List[A]): List[A] =
    foldLeft(as, ???)(???)

  // Exercise 3.14
  // Implement append in terms of either foldLeft or foldRight.
  def appendViaFoldRight[A](as: List[A], bs: List[A]): List[A] =
    foldRight(???, ???)(???)

  // Exercise 3.15 [Hard]
  // Write a function that concatenates a list of lists into a single list.
  // Its runtime should be linear in the total length of all lists.
  def concat[A](as: List[List[A]]): List[A] = ???

  // Exercise 3.16
  // Write a function that transforms a list of integers by adding 1 to each element.
  // (Reminder: this should be a pure function that returns a new List!)
  def addOne(as: List[Int]): List[Int] = ???

  // Exercise 3.17
  // Write a function that turns each value in a List[Double] into a String.
  // You can use the expression d.toString to convert some d: Double to a String.
  def doubleToString(as: List[Double]): List[String] = ???

  // Exercise 3.18
  // Write a function map that generalizes modifying each element in a list
  // while maintaining the structure of the list.
  def map[A, B](as: List[A])(f: A => B): List[B] = ???

  // Exercise 3.19
  // Write a function filter that removes elements from a list unless they satisfy a given predicate.
  // Use it to remove all odd numbers from a List[Int].
  def filter[A](as: List[A])(f: A => Boolean): List[A] = ???

  // Exercise 3.20
  // Write a function flatMap that works like map except that the function given will
  // return a list instead of a single result, and that list should be inserted into
  // the final resulting list.
  // For instance, flatMap(List(1,2,3))(i => List(i,i)) should result in List(1,1,2,2,3,3).
  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = ???

  // Exercise 3.21
  // Use flatMap to implement filter.
  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] =
    flatMap(as) { ??? }

  // Exercise 3.22
  // Write a function that accepts two lists and constructs a new list by adding corresponding elements.
  // For example, List(1,2,3) and List(4,5,6) become List(5,7,9).
  def addPairwise(a: List[Int], b: List[Int]): List[Int] = ???
  // Exercise 3.23
  // Generalize the function you just wrote so that it’s not specific to integers or addition.
  // Name your generalized function zipWith.
  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = ???

  // Exercise 3.24 [Hard]
  // Implement hasSubsequence for checking whether a List contains another List as a subsequence.
  // For instance, List(1,2,3,4) would have List(1,2), List(2,3), and List(4) as subsequences, among others.
  // You may have some difficulty finding a concise purely functional implementation that is also efficient.
  // That’s okay. Implement the function however comes most naturally.
  // We’ll return to this implementation in chapter 5 and hopefully improve on it.
  // Note: Any two values x and y can be compared for equality in Scala using the expression x == y.
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean =
    @annotation.tailrec
    def startsWith(sup: List[A], sub: List[A]): Boolean = (sup, sub) match
      case (_, Nil)                             => true
      case (Cons(x, xs), Cons(y, ys)) if x == y => startsWith(xs, ys)
      case _                                    => false

    sup match
      case Nil                       => sub == Nil
      case _ if startsWith(sup, sub) => true
      case Cons(_, xs)               => hasSubsequence(xs, sub)
