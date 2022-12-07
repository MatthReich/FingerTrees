package empty.implEmpty

import empty.IEmpty
import single.ISingle
import single.implSingle.Single
import fingertree.implFingerTree.ITreeComponent

final case class Empty() extends IEmpty, ITreeComponent[Nothing]:
    
    override def :+[A1](newEntry: A1): ITreeComponent[A1] = Single(newEntry)   

    override def toString: String = s"Empty()"


































































    