package fpinscala.basics.for_comprehension

import fpinscala.utils.Utils.*
import pprint.*

case class Person(name: String, age: Int)

val persons =
  List(Person("John", 33), Person("Stacy", 15), Person("Tiger", 55))

case class Department(title: String, chair: String)

val departments = List(
  Department("Computer Science", "John"),
  Department("Mathematics", "Stacy"),
  Department("Physics", "Tiger")
)

object For_Comprehension1 /* extends App */:
  delim()

  val names =
    for person <- persons
    yield person.name

  println(names)
  delim()

end For_Comprehension1

object For_Comprehension2 /* extends App */:
  delim()

  val result = for
    person <- persons
    department <- departments
  yield (person.name, department.title)

  pprintln(result)
  delim()

end For_Comprehension2

object For_Comprehension3 /* extends App */:
  delim()

  val result = for
    department <- departments
    person <- persons if person.name == department.chair
  yield (department.title, person.age)

  pprintln(result)
  delim()

end For_Comprehension3

object For_Loop /* extends App */:

  val as = List(1, 2, 3)
  val bs = List("a", "b", "c")

  delim()
  for x <- as
  do println(x)
  delim()

  as.foreach(x => println(x))
  delim()

  for (x, y) <- as.zip(bs)
  do println(s"$x -> $y")
  delim()

end For_Loop
