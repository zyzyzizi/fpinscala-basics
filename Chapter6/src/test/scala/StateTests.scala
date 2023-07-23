import pprint.*
import fpinscala.utils.Utils.*
import fpinscala.datatypes.*
import State.*

class StateTest1 extends munit.FunSuite:
  type Stack[A] = List[A]

  test("state demo1".ignore) {

    def push[A](a: A): State[Stack[A], Unit] = State { xs =>
      ((), a :: xs)
    }

    def push2[A](a: A): State[Stack[A], Unit] =
      modify(s => a :: s)

    def push3[A](a: A): State[Stack[A], Unit] = for
      xs <- get[Stack[A]]
      _ <- set(a :: xs)
    yield ()

    def pop[A]: State[Stack[A], A] = State {
      _ match
        case Nil    => sys.error("stack is empty")
        case h :: t => (h, t)
    }

    def pop2[A]: State[Stack[A], A] =
      for
        xs <- get[Stack[A]]
        _ <- set(xs.tail)
      yield xs.head

    delim()

    val stackManip: State[Stack[Int], Unit] = for
      _ <- push(1)
      _ <- push(2)
      _ <- push(3)
    yield ()

    println(stackManip.run(List.empty[Int]))

    val stackManip2: State[Stack[Int], Int] = for
      _ <- push(1)
      _ <- push(2)
      _ <- push(3)
      a <- pop
      b <- pop
    yield b

    println(stackManip2.run(List.empty[Int]))
    delim()
  }

  test("state demo2".only) {
    delim()

    pprintln(pure(7).run(10))
    pprintln(pure(7).map(_ + 10).run(10))
    pprintln(
      pure(7)
        .flatMap { a =>
          pure(a * a)
        }
        .run(10)
    )
    delim()

    def zipWithIndex[A](as: List[A], start: Int = 0): List[(A, Int)] =
      traverse(as) { a =>
        State((s: Int) => ((a, s), s + 1))
      }.run(start)._1

    pprintln(zipWithIndex(List("a", "b", "c", "d")))
    delim()
  }
