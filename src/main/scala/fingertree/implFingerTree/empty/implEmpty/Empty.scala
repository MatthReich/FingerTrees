package empty.implEmpty

import empty.IEmpty
import single.ISingle
import single.implSingle.Single

final case class Empty[A]() extends IEmpty[A]:
    override def +:(entry: A): ISingle[A] = Single(entry)   

    override def toString: String = "Empty()"
