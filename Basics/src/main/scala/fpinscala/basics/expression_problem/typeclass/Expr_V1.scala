package fpinscala.basics.expression_problem.typeclass2

import fpinscala.utils.Utils.*

case class Literal(value: Double)
case class Add[A, B](lhs: A, rhs: B)
case class Mul[A, B](lhs: A, rhs: B)

trait Stringify[A]:
  def stringify(a: A): String

object Stringify:

  given literalStringify: Stringify[Literal] with
    def stringify(a: Literal): String = a.value.toString

  given addStingify[A, B](using
      s1: Stringify[A],
      s2: Stringify[B]
  ): Stringify[Add[A, B]] with
    def stringify(expr: Add[A, B]): String =
      "(" + s1.stringify(expr.lhs) + " + " + s2.stringify(expr.rhs) + ")"

  given mulStringify[A, B](using
      s1: Stringify[A],
      s2: Stringify[B]
  ): Stringify[Mul[A, B]] with
    def stringify(expr: Mul[A, B]): String =
      "(" + s1.stringify(expr.lhs) + " * " + s2.stringify(expr.rhs) + ")"

end Stringify

trait Eval[A]:
  def eval(a: A): Double

object Eval:

  given literalEval: Eval[Literal] with
    def eval(a: Literal): Double = a.value

  given addEval[A, B](using e1: Eval[A], e2: Eval[B]): Eval[Add[A, B]] = new:
    def eval(expr: Add[A, B]): Double =
      e1.eval(expr.lhs) + e2.eval(expr.rhs)

  given mulEval[A, B](using
      e1: Eval[A],
      e2: Eval[B]
  ): Eval[Mul[A, B]] = new:
    def eval(expr: Mul[A, B]): Double =
      e1.eval(expr.lhs) * e2.eval(expr.rhs)

end Eval

object ExprDemo /* extends App */:
  import fpinscala.utils.Utils.*
  import Stringify.{given, *}, Eval.{given, *}

  def printExpressions[A](expr: A)(using p: Stringify[A]) = {
    p.stringify(expr)
  }

  def expressionEvaluator[A](expr: A)(using e: Eval[A]) = {
    e.eval(expr)
  }

  delim()
  // (3 + 4) * 7
  val expr2 = Mul(Add(Literal(3), Literal(4)), Literal(7))
  println(printExpressions(expr2))

  val expr1 = Mul(Add(Literal(3), Literal(4)), Literal(7))
  println(expressionEvaluator(expr1))

  delim()

end ExprDemo
