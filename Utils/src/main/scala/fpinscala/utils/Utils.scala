package fpinscala.utils

object Utils:

  def delim(length: Int = 50): Unit = println("\u2500" * length)

  def label(title: String, color: String = Console.CYAN): Unit =
    println(color ++ title ++ Console.RESET)

end Utils
