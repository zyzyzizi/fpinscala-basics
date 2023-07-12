// import munit.FunSuite

import pprint.*
import fpinscala.chapter4.datatypes.validated.*
import fpinscala.chapter4.datatypes.either.*
import Validated.*, Either.*

class ValidatedTest extends munit.FunSuite:

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

  test("validated demo1".only) {
    println("\u2500" * 50)
    val p1 = Person.make("Curry", 34)
    println(p1)
    val p2 = Person.make("Haskell", 20)
    println(p2)
    println("\u2500" * 50)

    val p3 = Person.make("", 50)
    println(p3)
    val p4 = Person.make("Joerge", -1)
    println(p4)
    val p5 = Person.make("", -1)
    println(p5)
    println("\u2500" * 50)
  }
