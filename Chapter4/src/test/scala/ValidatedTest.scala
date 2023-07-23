// import munit.FunSuite

import pprint.*
import fpinscala.datatypes.{Either, Validated}
import Validated.*, Either.*

class ValidatedTest extends munit.FunSuite:

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
