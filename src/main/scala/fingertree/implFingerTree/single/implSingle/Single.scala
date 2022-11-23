package single.implSingle

import single.ISingle
import deep.IDeep
import deep.implDeep.Deep
import digit.implDigit.Digit1
import empty.implEmpty.Empty

final case class Single[A](one: A) extends ISingle[A]:
    override def +:(entry: A): IDeep[A] = Deep(Digit1(one), Empty(), Digit1(entry))

    override def toString: String = String.format("Single( %s )", one.toString())
