package empty.implEmpty

import empty.IEmpty

final case class Empty() extends IEmpty:
    override def toString: String = "Empty()"
