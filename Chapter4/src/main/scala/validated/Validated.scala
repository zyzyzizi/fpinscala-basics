package fpinscala.chapter4.datatypes.validated
import fpinscala.chapter4.datatypes.either.Either
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
  def map[B](f: A => B): Validated[E, B] = ???

  def map2[E, B, C](b: Validated[E, B])(
      f: (A, B) => C
  ): Validated[E, C] = ???

  def flatMap[E, B](f: A => Validated[E, B]): Validated[E, B] = ???

  def orElse[E, A](f: => Validated[E, A]): Validated[E, A] = ???

  def toEither: Either[List[E], A] = this match
    case Valid(a)    => Right(a)
    case Invalid(es) => Left(es)

object Validated:
  def fromEither[E, A](e: Either[List[E], A]): Validated[E, A] =
    e match
      case Either.Right(a) => Valid(a)
      case Either.Left(es) => Invalid(es)

  def traverse[E, A, B](
      as: List[A],
      f: A => Validated[E, B]
  ): Validated[E, List[B]] = ???

  def sequence[E, A](vs: List[Validated[E, A]]): Validated[E, List[A]] = ???
