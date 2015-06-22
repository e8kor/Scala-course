// Named arguments, default values, multiple argument lists, call-by name arguments
def uberFunction(arg1: String, arg2: Int = 0)(body: => Unit) = {
  println(arg1)
  body
  println(arg2)
}

uberFunction("Hello", 1)(println("World"))

uberFunction("Hello")(println("World"))

uberFunction(arg2 = 2, arg1 = "Hello") {
  println("World")
}

def IfElse(pred: Boolean)(cons: => Unit)(alt: => Unit) = if (pred) cons else alt

IfElse(true) {
  println("In truth we trust")
} {
  println("Or not")
}

def varargs(args: String*) = println(args)
varargs()
varargs("one")
varargs("one", "two")

// Anonymous Functions
def isEmpty(arg: String): Boolean = arg.isEmpty // type is Function1[String, Boolean]
val isEmpty2 = (arg: String) => arg.isEmpty // also can be written as (String => Boolean)

val isEmpty3 = new Function1[String, Boolean] {
  override def apply(v1: String): Boolean = v1.isEmpty
}

val isEmpty4 = isEmpty _ // eta expansion

isEmpty("") == isEmpty2("") == isEmpty3("") == isEmpty4("")
