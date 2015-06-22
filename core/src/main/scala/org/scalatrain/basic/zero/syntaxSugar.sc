// Apply
val f = (s: String) => s.isEmpty()
f.apply("Not empty") // false
f("")

// true

object Callable {
  def apply() = println("Called!")
}

Callable.apply()
Callable()

// Update
class Updatable {
  def update(i: Int, value: String): Unit = println(s"Update $i with $value")
}

val u = new Updatable
u.update(5, "Test")
u(5) = "Test"