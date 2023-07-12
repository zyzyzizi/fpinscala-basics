package fpinscala.api.parser

object ParserDemo extends App:
  import fpinscala.utils.Utils.*
  import SimpleParser.{given, *}

  delim()
  println(char('a')("abc"))
  delim()

  println(string("ab")("abc"))
  delim()

  // println(regex("\\d+".r)("123abc"))
  // delim()

  // println((string("ab") | string("cad"))("abracadabra"))
  // delim()

  // label("[basics]")
  // println(string("a")("abra"))
  // println(succeed("alpha")("hello"))
  // println(flatMap(string("a"))(a => succeed(a + a))("abra"))
  // println(char('a')("abraca"))
  // println((char('a') | char('c'))("dbraca"))
  // println((char('a') | char('c'))("cbraca"))
  // delim()

  // label("[many]")
  // println(string("a").many("aaab"))
  // println(string("a").many("aaba"))
  // println(string("a").many("aaa"))
  // delim()

  // label("[product]")
  // println((char('a') ** char('c'))("acme"))
  // delim()

  // label("[many1]")
  // println(char('c').many1("camera"))
  // println(char('c').many1("amera"))
  // delim()

  // label("[map, map2]")
  // println(string("hello").map(_.toUpperCase)("hello, world"))
  // println(
  //   char('a').map2(char('b'))((a, b) => a.toString ++ b.toString)("abra")
  // )

  // delim()

  // label("[listOfN]")
  // println((string("ab") | string("cad")).listOfN(0)("ababcad"))
  // println((string("ab") | string("cad")).listOfN(3)("cadabab"))

  // delim()

  // label("[flatMap]")
  // println(digit.flatMap(d => char('a').listOfN(d.toInt))("3aaabcd"))
  // delim()

end ParserDemo
