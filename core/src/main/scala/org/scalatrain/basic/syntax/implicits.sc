
class Complex(val real: Double, val imaginary: Double = 0.0) {
  def +(rhs: Complex): Complex = new Complex(real + rhs.real, imaginary + rhs.imaginary)

  //      def +[N : Numeric](n: N) = new Complex(implicitly[Numeric[N]].plus(real, n))
}

// implicit defs

val c = new Complex(1.0)

implicit def int2Complex(i: Int): Complex = new Complex(i)

// Conversions to an expected type
val i: Complex = 1

def foo(c: Complex) = println(c.real)

foo(2)
// Conversions of the receiver of a selection
println(2.imaginary)
println((2 + i).real)
// Implicit parameters
def bar(s: Int)(implicit convertor: Int => Complex): Complex = convertor(s)

bar(123)(int2Complex)

class Config {
  val url = "https://github.com/nau/jscala"
}

//    implicit val config = new Config
def contextual(msg: String)(implicit c: Config) = {
  c.url
  import c._
  url
}

//  contextual("asdf")
