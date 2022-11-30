package single.implSingle

import single.ISingle
import deep.IDeep
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty
import node.INode

final case class Single[A](one: A | INode[A]) extends ISingle[A]:
  override def +:(entry: A | INode[A]): IDeep[A] =
    one match
      case value: A =>
        Deep(
          Digit1(value),
          Empty(),
          Digit1(entry.asInstanceOf[A])
        )
      case _ =>
        entry match
          case node: INode[A] =>
            Deep(
              Digit1(one),
              Empty(),
              Digit1(entry)
            )
          case _ => Deep(Digit1(one.asInstanceOf[A]), Empty(), Digit1(one.asInstanceOf[A]))

  override def toString: String = String.format("Single( %s )", one.toString())
