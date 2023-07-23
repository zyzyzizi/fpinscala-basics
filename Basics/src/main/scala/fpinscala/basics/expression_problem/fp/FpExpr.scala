package fpinscala.basics.expression_problem.fp

enum Expr {
  case Const(value: Double)
  case Add(lhs: Expr, rhs: Expr)
  case Mul(lhs: Expr, rhs: Expr)
}
import Expr.*

object Expr {

  def stringify(expr: Expr): String = expr match
    case Const(value) => value.toString
    case Add(lhs, rhs) =>
      "(" ++ stringify(lhs) ++ " + " ++ stringify(rhs) ++ ")"
    case Mul(lhs, rhs) =>
      "(" ++ stringify(lhs) ++ " * " ++ stringify(rhs) ++ ")"

  def eval(expr: Expr): Double = expr match
    case Const(value)  => value
    case Add(lhs, rhs) => eval(lhs) + eval(rhs)
    case Mul(lhs, rhs) => eval(lhs) * eval(rhs)
}

object FpDemo /* extends App */ {
  import fpinscala.utils.Utils.*
  import Expr.*

  delim()
  val expr1 = Add(Const(100), Const(200))
  println(stringify(expr1))
  println(eval(expr1))
  delim()

  val expr2 = Mul(Add(Const(1), Const(2)), Add(Const(3), Const(4)))
  println(stringify(expr2))
  println(eval(expr2))
  delim()
}
