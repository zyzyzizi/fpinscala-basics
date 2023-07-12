package fpinscala.chapter2
import fpinscala.utils.Utils.*

// A comment!
/* Another comment */
/** A documentation comment */
object MyProgram:
  def abs(n: Int): Int =
    if n < 0 then -n
    else n

  private def formatAbs(x: Int) =
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))

  /* @main */
  def printAbs: Unit =
    delim()
    println(formatAbs(-42))
    delim()

object MyProgram2 /* extends App */:
  delim()
  println("Hello from MyProgram2")
  delim()

// object MyProgram3:
//   def main(args: Array[String]): Unit = {
//     delim()
//     println("Hello from MyProgram3")
//     delim()
//   }
