package fpinscala.datatypes
import fpinscala.utils.Utils.*
import Option.*

private def toIntOption(s: String): Option[Int] =
  try Some(s.toInt)
  catch case _: NumberFormatException => None

object OptionDemo1 /* extends App */:

  case class Employee(
      name: String,
      department: String,
      manager: Option[String] = None
  )

  def lookupByName(name: String): Option[Employee] =
    name match
      case "Joe"   => Some(Employee("Joe", "IT", Some("Suzan")))
      case "Mary"  => Some(Employee("Mary", "Finance", None))
      case "Suzan" => Some(Employee("Suzan", "IT", None))
      case _       => None

  // delim()
  // println(lookupByName("Joe").map(_.department))
  // println(lookupByName("Taddy").map(_.department).getOrElse("Default"))
  // println(lookupByName("Taddy").map(_.department).orElse(Some("Default")))
  // delim()

  // println(lookupByName("Joe").flatMap(_.manager))
  // println(lookupByName("Mary").flatMap(_.manager))
  // delim()

  // val dept: String = lookupByName("Mark")
  //   .map(_.department)
  //   .filter(_ != "Accounting")
  //   .getOrElse("Default Dept")
  // println(dept)
  // delim()

end OptionDemo1

object OptionDemo2 /* extends App */:

  def mean(xs: Seq[Double]): Option[Double] =
    if xs.isEmpty then None
    else Some(xs.sum / xs.length)

  // Exercise 4.2
  // Implement the variance function in terms of flatMap. If the mean of a sequence
  // is m, the variance is the mean of math.pow(x - m, 2) for each element x in the
  // sequence. See the definition of variance on Wikipedia (http://mng.bz/0Qsr).
  def variance(xs: Seq[Double]): Option[Double] = ???

  delim()
  println(variance(Seq(1, 2, 3, 4, 5)))
  delim()

  // // Inefficient implementation: travere the list twice.
  // def parseIntsInefficient[A](as: List[String]): Option[List[Int]] = ???

  // def parseInts[A](as: List[String]): Option[List[Int]] = ???

  // println(parseInts(List("1", "323", "77")))
  // delim()
end OptionDemo2

object OptionDemo3_Lifting /* extends App */:

  delim()
  val absOpt: Option[Double] => Option[Double] = lift(math.abs)
  println(absOpt(Some(-1.0)))
  delim()

  /** Top secret formula for computing an annual car insurance premium from two
    * key factors.
    */
  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double =
    (age * numberOfSpeedingTickets) / 2.0

  // Does not compile!
  // def parseInsuranceRateQuote(
  //     age: String,
  //     numberOfSpeedingTickets: String
  // ): Option[Double] =
  //   val optAge = toIntOption(age)
  //   val optTickets = toIntOption(numberOfSpeedingTickets)
  //   insuranceRateQuote(optAge, optTickets)

  def parseInsuranceRateQuote(
      age: String,
      numberOfSpeedingTickets: String
  ): Option[Double] =
    val optAge = toIntOption(age)
    val optTickets = toIntOption(numberOfSpeedingTickets)
    ??? // Use map2 to lift insuranceRateQuote

end OptionDemo3_Lifting

object OptionDemo4 /* extends App */:

  // Inefficient implementation: travere the list twice.
  def parseIntsInefficient[A](as: List[String]): Option[List[Int]] =
    sequence(as.map(toIntOption))

  def parseInts[A](as: List[String]): Option[List[Int]] =
    traverse(as)(toIntOption)

  delim()
//   println(parseInts(List("1", "323", "abc", "77")))
  println(parseInts(List("1", "323", "77")))
  delim()

end OptionDemo4
