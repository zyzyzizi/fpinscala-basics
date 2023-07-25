package fpinscala.basics.parameter_passing

import fpinscala.utils.Utils.*

/*
 * (λx.x + x)((λy.y * y)(5))
 */

object Call_by_Value extends App:

  def funcY(y: Int) = { println("funcY"); y * y }
  def funcX(x: Int) = { println("funcX"); x + x }

  delim()
  label("Call by Value") // AOR
  println(
    funcX(funcY(5))
  )
  delim()

end Call_by_Value

object Call_by_Name extends App:

  def funcY(y: Int) = { println("funcY"); y * y }
  def funcX(x: => Int) = { println("funcX"); x + x }

  delim()
  label("Call by Name")
  println(
    funcX(funcY(5)) // NOR
  )
  delim()

end Call_by_Name

object Call_by_Need extends App:

  def funcY(y: Int) = { println("funcY"); y * y }

  def funcX(x: => Int) =
    println("funcX")
    lazy val k =
      x // still delay .. and evaluate and cache the value when first invoked
    k + k

  delim()
  label("Call by Need")
  println(
    funcX(funcY(5))
  )
  delim()

end Call_by_Need
