package fpinscala.chapter5.datatypes.lazylist

enum LazyList[+A]:
  case Empty
  // Scala doesn’t allow the parameters of a case class to be by-name parameterss(
  // and each data constructor of an enum which takes parameters defines a case class).
  case Cons(h: () => A, t: () => LazyList[A])

  def headOption: Option[A] = ???

  // Exercise 5.1
  // Write a function to convert a LazyList to a List, which will force its evaluation.
  def toList: List[A] = this match
    case Empty      => ???
    case Cons(h, t) => ???

  // Exercise 5.2
  // Write the function take(n) for returning the first n elements of a LazyList, and
  // drop(n) for skipping the first n elements of a LazyList.
  def take(n: Int): LazyList[A] =
    if n <= 0 then Empty
    else
      this match
        case Empty      => ???
        case Cons(h, t) => ???

  def drop(n: Int): LazyList[A] =
    if n <= 0 then this
    else
      this match
        case Empty      => ???
        case Cons(_, t) => ???

  // Exercise 5.3
  // Write the function takeWhile for returning all starting elements of a LazyList that
  // match the given predicate.
  def takeWhile(p: A => Boolean): LazyList[A] = this match
    case Empty      => ???
    case Cons(h, t) => ???

  /** More generally speaking, laziness lets us separate the description of an
    * expression from the evaluation of that expression. This gives us a
    * powerful ability—we may choose to describe a “larger” expression than we
    * need, and then evaluate only a portion of it.
    */
  def exists(p: A => Boolean): Boolean = ???

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match
    case Empty      => ???
    case Cons(h, t) => ???

  def existsViaFoldRight(p: A => Boolean): Boolean =
    foldRight(???)(???)
  /*
   * Separating program description from evaluation
   */

  // Exercise 5.4
  // Implement forAll, which checks that all elements in the LazyList match a given predicate.
  def forAll(p: A => Boolean): Boolean =
    foldRight(???)(???)
  // Exercise 5.5
  // Use foldRight to implement takeWhile.
  def takeWhileViaFoldRight(p: A => Boolean): LazyList[A] =
    foldRight(???)(???)

  // Exercise 5.6 [Hard]
  // Implement headOption using foldRight.
  def headOptionViaFoldRight: Option[A] =
    foldRight(???)(???)

  // Exercise 5.7
  // Implement map, filter, append, and flatMap using foldRight. The append method
  // should be non-strict in its argument.
  def map[B](f: A => B): LazyList[B] =
    foldRight(???)(???)

  def filter(f: A => Boolean): LazyList[A] =
    foldRight(???)(???)

  def append[A](s: => LazyList[A]): LazyList[A] =
    foldRight(???)(???)

  def flatMap[B](f: A => LazyList[B]): LazyList[B] =
    foldRight(???)(???)

  // Use filter and headOption to implement find.
  def find(p: A => Boolean): Option[A] =
    filter(p).headOption

  // Exercise 5.13
  // Use unfold to implement map, take, takeWhile, zipWith (as in chapter 3), and
  // zipAll. The zipAll function should continue the traversal as long as either
  // list has more elements—it uses Option to indicate whether each list has been
  // exhausted.
  def mapViaUnfold[B](f: A => B): LazyList[B] =
    LazyList.unfold(???)(???)

  def takeViaUnfold(n: Int): LazyList[A] =
    LazyList.unfold(???)(???)

  def takeWhileViaUnfold(f: A => Boolean): LazyList[A] =
    LazyList.unfold(???)(???)

  def zipWithViaUnfold[B, C](s2: LazyList[B])(f: (A, B) => C): LazyList[C] =
    LazyList.unfold(???)(???)

  def zipAll[B](s2: LazyList[B]): LazyList[(Option[A], Option[B])] =
    LazyList.unfold(???)(???)

  // Exercise 5.14 [Hard]
  // Implement startsWith using functions you’ve written. It should check
  // if one LazyList is a prefix of another. For instance, Stream(1,2,3) startsWith
  // Stream(1,2) would be true.
  def startsWith[A](s: LazyList[A]): Boolean =
    zipAll(s)
      .takeWhile((a, b) => (a, b)._2.isDefined) forAll:
        case (h1, h2) =>
          h1 == h2

  // Exercise 5.15
  // Implement tails using unfold. For a given LazyList, tails returns the LazyList
  // of suffixes of the input sequence, starting with the original LazyList. For
  // example, given LazyList(1,2,3), it would return LazyList(LazyList(1,2,3),
  // LazyList(2,3), LazyList(3), LazyList()).
  def tails: LazyList[LazyList[A]] =
    LazyList
      .unfold(this):
        case Empty      => None
        case Cons(h, t) => Some((Cons(h, t), t()))
      .append(LazyList(LazyList.empty))

  def hasSubsequence[A](l: LazyList[A]): Boolean =
    tails.exists(_.startsWith(l))

  // Exercise 5.16
  // Hard: Generalize tails to the function scanRight, which is like a foldRight
  // that returns a stream of the intermediate results. For example:
  // scala> Stream(1,2,3).scanRight(0)(_ + _).toList
  // res0: List[Int] = List(6,5,3,0)
  // This example should be equivalent to the expression
  // List(1+2+3+0, 2+3+0, 3+0, 0).

  // def scanRight[B](z: B)(f: (A, => B) => B): LazyList[B] =
  //   foldRight((z, LazyList(z))): (a, acc) =>
  //     lazy val b = f(a, acc._1)
  //     (b, LazyList.cons(b, acc._2))

  def scanRight[B](z: B)(f: (A, => B) => B): LazyList[B] =
    foldRight(z -> LazyList(z)): (a, acc) =>
      lazy val temp = acc
      val newResult = f(a, temp(0))
      (newResult, LazyList.cons(newResult, temp(1)))
    .apply(1)

object LazyList:
  /*
   * Smart constrcutors: cons and empty
   */
  def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] =
    lazy val head = hd // memoize
    lazy val tail = tl
    Cons(() => head, () => tail)

  def empty[A]: LazyList[A] = Empty

  def apply[A](as: A*): LazyList[A] =
    if as.isEmpty then empty
    else cons(as.head, apply(as.tail*))

  /*
   * Infinite lazy lists and corecursion
   */

  val ones: LazyList[Int] = cons(1, ones)

  // Exercise 5.8
  // Generalize ones slightly to the function constant, which returns an infinite
  // LazyList of a given value.
  def continually[A](a: A): LazyList[A] = ???

  // Exercise 5.9
  // Write a function that generates an infinite stream of integers, starting from n,
  // then n + 1, n + 2, and so on.
  def from(n: Int): LazyList[Int] = ???

  // Exercise 5.10
  // Write a function fibs that generates the infinite stream of Fibonacci numbers:
  // 0, 1, 1, 2, 3, 5, 8, and so on.
  def fibs: LazyList[Int] = ???

  // Exercise 5.11: Coecursive Function
  // Write a more general stream-building function called unfold. It takes an initial
  // state, and a function for producing both the next state and the next value in
  // the generated stream.
  // The unfold function is an example of what’s sometimes called a corecursive
  // function. Whereas a recursive function consumes data, a corecursive function
  // produces data.
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): LazyList[A] =
    f(z) match
      case Some((a, s)) => ???
      case None         => ???

  // Exercise 5.12
  // Write fibs, from, constant, and ones in terms of unfold.
  def fibsViaUnfold: LazyList[Int] =
    unfold(???)(???)

  def fromViaUnfold(n: Int): LazyList[Int] =
    unfold(???)(???)

  def continuallyViaUnfold[A](a: A): LazyList[A] =
    unfold(???)(???)

  def onesViaUnfold: LazyList[Int] =
    unfold(???)(???)
