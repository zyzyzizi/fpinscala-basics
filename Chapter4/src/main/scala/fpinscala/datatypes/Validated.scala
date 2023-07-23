package fpinscala.datatypes

import Either.*

enum Validated[+E, +A]:
  case Valid(get: A)
  case Invalid(errors: List[E])

  // Exercise 4.8
  // In this implementation, map2 is only able to report one error, even if both
  // the name and the age are invalid. What would you need to change in order to
  // report both errors? Would you change map2 or the signature of mkPerson? Or
  // could you create a new data type that captures this requirement better than
  // Either does, with some additional structure? How would orElse, traverse, and
  // sequence behave differently for that data type?
  def map[B](f: A => B): Validated[E, B] = this match
    case Valid(a)    => Valid(f(a))
    case Invalid(es) => Invalid(es)

  def map2[EE >: E, B, C](b: Validated[EE, B])(
      f: (A, B) => C
  ): Validated[EE, C] = (this, b) match
    case (Valid(aa), Valid(bb))       => Valid(f(aa, bb))
    case (Invalid(es), Valid(_))      => Invalid(es)
    case (Valid(_), Invalid(es))      => Invalid(es)
    case (Invalid(es1), Invalid(es2)) => Invalid(es1 ++ es2)

  def flatMap[E, B](f: A => Validated[E, B]): Validated[E, B] = ???

  def orElse[E, A](f: => Validated[E, A]): Validated[E, A] = ???

  def toEither: Either[List[E], A] = this match
    case Valid(a)    => Right(a)
    case Invalid(es) => Left(es)

object Validated:
  def fromEither[E, A](e: Either[List[E], A]): Validated[E, A] =
    e match
      case Right(a) => Valid(a)
      case Left(es) => Invalid(es)

  def traverse[E, A, B](
      as: List[A],
      f: A => Validated[E, B]
  ): Validated[E, List[B]] =
    as.foldRight(Valid(Nil): Validated[E, List[B]])((a, acc) =>
      f(a).map2(acc)(_ :: _)
    )

  def sequence[E, A](vs: List[Validated[E, A]]): Validated[E, List[A]] =
    traverse(vs, identity)

object Validatedsample:
  import Validated.{Invalid, Valid}

  case class Name private (value: String)

  object Name:
    def apply(name: String): Validated[String, Name] =
      if name == "" || name == null then Invalid(List("Name is empty."))
      else Valid(new Name(name))

  case class Age private (value: Int)
  object Age:
    def apply(age: Int): Validated[String, Age] =
      if age < 0 then Invalid(List("Age is out of range."))
      else Valid(new Age(age))

  case class Person(name: Name, age: Age)
  object Person:
    def make(name: String, age: Int): Validated[String, Person] =
      Name(name).map2(Age(age))(Person(_, _))

end Validatedsample
