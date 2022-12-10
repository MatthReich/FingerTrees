import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import node.nodeImpl.Node2
import digit.implDigit.Digit4
import fingertree.implFingerTree.FingerTree

@main def main: Unit =
  var fingerTree = FingerTree[Int]()

  println(0 + ": " + fingerTree.toString())
  println(
    0 + ": size = " + fingerTree.size + "; isEmpty: " + fingerTree.isEmpty + "; head: " + fingerTree.head + "; last: " + fingerTree.last
  )

  for (i <- Range(1, 10))
    fingerTree = fingerTree.append(i)
    println(i + ": " + fingerTree.toString())
    println(
      i + ": size = " + fingerTree.size + "; isEmpty: " + fingerTree.isEmpty + "; head: " + fingerTree.head + "; last " + fingerTree.last
    )

  var fingerTreeToConcat = FingerTree[Int]()
  for (i <- Range(1, 10))
    fingerTreeToConcat = fingerTreeToConcat.prepend(i)

  println(fingerTreeToConcat.toString())
  val selfConcat = fingerTree.concat(fingerTree)
  val prepConcat = fingerTree.concat(fingerTreeToConcat)

  println(
    "self: " + selfConcat
      .toString() + "\nsize: " + selfConcat.size
  )

  println(
    "self: " + prepConcat
      .toString() + "\nsize: " + prepConcat.size
  )

  println("finished")
