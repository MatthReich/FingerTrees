# FingerTrees

[![Build Status](https://app.travis-ci.com/MatthReich/FingerTrees.svg?branch=master)](https://app.travis-ci.com/MatthReich/FingerTrees) [![Coverage Status](https://coveralls.io/repos/github/MatthReich/FingerTrees/badge.svg?branch=master)](https://coveralls.io/github/MatthReich/FingerTrees?branch=master)

## Allgemein

FingerTrees sind eine effiziente funktionale Datenstruktur. Sie sind eine spezielle Art von binären Baumstrukturen, die dazu dienen, Sequenzen von Elementen zu speichern und schnellen Zugriff auf diese Elemente zu ermöglichen 

Unter einer funktionalen Datenstruktur versteht man eine Datenstruktur, die in einem funktionalen Programmierparadigma implementiert wurde. Im Gegensatz zu imperativen Datenstrukturen, die mithilfe von Anweisungen verändert werden, werden funktionale Datenstrukturen durch die Rückgabe neuer Datenstrukturen von Funktionen verändert. Dies bedeutet, dass funktionale Datenstrukturen nicht mutierbar sind und keine Seiteneffekte haben. Die Daten, welche nach der Veränderung gleich bleiben, werden dabei nicht kopiert, sondern über Referenzen mehrfach verwendet.

Dabei besteht ein FingerTree aus 5 Komponenten: `Empty`, `Single`, `Deep`, `Digit` und `Node`.


## Benutzung

Einen FingerTree erzeugt man wie folgt:

```scala
val intFingerTree: FingerTree[Int]  = FingerTree[Int]()
```
Möchte man Elemente hinzufügen, so ist dies über `append` oder `prepend` möglich. Dabei kann 1 Argument, n Argumente oder eine Sequenz übergeben werden.

```scala
val oneParamFingerTree = intFingerTree append 1
val nParamFingerTree   = intFingerTree prepend (1, 2, 3, 4)
val seqParamFingerTree = intFingerTree.append((1 to 50).toSeq: _*)
```

Wenn man zwei FingerTrees miteinander kombinieren möchte, ist dies über die Funktion `concat` möglich.

```scala
val concatenatedFingerTree = nParamFingerTree concat seqParamFingerTree
```
`head`, `last`, `init` als auch `tail` geben jeweils ein `Option[_]` des Wertes raus, um ein einfacheres Handling zu ermöglichen.

```scala
val head: Option[Int] = nParamFingerTree.head
val last: Option[Int] = nParamFingerTree.last
val init: Option[FingerTree[Int]] = nParamFingerTree.init
val tail: Option[FingerTree[Int]] = nParamFingerTree.tail
```
Auch ist es möglich, die `size` des FingerTree ausgeben zu lassen.

```scala
val size: Int = seqParamFingerTree.size
```

Für die Überprüfung, ob der Baum `empty` ist, wird `isEmpty` benutzt.

```scala
val isFingerTreeEmpty: Boolean = intFingerTree.isEmpty
```

Zuletzt ist es auch Möglich, den Baum als Liste, über `toList`, zurückzubekommen.

```scala
val seqParamFingerTreeAsList: List[Int] = seqParamFingerTree.toList
```

## Implementierung

### Generics

Im Zuge der Implementierung kommen Generics, für die Typenunabhängigkeit, zum Einsatz. Dabei steht `A` oder `B`, typisch nach der [Dokumentation von Scala](https://docs.scala-lang.org/scala3/book/types-generics.html), für den Typparameter. 

Folgend werden einige Darstellungen näher erläutert.

Mit `A*` symbolisiert man `varargs`, also eine variable Anzahl von Argumenten. Dabei kann man diese auf unterschiedliche Art und Weisen nutzen. So kann man einfach mehrere Argumente der Funktion `f` übergeben, wie zum Beispiel `f(1, 2, 3, 4)`. Es ist aber auch Möglich eine Sequenz `val seq = Seq(1, 2, 3, 4)` an die Funktion zu übergeben `f(seq: _*)`.

Unter `+A` versteht man ...
> wird ergänzt

Die Verwendung von `B >: A` bedeutet, ...
> wird ergänzt


## Quellen

[https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala](https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala)

[https://observablehq.com/@vpatryshev/finger-trees-in-js](https://observablehq.com/@vpatryshev/finger-trees-in-js)

[https://en.wikipedia.org/wiki/Finger_tree](https://en.wikipedia.org/wiki/Finger_tree)

[https://docs.scala-lang.org/scala3/book/introduction.html](https://docs.scala-lang.org/scala3/book/introduction.html)

[https://andrew.gibiansky.com/blog/haskell/finger-trees/](https://andrew.gibiansky.com/blog/haskell/finger-trees/)

[https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf](https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf)
