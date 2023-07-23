package fpinscala.intro

object Cafes:

  case class CreditCard(val kind: String)

  class Coffee:
    var price = 10.0

  case class Charge(cc: CreditCard, amount: Double):
    def combine(other: Charge): Charge =
      if cc == other.cc then Charge(cc, amount + other.amount)
      else throw new Exception("Can't combine charges to different cards")

  class Cafe:
    def buyCoffee(cc: CreditCard): (Coffee, Charge) =
      val cup = new Coffee()
      (cup, Charge(cc, cup.price))

    def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) =
      val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
      val (coffees, charges) = purchases.unzip
      (coffees, charges.reduce((c1, c2) => c1.combine(c2)))

    def coalesce(charges: List[Charge]): List[Charge] =
      charges
        .groupBy(_.cc)
        .values
        .map(_.reduce(_ combine _))
        .toList

object Demo:
  import Cafes.*

  def main(args: Array[String]): Unit =
    val visa = CreditCard("Visa")
    val master = CreditCard("Master")
    val cafe = new Cafe

    val (_, c1) = cafe.buyCoffees(visa, 10)
    val (_, c2) = cafe.buyCoffees(master, 2)
    val (_, c3) = cafe.buyCoffees(visa, 5)
    val (_, c4) = cafe.buyCoffees(master, 3)

    val charges = List(c1, c2, c3, c4)
    val coalesced = cafe.coalesce(charges)
    println(coalesced)
