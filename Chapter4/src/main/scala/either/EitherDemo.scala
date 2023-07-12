package fpinscala.chapter4.datatypes.either
import fpinscala.utils.Utils.*
import Either.*

object Eitherdemo1 extends App:
  import scala.util.control.NonFatal

  def mean(xs: Seq[Double]): Either[String, Double] =
    if xs.isEmpty then Left("mean of empty list!")
    else Right(xs.sum / xs.length)

  // Write in terms of catchNonFatal
  def safeDiv(x: Int, y: Int): Either[Throwable, Int] =
    try Right(x / y)
    catch case NonFatal(e) => Left(e)

end Eitherdemo1

object EitherDemo2 extends App:

  /** Top secret formula for computing an annual car insurance premium from two
    * key factors.
    */
  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double =
    (age * numberOfSpeedingTickets) / 2.0

  def parseInsuranceRateQuote(
      age: String,
      numberOfSpeedingTickets: String
  ): Either[Throwable, Double] =
    for
      a <- Either.catchNonFatal(age.toInt)
      tickets <- Either.catchNonFatal(numberOfSpeedingTickets.toInt)
    yield insuranceRateQuote(a, tickets)

end EitherDemo2

object EitherDemo3 /* extends App */:

  // Exercise 4.8
  // In this implementation, map2 is only able to report one error, even if both
  // the name and the age are invalid. What would you need to change in order to
  // report both errors? Would you change map2 or the signature of mkPerson? Or
  // could you create a new data type that captures this requirement better than
  // Either does, with some additional structure? How would orElse, traverse, and
  // sequence behave differently for that data type?

  delim()
  label("Both invald, but only one error reported")
  var p1 = Person.make("", -1)
  println(p1)
  delim()

  label("Both invald, both errors reported")
  val p2 = Person.makeBoth("", -1)
  println(p2)
  delim()

  label(
    "Each successive use of map2Both adds another layer of List to the error type"
  )
  val p3 = Person.makeBoth("", 34)
  val p4 = Person.makeBoth("", -1)
  val pair: Either[List[List[String]], (Person, Person)] =
    map2Both(p3, p4, (_, _))
  println(pair)
  delim()

  label("map2All flattens the error type")
  val pair2 = map2All(p3, p4, (_, _))
  println(pair2)
  delim()

end EitherDemo3

case class Name private (value: String)
object Name:
  def apply(name: String): Either[String, Name] =
    if name == null || name.isEmpty then Left("Name is empty")
    else Right(new Name(name))

case class Age private (value: Int)
object Age:
  def apply(age: Int): Either[String, Age] =
    if age < 0 then Left("Age is out of range")
    else Right(new Age(age))

case class Person(name: Name, age: Age)
object Person:
  def make(name: String, age: Int): Either[String, Person] =
    Name(name).map2(Age(age))(Person(_, _))

  def makeBoth(name: String, age: Int): Either[List[String], Person] =
    map2Both(Name(name), Age(age), Person(_, _))
