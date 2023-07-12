package fpinscala.api.parser

import scala.util.matching.Regex

object SimpleParser extends Parsers[SimpleParser.Parser]:

  enum Result[+A]:
    case Success(remaining: String, result: A)
    case Failure(message: String) extends Result[Nothing]
  import Result.*

  type ParseResult[+A] = Result[A]
  type Parser[+A] = String => ParseResult[A]

  override def succeed[A](a: A): Parser[A] =
    input => Success(input, a)

  override def string(s: String): Parser[String] =
    input =>
      if input.startsWith(s) then Success(input.drop(s.length), s)
      else Failure(s"Expected: $s")

  override def regex(r: Regex): Parser[String] =
    input =>
      r.findPrefixOf(input) match
        case Some(matched) =>
          Success(input.drop(matched.length), matched)
        case None => Failure(s"Expected: $r")

  extension [A](p: Parser[A])
    override def run(input: String): ParseResult[A] =
      p(input) match
        case s @ Success(remaining, result) => s
        case f @ Failure(message)           => f

    override def flatMap[B](f: A => Parser[B]): Parser[B] =
      input =>
        p(input) match
          case Success(remaining, result) => f(result)(remaining)
          case f @ Failure(message)       => f

    override def or(p2: => Parser[A]): Parser[A] =
      input =>
        p(input) match
          case s @ Success(remaining, result) => s
          case Failure(_)                     => p2(input)

end SimpleParser
