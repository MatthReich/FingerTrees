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
│   │   │   └── view                    # Implementierung von der Hilfskomponente View, welche für init bzw tail benötigt wird
│   │   │       └── ... 
│   │   ├── FingerTree.scala            # Die Hauptimplementierung des FingerTrees, welche auf die Komponenten zugreift
│   │   └── ITreeComponent.scala            
│   └── IFingerTree.scala               # Interface der FingerTrees, welches alle möglichen Operationen beinhaltet                    
└── ...
```

### Generics

Im Zuge der Implementierung kommen Generics, für die Typenunabhängigkeit, zum Einsatz. Dabei steht `A` oder `B`, typisch nach der [Dokumentation von Scala](https://docs.scala-lang.org/scala3/book/types-generics.html), für den Typparameter. 

Folgend werden einige Darstellungen näher erläutert.

Mit `A*` symbolisiert man `varargs`, also eine variable Anzahl von Argumenten. Dabei kann man diese auf unterschiedliche Art und Weisen nutzen. So kann man einfach mehrere Argumente der Funktion `f` übergeben, wie zum Beispiel `f(1, 2, 3, 4)`. Es ist aber auch Möglich eine Sequenz `val seq = Seq(1, 2, 3, 4)` an die Funktion zu übergeben `f(seq: _*)`.

Unter `+A` versteht man ...
> wird ergänzt

Die Verwendung von `B >: A` bedeutet, ...
> wird ergänzt

### append, prepend
> wird ergänzt

### concat
> wird ergänzt

### head, last
> wird ergänzt

### init, tail
> wird ergänzt

### size, isEmpty, toList
> wird ergänzt

### toString
> wird ergänzt

## Quellen

[https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala](https://www.atlassian.com/blog/software-teams/covariance-and-contravariance-in-scala)

[https://observablehq.com/@vpatryshev/finger-trees-in-js](https://observablehq.com/@vpatryshev/finger-trees-in-js)

[https://en.wikipedia.org/wiki/Finger_tree](https://en.wikipedia.org/wiki/Finger_tree)

[https://docs.scala-lang.org/scala3/book/introduction.html](https://docs.scala-lang.org/scala3/book/introduction.html)

[https://andrew.gibiansky.com/blog/haskell/finger-trees/](https://andrew.gibiansky.com/blog/haskell/finger-trees/)

[https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf](https://proglang.informatik.uni-freiburg.de/teaching/proseminar/2007ws/material/Nutz.pdf)
