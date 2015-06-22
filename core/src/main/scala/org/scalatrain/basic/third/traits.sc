import org.scalatrain.basic.third._

import scala.collection.mutable.ArrayBuffer

// Linearization

// Cat ￼ ￼ FourLegged ￼ ￼ HasLegs ￼ ￼ Furry ￼ ￼ Animal ￼ ￼ AnyRef ￼ ￼ Any
val cat = new Cat
val man = new Animal with TwoLegged with HasHands

def compound(animal: HasLegs with HasHands) = {}
//        compound(cat)   // Won't compile
compound(man)


//    trait Wrong extends Tweeter
val user = new User with Tweeter


// Stackable

abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get() = buf.remove(0)

  def put(x: Int) {
    buf += x
  }
}

val queue = new BasicIntQueue
queue.put(10)
println(queue.get())

trait Doubling extends IntQueue {
  abstract override def put(x: Int) {
    super.put(2 * x)
  }
}

val doublequeue = new BasicIntQueue with Doubling
doublequeue.put(10)
println(doublequeue.get())

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) {
    super.put(x + 1)
  }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 10) super.put(x)
  }
}

val compQ = new BasicIntQueue with Doubling with Incrementing with Filtering
compQ.put(7)
compQ.put(10)
compQ.put(20)
println(compQ.get())
println(compQ.get())



implicit val config = new Config(Map("url" -> "jscala.org"))
def $(key: String)(implicit config: Config) = config.get(key)


val url = $("url")