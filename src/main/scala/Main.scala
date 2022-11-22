import digit.IDigit
import digit.implDigit.Digit1
import node.INode
import node.nodeImpl.Node2
import digit.implDigit.Digit4
@main def hello: Unit =
  println("Hello world!")
  println(msg)

  val node: INode[IDigit[Int]] = Node2(Digit1[Int](10), Digit4[Int](4, 5, 6, 7))
  println(node.toString)

def msg = "I was compiled by Scala 3. :)"
