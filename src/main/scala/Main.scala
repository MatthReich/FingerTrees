import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import node.nodeImpl.Node2
import digit.implDigit.Digit4
import fingertree.implFingerTree.FingerTree

@main def hello: Unit =
  var fingerTree = FingerTree[Int]()
  println(fingerTree.toString())
  fingerTree = fingerTree.append(1)
  println(fingerTree.toString())
  fingerTree = fingerTree.append(2)
  println(fingerTree.toString())
  fingerTree = fingerTree.append(3)
  println(fingerTree.toString())
  fingerTree = fingerTree.append(4)
  println(fingerTree.toString())
  fingerTree = fingerTree.append(5)
  println(fingerTree.toString())
  println("finished")

// Empty -> Single(Value) -> Deep(Digit1 Empty Digit1) -> Deep(digit1234 Empty Digit 1) -> Deep(Digit2, )
