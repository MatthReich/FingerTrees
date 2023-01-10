# FingerTrees

[![Build Status](https://app.travis-ci.com/MatthReich/FingerTrees.svg?branch=master)](https://app.travis-ci.com/MatthReich/FingerTrees) [![Coverage Status](https://coveralls.io/repos/github/MatthReich/FingerTrees/badge.svg?branch=master)](https://coveralls.io/github/MatthReich/FingerTrees?branch=master)

Dieses Projekt entstand im Zuge des Seminars aus dem Informatik Master (INFM) der Hochschule Offenburg. Dabei war es die Aufgabe, einen FingerTree zu implementieren, wobei nur Basisoperationen unterstützt werden sollen und nicht alle möglichen Operationen. Im Folgenden wird auf die Struktur, Benutzung und Implementierung bezüglich dieses Seminars eingegangen.

## Allgemein

FingerTrees sind eine effiziente funktionale Datenstruktur. Sie sind eine spezielle Art von binären Baumstrukturen, die dazu dienen, Sequenzen von Elementen zu speichern und schnellen Zugriff auf diese Elemente zu ermöglichen

Dabei versteht man unter einer funktionalen Datenstruktur eine Datenstruktur, die in einem funktionalen Programmierparadigma implementiert wurde. Im Gegensatz zu imperativen Datenstrukturen, die mithilfe von Anweisungen verändert werden, werden funktionale Datenstrukturen durch die Rückgabe neuer Datenstrukturen von Funktionen verändert. Dies bedeutet, dass funktionale Datenstrukturen nicht mutierbar sind und keine Seiteneffekte haben. Die Daten, welche nach der Veränderung gleich bleiben, werden dabei nicht kopiert, sondern über Referenzen mehrfach verwendet.

Die Effizienz dieser Datenstruktur soll vor allem in dem vorne und hinten Anfügen von Elementen (O(log n), bis zu O(1)), dem Zusammenführen von zwei FingerTrees (O(log n)) und auch dem Zugriff auf das erste bzw. letzte Element (O(1)) gewährleistet sein.

Bei der Implementierung wurde Bezug auf das Paper von [Ralf Hinze und Ross Paterson](https://www.staff.city.ac.uk/~ross/papers/FingerTree.pdf) genommen.

## Struktur

Eine Übersicht der Struktur des Projekts, um die wichtigsten Dateien für das Seminar zu finden, wird folgend gegeben.

```
.
├── documents
│   └── FingerTrees.pptx                        # Präsentation über FingerTrees
├── src
│   ├── main/scala/fingertree/
│   │   ├── Worksheet.worksheet.sc              # Worksheet um FingerTree manuell auszuprobieren
│   │   └── ...                                 # Implementierung der Datenstruktur FingerTrees
│   └── test/scala/fingertree/implFingerTree/
│       └── ...                                 # Die Tests für FingerTrees
└── README.md                                   # Die mit abzugebende textuelle Erklärung
```

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

In dem im Projekt enthaltenen [Worksheet](https://github.com/MatthReich/FingerTrees/blob/master/src/main/scala/fingertree/Worksheet.worksheet.sc) sind diese Operationen nochmals aufgeführt und selbst testbar, ohne eine Main-Klasse zu definieren.

## Implementierung

Folgend wird auf die Implementierung eingegangen, was die wesentlichen Punkte des Codes erläutert.

### Struktur

Die Struktur der Implementierung des FingerTrees und der einzelnen Komponenten ist wie folgt:

```
├── ...
├── src/main/scala/fingertree/
│   ├── implFingerTree
│   │   ├── implTreeComponents
│   │   │   ├── deep                    # Implementierung von der Komponente Deep
│   │   │   │   └── ...
│   │   │   ├── digit                   # Implementierung von der Komponente Digit
│   │   │   │   └── ...
│   │   │   ├── empty                   # Implementierung von der Komponente Empty
│   │   │   │   └── ...
│   │   │   ├── node                    # Implementierung von der Komponente Node
│   │   │   │   └── ...
│   │   │   ├── single                  # Implementierung von der Komponente Single
│   │   │   │   └── ...
│   │   │   └── view                    # Implementierung von der Hilfskomponente View, welche für init bzw. tail benötigt wird
│   │   │       └── ...
│   │   ├── FingerTree.scala            # Die Hauptimplementierung des FingerTrees, welche auf die Komponenten zugreift
│   │   └── ITreeComponent.scala
│   └── IFingerTree.scala               # Interface der FingerTrees, welches alle möglichen Operationen beinhaltet
└── ...
```

### Allgemeine Syntax

Im Zuge der Implementierung kommen Generics, für die Typenunabhängigkeit, zum Einsatz. Dabei steht `A` oder `B`, typisch nach der [Dokumentation von Scala](https://docs.scala-lang.org/scala3/book/types-generics.html), für den Typparameter. Der Typ bei zum Beispiel Listen wird generell in `[]` angefügt, also wäre eine Liste vom Typ `A` `List[A]`.

Mit `A*` symbolisiert man `varargs`, also eine variable Anzahl von Argumenten. Dabei kann man diese auf unterschiedliche Art und Weisen nutzen. So kann man einfach mehrere Argumente der Funktion `def f(args: A*)` übergeben, wie zum Beispiel `f(1, 2, 3, 4)`. Es ist aber auch Möglich eine Sequenz `val seq = Seq(1, 2, 3, 4)` an die Funktion zu übergeben `f(seq: _*)`.

Unter `+A` versteht man Kovarianz. Die Verwendung von `B >: A` ermöglicht eine lower bound zu nutzen. Dabei wird in diesem Fall festgelegt, dass `B` ein Supertype von `A` sein muss. Der Vollständigkeit wegen wird noch `-A` erwähnt, was für die Kontravarianz steht, aber nicht verwendet wird.

### append, prepend

Wenn ein Element dem FingerTree hinzugefügt wird, wird dies auf die root-Komponente `treeHead` weitergegeben. Dabei wird im Falle eines `Empty` ein `Single` erzeugt und im Falle eines `Single` ein `Deep`. Jenachdem ob `append` oder `prepend` benutzt wird, mit dem neuen Element als Suffix oder Präfix des Deeps. Im `Deep` wird im Falle des `append` das Suffix betrachtet, ob es ein `Digit4` speichert. Dies ist in dem Folgenden Codeabschnitt zu erkennen.

```scala
suffix match
  case Digit4(entry1, entry2, entry3, entry4) =>
    val newDeep = deep.:+[INode[A]](Node3(entry1, entry2, entry3))
    val newSuffix = Digit2(entry4, newEntry)
    this.copy(deep = newDeep, suffix = newSuffix)
  case partialDigit =>
    this.copy(suffix = partialDigit :+ newEntry)
```

Falls es ein `Digit4` ist, wird ein neues `Deep` erzeugt, welches die ersten drei Elemente des `Digit4` als `Node3` der nächsten Ebene (deep) weitergibt. Das Suffix beinhaltet ein `Digit2` welches das letzte Element des `Digit4` und den neuen Eintrag beinhaltet. Anschließend wird eine Kopie von sich selbst mit den beiden neu erzeugten Elementen zurück gegeben. Falls kein `Digit4` gespeichert war, wird einfach das `Digit` um das neue Element erweitert. Für `prepend` funktioniert das ganze genau gleich, nur Spiegelverkehrt.

### concat

Um den Text einfacher zu halten wird von FingerTree A, auf welchen FingerTree B verschmolzen wird gesprochen. Wenn A leer ist, wird einfach B zurückgegeben. Wenn A nur ein Element speichert, wird die root-Komponente von B mit `prepend` an dieses Element angefügt. Wenn A ein Deep ist wird nun dort geschaut ob B ein `Empty` ist. Wenn ja wird A zurück gegeben. Bei einem `Single` wird B an A mit `append` angefügt. Die eigentliche Komplexität des Verschmelzens passiert, wenn A und B beide `Deep` sind. Dabei wird eine Hilfsmethode `concatDeep` aufgerufen, welche eine Liste entgegen nimmt und die beiden FingerTrees.

```scala
private def concatDeep[A](
    thisDeep: IDeep[A],
    concatList: List[A],
    deepToConcat: IDeep[A]
): ITreeComponent[A] =
  val newPrefix = thisDeep.prefix
  val newDeep = concatNewDeep(
    thisDeep.deep,
    createNodeCombinations(
      thisDeep.suffix.toList ::: concatList ::: deepToConcat.prefix.toList
    ),
    deepToConcat.deep
  )
  val newSuffix = deepToConcat.suffix
  Deep(newPrefix, newDeep, newSuffix)
```

> wird ergänzt

```scala
private def concatNewDeep[A](
    left: ITreeComponent[A],
    concatList: List[A],
    right: ITreeComponent[A]
): ITreeComponent[A] =
  (left, right) match
    case (Empty(), _) => concatList.foldRight(right)((a, b) => a +: b)
    case (_, Empty()) => concatList.foldLeft(left)((b, a) => b :+ a)
    case (Single(entry), _) =>
      entry +: concatList.foldRight(right)((a, b) => a +: b)
    case (_, Single(entry)) =>
      concatList.foldLeft(left)((b, a) => b :+ a) :+ entry
    case (leftDeep @ Deep(_, _, _), rightDeep @ Deep(_, _, _)) =>
      concatDeep[A](leftDeep, concatList, rightDeep)
```

> wird ergänzt

```scala
private def createNodeCombinations[A](
    entriesAsList: List[A]
): List[INode[A]] =
  (entriesAsList: @unchecked) match
    case entry1 :: entry2 :: Nil => Node2(entry1, entry2) :: Nil
    case entry1 :: entry2 :: entry3 :: Nil =>
      Node3(entry1, entry2, entry3) :: Nil
    case entry1 :: entry2 :: entry3 :: entry4 :: Nil =>
      Node2(entry1, entry2) :: Node2(entry3, entry4) :: Nil
    case entry1 :: entry2 :: entry3 :: tail =>
      Node3(entry1, entry2, entry3) :: createNodeCombinations(tail)
```

> wird ergänzt

### head, last

Bei einem leeren Baum wird als erstes bzw. letztes Element `None` zurückgegeben, da keins existiert. Im Falle von nur einem gespeicherten Element, wird dieses als `head` oder `last` gesehen. Um bei einem `Deep` an das erste Element zu kommen, wird von diesem auf das Präfix `head` aufgerufen, welches dann das erste Element des gespeicherten `Digit` liefert. Wenn das letzte Element gefragt ist, wird auf dem Suffix `last` gefragt und das letzte Element des `Digit` zurück gegeben.

### init, tail

> wird ergänzt

### size, isEmpty, toList

> wird ergänzt

### toString

Das `toString` 

## Quellen

[https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala](https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala)

[https://observablehq.com/@vpatryshev/finger-trees-in-js](https://observablehq.com/@vpatryshev/finger-trees-in-js)

[https://en.wikipedia.org/wiki/Finger_tree](https://en.wikipedia.org/wiki/Finger_tree)

[https://docs.scala-lang.org/scala3/book/introduction.html](https://docs.scala-lang.org/scala3/book/introduction.html)

[https://andrew.gibiansky.com/blog/haskell/finger-trees/](https://andrew.gibiansky.com/blog/haskell/finger-trees/)

[https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf](https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf)
