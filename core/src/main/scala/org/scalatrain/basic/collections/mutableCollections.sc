
import scala.collection.mutable

// Seq: IndexedSeq, LinearSeq, Array, ArrayBuffer, ListBuffer
val ab = mutable.ArrayBuffer("a", "b", "c")


"a" +: ab :+ "z"
ab(0)

ab.head
ab.init
ab.last
ab.tail

// Set: HashSet
val hs = mutable.HashSet("red", "green")
hs += "blue"
hs -= "red"
hs.contains("green")
hs & Set("blue", "yellow")
println(hs & Set("blue", "yellow"))
println(hs("green"))
// Map: HashMap, TrieMap

val m = mutable.HashMap((1, "one"), 2 -> "two")
m.contains(1)
m.getOrElseUpdate(3, hs.head)
val m2 = mutable.ArrayBuffer(4 -> "four", 5 -> "five").toMap

val m3 = m -- m2.keys

m(1)

m.update(6, "six")
m(6) = "six"
m += 6 -> "six"

m.contains(1) == m.isDefinedAt(1)

def foo(i: Int, mappingFunc: Int => String) = mappingFunc(i)
foo(1, m)
println(m)

val f: Function1[Int, String] = _.toString
val f1: Int => String = _.toString
type ~[A, B] = Tuple2[A, B]
val t: Int ~ String ~ Double = ((1, "2"), 3.0)

case class !![A, B](a: A, b: B)

new !!(new !!(1, "String"), 3.0) match {
  case a !! b !! c => println(a, b, c)
  case !!(!!(a, b), c) => println(a, b, c)
}

