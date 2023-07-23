package fpinscala.intro

import fpinscala.utils.Utils.*

object CafeDemo extends App:
  import Cafes.*

  val visa = CreditCard("Visa")
  val master = CreditCard("Master")
  val cafe = new Cafe

  val (_, c1) = cafe.buyCoffees(visa, 10)
  val (_, c2) = cafe.buyCoffees(master, 2)
  val (_, c3) = cafe.buyCoffees(visa, 5)
  val (_, c4) = cafe.buyCoffees(master, 3)

  val charges = List(c1, c2, c3, c4)
  val coalesced = cafe.coalesce(charges)

  delim()
  println(coalesced)
  delim()

end CafeDemo
