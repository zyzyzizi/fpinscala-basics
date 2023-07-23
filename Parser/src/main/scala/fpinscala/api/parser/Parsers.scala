package fpinscala.api.parser

import java.util.regex.*
import scala.util.matching.Regex

trait Parsers[Parser[+_]]:
  type ParseResult[+A]

  // Law: string(s).run(s) == Right(s)
  def string(s: String): Parser[String]

  // Law: char(c).run(c.toString) == Right(c)
  def char(c: Char): Parser[Char] =
    string(c.toString).map(_.charAt(0))

  // Law: succeed(a).run("") == Right(a)
  def succeed[A](a: A): Parser[A]

  def regex(r: Regex): Parser[String]

  /** Parser which consumes zero or more whitespace characters. */
  def whitespace: Parser[String] = regex("\\s*".r)

  /** Parser which consumes 1 digit. */
  def digit: Parser[String] = regex("\\d".r)

  /** Parser which consumes 1 or more digits. */
  def digits: Parser[String] = regex("\\d+".r)

  extension [A](p: Parser[A])
    def run(input: String): ParseResult[A]

    // string("abra").or(string("cadabra")).run("abra") == Right("abra")
    // string("abra").or(string("cadabra")).run("cadabra") == Right("cadabra")
    infix def or(p2: => Parser[A]): Parser[A]
    def |(p2: Parser[A]): Parser[A] = p.or(p2)

    // Exercise 9.4
    // Hard: Using map2 and succeed, implement the listOfN combinator from earlier.
    def listOfN(n: Int): Parser[List[A]] = ???

    // Structure preverving: map(p)(a => a) == p
    // Exercise 9.6
    // Implement product and map2 in terms of flatMap.
    def map[B](f: A => B): Parser[B] = ???

    infix def product[B](p2: => Parser[B]): Parser[(A, B)] = ???
    def **[B](p2: => Parser[B]): Parser[(A, B)] = product(p2)

    def map2[B, C](p2: => Parser[B])(f: (A, B) => C): Parser[C] = ???

    // Exercise 9.3 [Hard]
    // Before continuing, see if you can define many in terms of or, map2, and succeed.
    // zero or more
    def many: Parser[List[A]] = ???

    // one or more
    // p.many1 is just p followed by p.many.
    def many1: Parser[List[A]] = ???

    def flatMap[B](f: A => Parser[B]): Parser[B]

    /** Sequences two parsers, ignoring the result of the first.
      */
    def *>[B](p2: => Parser[B]) = ???

    /** Sequences two parsers, ignoring the result of the second.
      */
    def <*(p2: => Parser[Any]) = ???

end Parsers
