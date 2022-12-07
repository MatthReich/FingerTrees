import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import node.nodeImpl.Node2
import digit.implDigit.Digit4
import fingertree.implFingerTree.FingerTree

@main def hello: Unit =
  var fingerTree = FingerTree[Int]()

  println(0 + ": " + fingerTree.toString())
  for (i <- Range(1, 40))
    fingerTree = fingerTree.append(i)
    println(i + ": " + fingerTree.toString())

  println("finished")
