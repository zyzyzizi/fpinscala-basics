package fpinscala.datatypes

enum Option[+A]:
  case Some(get: A)
  case None

  // Exercise 4.1
  // It’s fine to use pattern matching, though you should be able to
  // implement all the functions besides map and getOrElse without
  // resorting to pattern matching. Try implementing flatMap, orElse, and
  // filter in terms of map and getOrElse.
  def map[B](f: A => B): Option[B] = ???

  def getOrElse[A](default: => A): A = ???

  def flatMap[B](f: A => Option[B]): Option[B] = ???

  // Implement via map and getOrElse.
  def orElse[A](ob: => Option[A]): Option[A] = ???

  def filter(f: A => Boolean): Option[A] = ???

  // Exercise 4.3
  // Write a generic function map2 that combines two Option values using a binary
  // function. If either Option value is None, then the return value is too.
  def map2[B, C](ob: Option[B])(f: (A, B) => C): Option[C] = ???

object Option:
  def lift[A, B](f: A => B): Option[A] => Option[B] = ???

  // Exercise 4.4
  // Write a function sequence that combines a list of Options into one Option
  // containing a list of all the Some values in the original list. If the
  // original list contains None even once, the result of the function should be
  // None; otherwise the result should be Some with a list of all the values.
  def sequence[A](as: List[Option[A]]): Option[List[A]] = ???

  // Exercise 4.5
  // Implement this function. It’s straightforward to do using map and sequence,
  // but try for a more efficient implementation that only looks at the list once.
  def traverse[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] = ???

  def sequenceViaTraverse[A](as: List[Option[A]]): Option[List[A]] = ???
