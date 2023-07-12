package fpinscala.chapter6.datatypes.state

// Option1:
// type State[S, +A] = S => (A, S)

// Option 2:
// case class State[S, +A](run: S => (A, S))

// Option 3
// At the cost of some boilerplate conversions, we get both encapsulation and performance.
opaque type State[S, +A] = S => (A, S)

object State:
  extension [S, A](underlying: State[S, A])
    def run(s: S): (A, S) = ???

    def map[B](f: A => B): State[S, B] = ???

    def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = ???

    def flatMap[B](f: A => State[S, B]): State[S, B] = ???

    def map2ViaFlatMap[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = ???

  def apply[S, A](f: S => (A, S)): State[S, A] = ???

  // Exercise 6.10
  // Generalize the functions unit, map, map2, flatMap, and sequence. Add them
  // as extension methods on the State type where possible. Otherwise you
  // should put them in the State companion object
  def pure[S, A](a: A): State[S, A] = ???

  def get[S]: State[S, S] = ???

  def set[S](s: S): State[S, Unit] = ???

  def modify[S](f: S => S): State[S, Unit] = ???

  def sequence[S, A](fs: List[State[S, A]]): State[S, List[A]] = ???

  def traverse[S, A, B](as: List[A])(f: A => State[S, B]): State[S, List[B]] =
    ???
