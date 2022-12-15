import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import node.nodeImpl.Node2
import digit.implDigit.Digit4
import fingertree.implFingerTree.FingerTree

@main def main: Unit =
  var fingerTree = FingerTree[Int]()

  println(0 + ": " + fingerTree.toString())

  for (i <- Range(1, 51))
    fingerTree = fingerTree.append(i)
    println(i + ": " + fingerTree.toString())

  var fingerTreeToConcat = FingerTree[Int]()
  for (i <- Range(1, 10))
    fingerTreeToConcat = fingerTreeToConcat.prepend(i)

  val selfConcat = fingerTree.concat(fingerTree)
  println(
    "selfConcat: " + selfConcat
      .toString() + "\nsize: " + selfConcat.size
  )

  val prepConcat = fingerTree.concat(fingerTreeToConcat)
  println(
    "prepConcat: " + prepConcat
      .toString() + "\nsize: " + prepConcat.size
  )

  println("finished")
