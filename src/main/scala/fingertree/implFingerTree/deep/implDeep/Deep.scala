package deep.implDeep

import deep.IDeep
import digit.IDigit
import empty.IEmpty

final case class Deep[A](one: IDigit[A], two: IEmpty, three: IDigit[A])
    extends IDeep[A]:
  override def +:(entry: A): Deep[A] = this.copy(three = three.+:(entry))

  override def toString: String = String.format(
    "Deep( %s, %s, %s )",
    one.toString,
    two.toString,
    three.toString
  )
