import fpinscala.datatypes.List
import List.*

class ListTest extends munit.FunSuite {

  test("apply".ignore) {
    val list = List(1, 2, 3, 4, 5)
    println(list)
  }

  test("tail".ignore) {
    val list = List(1, 2, 3, 4, 5)
    println(tail(list))
  }

  test("exception".ignore) {
    intercept[NoSuchElementException] {
      // the code that should throw an exception
      Set.empty.head
    }
  }
}
