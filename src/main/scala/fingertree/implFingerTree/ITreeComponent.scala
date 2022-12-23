package fingertree.implFingerTree

import view.IView
import view.implView.IViewLeft
import view.implView.IViewRight

trait ITreeComponent[+A]:
  def :+[B >: A](newEntry: B): ITreeComponent[B]
  def +:[B >: A](newEntry: B): ITreeComponent[B]
  def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B]

  def size: Int
  def isEmpty: Boolean
  def head: Option[A]
  def last: Option[A]
  def init: Option[ITreeComponent[A]]
  def tail: Option[ITreeComponent[A]]

  def viewLeft: Option[IViewLeft[A]]
  def viewRight: Option[IViewRight[A]]

  def toList: List[A]
  def toString: String
