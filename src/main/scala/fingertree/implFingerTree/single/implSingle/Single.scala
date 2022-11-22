package single.implSingle

import single.ISingle

final case class Single[A](one: A) extends ISingle[A]:
    override def toString: String = String.format("Single( %s )", one.toString())
