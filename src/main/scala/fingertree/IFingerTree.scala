package fingertree

trait IFingerTree[A] {
  def append(entry: A): IFingerTree[A]
  def remove(entry: A): IFingerTree[A]

  def getIndex(entry: A): Int
  def get(index: Int): A
  def set(index: Int, entry: A): IFingerTree[A]

  def head: A
  def last: A

  def toString: String
}
