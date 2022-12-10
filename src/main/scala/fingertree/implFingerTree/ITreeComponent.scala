package fingertree.implFingerTree

trait ITreeComponent[+A]:
  def :+[B >: A](newEntry: B): ITreeComponent[B]
  def +:[B >: A](newEntry: B): ITreeComponent[B]
  def ++[B >: A](treeToConcat: ITreeComponent[B]): ITreeComponent[B]

  def size: Int
  def isEmpty: Boolean
  def head: Option[A]
  def last: Option[A]
