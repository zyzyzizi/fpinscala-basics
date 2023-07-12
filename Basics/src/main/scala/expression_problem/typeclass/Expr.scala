package fpinscala.basics.expression_problem.typeclass

// enum dowsn'work with typeclass
trait Expr
case class Literal(value: Double) extends Expr
case class Add[A <: Expr, B <: Expr](lhs: A, rhs: B) extends Expr
case class Mul[A <: Expr, B <: Expr](lhs: A, rhs: B) extends Expr

object Stringify:
//   import Expr.*

  trait Stringify[A]:
    def stringify(a: A): String

  given literalStringify: Stringify[Literal] with
    def stringify(a: Literal): String = a.value.toString

  given addStingify[A <: Expr, B <: Expr](using
      s1: Stringify[A],
      s2: Stringify[B]
  ): Stringify[Add[A, B]] with
    def stringify(expr: Add[A, B]): String =
      "(" + s1.stringify(expr.lhs) + " + " + s2.stringify(expr.rhs) + ")"

  given mulStringify[A <: Expr, B <: Expr](using
      s1: Stringify[A],
      s2: Stringify[B]
  ): Stringify[Mul[A, B]] with
    def stringify(expr: Mul[A, B]): String =
      "(" + s1.stringify(expr.lhs) + " + " + s2.stringify(expr.rhs) + ")"

end Stringify

object Eval:
//   import Expr.*

  trait Eval[A]:
    def eval(a: A): Double

  given literalEval: Eval[Literal] with
    def eval(a: Literal): Double = a.value

  given addEval[A <: Expr, B <: Expr](using
      e1: Eval[A],
      e2: Eval[B]
  ): Eval[Add[A, B]] = new:
    def eval(expr: Add[A, B]): Double =
      e1.eval(expr.lhs) + e2.eval(expr.rhs)

  given mulEval[A <: Expr, B <: Expr](using
      e1: Eval[A],
      e2: Eval[B]
  ): Eval[Mul[A, B]] = new:
    def eval(expr: Mul[A, B]): Double =
      e1.eval(expr.lhs) * e2.eval(expr.rhs)
end Eval

object ExprDemo /* extends App */:
  import fpinscala.utils.Utils.*
  import Stringify.{given, *}, Eval.{given, *}

  def expressionEvaluator[A <: Expr](expr: A)(using e: Eval[A]) = {
    e.eval(expr)
  }

  def printExpressions[A <: Expr](expr: A)(using p: Stringify[A]) = {
    p.stringify(expr)
  }

  println("\u2500" * 50)
  // (3 + 4) * 7
  val expr1 = Mul(Add(Literal(3), Literal(4)), Literal(7))
  println(expressionEvaluator(expr1))

  val expr2 = Mul(Add(Literal(3), Literal(4)), Literal(7))
  println(printExpressions(expr2))

  println("\u2500" * 50)

end ExprDemo
