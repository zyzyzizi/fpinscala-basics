package fpinscala.basics.expression_problem.oo

abstract class Expr {
  def stringify: String
  def eval: Double
}

case class Literal(value: Double) extends Expr {
  override def stringify: String = value.toString
  override def eval: Double = value
}

case class Add(lhs: Expr, rhs: Expr) extends Expr {
  override def stringify: String =
    "(" + lhs.stringify ++ " + " ++ rhs.stringify + ")"
  override def eval: Double = lhs.eval + rhs.eval
}

case class Mul(lhs: Expr, rhs: Expr) extends Expr {
  override def stringify: String =
    "(" + lhs.stringify ++ " * " ++ rhs.stringify + ")"
  override def eval: Double = lhs.eval * rhs.eval
}

object OODemo /* extends App */ {
  import fpinscala.utils.Utils.*

  delim()
  val expr1 = Add(Literal(100), Literal(200))
  println(expr1.stringify)
  println(expr1.eval)
  delim()

  val expr2 = Mul(
    Add(Literal(1), Literal(2)),
    Add(Literal(4), Literal(5))
  )
  println(expr2.stringify)
  println(expr2.eval)
  delim()
}
