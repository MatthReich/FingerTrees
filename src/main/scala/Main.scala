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
    0 + ": size = " + fingerTree.size + "; head: " + fingerTree.head + "; last: " + fingerTree.last
  )

  for (i <- Range(1, 50))
    fingerTree = fingerTree.append(i)
    println(i + ": " + fingerTree.toString())
    println(
      i + ": size = " + fingerTree.size + "; head: " + fingerTree.head + "; last " + fingerTree.last
    )

  println("finished")
