package fpinscala.basics.variances

import fpinscala.utils.Utils.*

abstract class Animal(val name: String):
  override def toString(): String = s"Animal($name)"

class Dog(name: String) extends Animal(name):
  def bark: String = "woof"
  override def toString(): String = s"Dog($name)"

class Cat(name: String) extends Animal(name):
  def moew: String = "meow"
  override def toString(): String = s"Cat($name)"

/*
 * Invariance: F[A] & F[B] has nothing to do with A & B
 */
object InvarianceDemo /* extends App */:

  class Box[A](value: A):
    private var _value: A = value

    def get: A = _value
    def set(value: A): Unit = _value = value
    override def toString: String = s"Box($_value)"

  delim()
  var dogBox: Box[Dog] = Box(Dog("Fido"))
  println(dogBox.get)
  delim()

  dogBox.set(Dog("Rover"))
  println(dogBox.get)
  delim()

  // Try to assign a Box[Cat] to a Box[Dog]
  // dogBox = Box(Cat("Felix"))

  // Try to assign a Box[Dog] to a Box[Animal]
  // var animalBox: Box[Animal] = dogBox

  // Try to assign a Box[Animal] to a Box[Dog]
  // val animalBox: Box[Animal] = Box[Animal](Cat("Felix"))
  // dogBox = animalBox

end InvarianceDemo

/*
 * Covariance: A <: B => F[A] <: F[B]
 * OUT
 */
object CovarianceDemo /* extends App */:

  class Box[+A](value: A):
    private var _value: A = value

    def get: A = _value
    // def set(value: A): Unit = _value = value
    override def toString: String = s"Box($_value)"

  delim()
  var dogBox: Box[Dog] = new Box(Dog("Fido"))
  println(dogBox.get)
  delim()

  // Try to assign a Box[Cat] to a Box[Dog]
  // dogBox = Box(Cat("Felix"))

  // Try to assign a Box[Dog] to a Box[Animal]
  var animalBox: Box[Animal] = dogBox

  // Try to assign a Box[Animal] to a Box[Dog]
  animalBox = Box(Cat("Felix"))
  // dogBox = animalBox

end CovarianceDemo

/*
 * Contravariance: A <: B => F[B] <: F[A]
 * IN
 */
object ContravarianceDemo /* extends App */:

  class Box[-A](value: A):
    private var _value: A = value
    // def get: A = _value
    def set(value: A): Unit = _value = value
    override def toString: String = s"Box($_value)"

  delim()
  var dogBox: Box[Dog] = new Box(Dog("Fido"))
  println(dogBox)
  delim()

  dogBox.set(Dog("Rover"))
  println(dogBox)
  delim()

  // Try to assign a Box[Cat] to a Box[Dog]
  dogBox = Box(Cat("Felix")) // What happens here???

  // Try to assign a Box[Dog] to a Box[Animal]
  // var animalBox: Box[Animal] = dogBox

  val animalBox: Box[Animal] = Box[Animal](Cat("Felix"))
  dogBox = animalBox

end ContravarianceDemo

/*
 * Function1[-A, +B]
 *
 * if R <: S and T <: U => (S => T) <: (R => U)
 */
object FunctionDemo /* extends App */:

  def func1(m: Animal => Animal) = ???
  def func2(m: Dog => Animal) = ???
  def func3(m: Animal => Dog) = ???

  val f: Animal => Animal = ???
  val g: Dog => Animal = ???
  val h: Animal => Dog = ???

  func1(f) // Animal => Animal <: Animal => Animal (Yes, No)
  // func1(g) // Dog    => Animal <: Animal => Animal (Yes, No)
  func1(h) // Animal => Dog    <: Animal => Animal (Yes, No)

  func2(f) // Animal => Animal <: Dog    => Animal (Yes, No)
  func2(g) // Dog    => Animal <: Dog    => Animal (Yes, No)
  func2(h) // Animal => Dog    <: Dog    => Animal (Yes, No)

  // func3(f) // Animal => Animal <: Animal => Dog    (Yes, No)
  // func3(g) // Dog    => Animal <: Animal => Dog    (Yes, No)
  func3(h) // Animal => Dog    <: Animal => Dog    (Yes, No)

end FunctionDemo
