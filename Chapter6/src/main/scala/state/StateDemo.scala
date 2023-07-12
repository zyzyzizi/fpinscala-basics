package fpinscala.chapter6.datatypes.state
import fpinscala.utils.Utils.*

object StateDemo1 /* extends App */:
  import fpinscala.chapter6.rng.*, RNG.*
  import State.*

  type Rand[A] = State[RNG, A]

  delim()
  val int: Rand[Int] = State(RNG.int)
  val double: Rand[Double] = State(RNG.double)
  val nonNegativeInt: Rand[Int] = State(RNG.nonNegativeInt)
  val nonNegativeEven: Rand[Int] = nonNegativeInt.map(i => i - (i % 2))

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] =
    ra.map2(rb)((_, _))

  def intDouble: Rand[(Int, Double)] = both(int, double)
  def doubleInt: Rand[(Double, Int)] = both(double, int)
  def double3: Rand[(Double, Double, Double)] = State(RNG.double3)
  def ints(count: Int): Rand[List[Int]] = State(RNG.ints(count))

  def nonNegativeLessThan(n: Int): Rand[Int] =
    nonNegativeInt.flatMap: i =>
      val mod = i % n
      if i + (n - 1) - mod >= 0 then pure(mod)
      else nonNegativeLessThan(n)

  delim()
  label("int"); println(int.run(SimpleRNG(42)))
  label("double"); println(double.run(SimpleRNG(42)))
  label("nonNegativeInt"); println(nonNegativeInt.run(SimpleRNG(42)))
  label("nonNegativeEven"); println(nonNegativeEven.run(SimpleRNG(42)))
  label("intDouble"); println(intDouble.run(SimpleRNG(42)))
  label("doubleInt"); println(doubleInt.run(SimpleRNG(42)))
  label("double3"); println(double3.run(SimpleRNG(42)))
  label("ints"); println(ints(5).run(SimpleRNG(42)))
  label("nonNegativeLessThan");
  println(nonNegativeLessThan(10).run(SimpleRNG(42)))

  delim()

end StateDemo1

object StateDemo2 extends App:
  import pprint.*

  def words(input: String): List[String] =
    input.split("\\W+").toList.filterNot(_.isEmpty)

  val input1 =
    "this is a sample string for exam questions"
  val input2 =
    "that is a sample test string"

  def imperativeStyle(): Unit =
    def wordCounts(str: String, currMap: Map[String, Int]): Map[String, Int] = {
      words(str).foldLeft(currMap) { (map, word) =>
        val count = map.getOrElse(word, 0) + 1
        map + (word -> count)
      }
    }

    val map1 = wordCounts(input1, Map.empty)
    val map2 = wordCounts(input2, map1)
    pprintln(map2)

  end imperativeStyle

  def functionalStyle(): Unit =
    import fpinscala.chapter6.datatypes.state.State
    import State.*

    type WordMap[+A] = State[Map[String, Int], A]

    def update(input: String): Map[String, Int] => Map[String, Int] =
      (cmap: Map[String, Int]) =>
        words(input).foldLeft(cmap): (map, word) =>
          val count = map.getOrElse(word, 0) + 1
          map + (word -> count)

    def wordCounts(str: String): WordMap[Unit] = for
      currMap <- get[Map[String, Int]]
      _ <- set(update(str)(currMap))
    yield ()

    val m = for
      _ <- wordCounts(input1)
      _ <- wordCounts(input2)
    yield ()

    val (_, wordMap) = m.run(Map.empty[String, Int])
    pprintln(wordMap)
  end functionalStyle

  imperativeStyle()
  functionalStyle()

end StateDemo2

/** Exercise 6.11 (opti onal) Hard: To gain experience with the use of State,
  * implement a finite state automaton that models a simple candy dispenser. The
  * machine has two types of input: you can insert a coin, or you can turn the
  * knob to dispense candy. It can be in one of two states: locked or unlocked.
  * It also tracks how many candies are left and how many coins it contains.
  *
  * The rules of the machine are as follows:
  *   - Inserting a coin into a locked machine will cause it to unlock if
  *     there’s any candy left.
  *   - Turning the knob on an unlocked machine will cause it to dispense candy
  *     and become locked.
  *   - Turning the knob on a locked machine or inserting a coin into an
  *     unlocked machine does nothing.
  *   - A machine that’s out of candy ignores all inputs.
  *
  * The method `simulateMachine` should operate the machine based on the list of
  * inputs and return the number of coins and candies left in the machine at the
  * end. For example, if the input Machine has 10 coins and 5 candies, and a
  * total of 4 candies are successfully bought, the output should be (14, 1).
  */
import State.*

enum Input:
  case Coin, Turn

case class Machine(locked: Boolean, candies: Int, coins: Int)

def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] =
  import State.*
  import Input.*

  def update(i: Input)(s: Machine): Machine =
    (i, s) match
      case (_, Machine(_, 0, _)) => s // if no cady, do nothing
      case (Coin, Machine(false, _, _)) =>
        s // if unlocked, do nothing when coin inserted
      case (Turn, Machine(true, _, _)) =>
        s // if locked, do nothing when knob turned
      case (Coin, Machine(true, candy, coin)) => Machine(false, candy, coin + 1)
      case (Turn, Machine(false, candy, coin)) => Machine(true, candy - 1, coin)

  for
    // _ <- sequence(inputs.map(modify[Machine] compose update))
    _ <- traverse(inputs)(modify[Machine] compose update)
    s <- get
  yield (s.coins, s.candies)

object MachineDemo /* extends App */ {
  import Input.*

  val inputs = List(Coin, Turn, Coin, Coin, Turn, Turn, Turn)
  val initalState = Machine(true, 10, 5)

  println(
    simulateMachine(inputs).run(initalState)
  )
}
