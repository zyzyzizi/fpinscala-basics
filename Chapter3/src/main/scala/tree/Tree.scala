package fpinscala.chapter3.datatypes.tree

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], right: Tree[A])

  // Write a method size that counts the number of nodes (leaves and branches) in a tree.
  def size: Int = this match
    case Leaf(_)      => ???
    case Branch(l, r) => ???

  // Exercise 3.26
  // Write a function depth that returns the maximum path length from the
  // root of a tree to any leaf.
  def depth: Int = this match
    case Leaf(_)      => ???
    case Branch(l, r) => ???

  // Exercise 3.27
  // Write a function map, analogous to the method of the same name on List,
  // that modifies each element in a tree with a given function.
  def map[B](f: A => B): Tree[B] = this match
    case Leaf(a)      => ???
    case Branch(l, r) => ???

  // Exercise 3.28
  // Generalize size, maximum, depth, and map, writing a new function fold that
  // abstracts over their similarities. Reimplement them in terms of this more
  // general function. Can you draw an analogy between this fold function and
  // the left and rig(ht folds for List?
  def fold[B](f: A => B)(g: (B, B) => B): B = ???

  def sizeViaFold: Int = fold(???)(???)

  def mapViaFold[B](f: A => B): Tree[B] = fold(???)(???)

  def depthViaFold: Int = fold(???)(???)

end Tree

object Tree:

  // Write a function size that counts the number of nodes (leaves and branches) in a tree.
  def size[A](t: Tree[A]): Int = t.size

//   def firstPositive(t: Tree[Int]): Int = t match
//     case Leaf(i) => i
//     case Branch(l, r) =>
//       val lpos = firstPositive(l)
//       if lpos > 0 then lpos else firstPositive(r)

  extension (tree: Tree[Int])
    def firstPositive: Int = tree match
      case Leaf(i) => i
      case Branch(l, r) =>
        val lpos = l.firstPositive
        if lpos > 0 then lpos else r.firstPositive

  // Exercise 3.25
  // Write a function maximum that returns the maximum element in a Tree[Int].
  extension (tree: Tree[Int])
    def maximum: Int = tree match
      case Leaf(i)      => i
      case Branch(l, r) => l.maximum max r.maximum

    def maximumViaFold: Int = tree.fold(???)(???)

end Tree
