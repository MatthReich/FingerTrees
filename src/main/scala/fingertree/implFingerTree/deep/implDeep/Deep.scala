package deep.implDeep

import deep.IDeep
import digit.IDigit
import empty.IEmpty
import single.ISingle
import node.INode
import node.implNode.Node3
import single.implSingle.Single
import digit.implDigit.Digit4
import digit.implDigit.Digit2
import digit.implDigit.Digit1
import empty.implEmpty.Empty

final case class Deep[A](one: IDigit[A], two: IEmpty[A] | IDeep[A] | ISingle[A], three: IDigit[A])
    extends IDeep[A]:
  override def +:(entry: A): IDeep[A] =
    three.+:(entry) match
      case Some(value) => this.copy(three = value)
      case None => 
        val digit4: Digit4[A] = three.asInstanceOf[Digit4[A]]
        two match
          case empty: IEmpty[A] => 
            Deep(one, Single(Node3(digit4.one.asInstanceOf[A], digit4.two.asInstanceOf[A], digit4.three.asInstanceOf[A])), Digit2(digit4.four, entry))
          case single: ISingle[A] => 
            Deep(one, Deep(Digit1(two.asInstanceOf[ISingle[A]].one), Empty(), Digit1(Node3(digit4.one.asInstanceOf[A], digit4.two.asInstanceOf[A], digit4.three.asInstanceOf[A]))) , Digit2(digit4.four, entry))
          case deep: IDeep[A] => 
             deep.three.+:(Node3(digit4.one.asInstanceOf[A], digit4.two.asInstanceOf[A], digit4.three.asInstanceOf[A])) match
              case Some(value) =>  Deep(one, Deep(deep.one, deep.two, value), Digit2(digit4.four, entry))
              case None =>    
                val node4: Digit4[A] = deep.three.asInstanceOf[Digit4[A]]  
                Deep(one, Deep(deep.one, Single(Node3(node4.one.asInstanceOf[INode[A]], node4.two.asInstanceOf[INode[A]], node4.three.asInstanceOf[INode[A]])), Digit2(node4.four.asInstanceOf[INode[A]], Node3(digit4.one.asInstanceOf[A], digit4.two.asInstanceOf[A], digit4.three.asInstanceOf[A]) )), Digit2(digit4.four, entry))
      

  override def toString: String = String.format(
    "Deep( %s, %s, %s )",
    one.toString,
    two.toString,
    three.toString
  )
