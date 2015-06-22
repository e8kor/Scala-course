import java.util

def trololo(s: String): MyStringList = {
  val mySl = new MyStringList
  mySl += s + " la" * 8
  mySl
}

class MyStringList {
  var sl = new util.ArrayList[String]()

  def +=(s: String) = {
    sl.add(s)
  }

  def filter(p: String => Boolean) = {
    val it = sl.iterator()
    while (it.hasNext) {
      val curr = it.next
      if (p(curr) == false) it.remove()
    }
    this
  }

  filter(s => s == "Scala")
  filter(_.length > 42)

  def map(f: String => String): this.type = {
    var idx = 0
    while (idx < sl.size()) {
      val curr = sl.get(idx)
      sl.set(idx, f(curr))
      idx += 1
    }
    this
  }

  map(_.length.toString)

  def flatMap(f: String => MyStringList): this.type = {
    val result = new util.ArrayList[String]()
    val it = sl.iterator()
    while (it.hasNext) {
      val curr = it.next
      val list = f(curr).sl
      result.addAll(list)
    }
    sl = result
    this
  }

  flatMap(trololo)

  def fold(init: String, acc: (String, String) => String): String = {
    var result = init
    val it = sl.iterator()
    while (it.hasNext) {
      val curr = it.next
      result = acc(result, curr)
    }
    result
  }

  def concat: String = {
    var result = ""
    val it = sl.iterator()
    while (it.hasNext) {
      val curr = it.next
      result = result + curr
    }
    result
  }

  def concat2: String = fold("", (res: String, curr: String) => res + curr)

  def fold2(init: String)(acc: (String, String) => String): String = fold(init, acc)

  def concat3: String = fold2("")(_ + _)

  override def toString: String = sl.toString
}

// Examples:
val mySl = new MyStringList
mySl += "Scala"
mySl += "Java"

println(mySl)

println(mySl.filter(_ == "Scala"))

println(mySl.map(_.length.toString))

println(mySl.flatMap(trololo))

mySl += "6"

println(mySl.fold2("") { (acc, curr) =>
  acc + "{" + curr + "} "
})