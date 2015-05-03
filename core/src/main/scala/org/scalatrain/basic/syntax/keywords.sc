
// Imports

import java.lang.Integer.bitCount
import java.lang.{Long => JLong}
import java.util.{Map => _}
import java.{util => jutil}

bitCount(1)

//import args._
//length

// final int constant = 1
val constant: Int = 1

// long i = 0
var i = 0L: Long // type ascription

//var j: Int = i // Won't compile
val j: Int = i.asInstanceOf[Int]
j.isInstanceOf[Int]

val xml = <bean>Can't live without Spring</bean>;
println(xml)

def calc = {
  println("Calculating the truth")
  Thread.sleep(1000)
  true
}

lazy val how = if (calc) "Scala" else "Java"

while (i < 10) i += 1

do i -= 1 while (i > 0)

// for comprehension
for (arg <- Array("hello", "Scala", "World"))
  println(arg)

if (how.equals("Scala") || (how == "Java") && (how eq null) || (how ne null)) println("bugaga")

val num = how match {
  case "Scala" => 1
  case "Java" => 2
  case _ =>
}

val result = try {
  "one".toInt
} catch {
  case e: Exception => 0
} finally {
  println("Finally, only side-effects here.")
}
println(result)