package empty.implEmpty

import empty.IEmpty
import single.ISingle
import single.implSingle.Single
import fingertree.implFingerTree.ITreeComponent

final case class Empty() extends IEmpty, ITreeComponent[Nothing]:
  override def :+[B](newEntry: B): ITreeComponent[B] = Single(newEntry)

  override def +:[B](newEntry: B): ITreeComponent[B] = Single(newEntry)

  override def size: Int = 0

  override def isEmpty: Boolean = true

  override def head: Option[Nothing] = None

  override def last: Option[Nothing] = None

  override def toString: String = s"Empty()"
