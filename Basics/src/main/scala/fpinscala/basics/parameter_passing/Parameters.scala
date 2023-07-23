package fpinscala.basics.parameter_passing

import fpinscala.utils.Utils.*

/*
 * (λx.x + x)((λy.y * y)(5))
 */

object Call_by_Value /* extends App */:

  def funcY(y: Int) = { println("funcY"); y * y }
  def funcX(x: Int) = x + x

  label("Call by Value")
  println(
    funcX(funcY(5))
  )

end Call_by_Value

object Call_by_Name /* extends App */:

  def funcY(y: Int) = { println("funcY"); y * y }
  def funcX(x: => Int) = x + x

  label("Call by Name")
  println(
    funcX(funcY(5))
  )

end Call_by_Name

object Call_by_Need /* extends App */:

  def funcY(y: Int) = { println("funcY"); y * y }

  def funcX(x: => Int) =
    lazy val k = x
    k + k

  label("Call by Need")
  println(
    funcX(funcY(5))
  )

end Call_by_Need
