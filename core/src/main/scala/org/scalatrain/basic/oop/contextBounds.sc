import java.util.Date

class Complex(val real: Double, val imaginary: Double = 0.0) {
  def +(rhs: Complex): Complex = new Complex(real + rhs.real, imaginary + rhs.imaginary)

  //      def +[N : Numeric](n: N) = new Complex(implicitly[Numeric[N]].plus(real, n))
}

def calculate1[A: Numeric](a: A, b: A) = {


}
def calculate2[A](a: A, b: A)(implicit ev1: Numeric[A]) = {
  ev1.plus(a, b)
}

println(calculate2(2, 3))
println(calculate2(2.5, 3.5))
println(calculate2(BigDecimal(3), BigDecimal(3)))

implicit object ComplexNumeric extends Numeric[Complex] {
  override def plus(x: Complex, y: Complex): Complex = new Complex(x.real + y.real, x.imaginary + y.imaginary)

  override def toDouble(x: Complex): Double = ???

  override def toFloat(x: Complex): Float = ???

  override def toInt(x: Complex): Int = ???

  override def negate(x: Complex): Complex = ???

  override def fromInt(x: Int): Complex = ???

  override def toLong(x: Complex): Long = ???

  override def times(x: Complex, y: Complex): Complex = ???

  override def minus(x: Complex, y: Complex): Complex = ???

  override def compare(x: Complex, y: Complex): Int = ???
}

calculate2(new Complex(1), new Complex(2))


def bean[A: Manifest](f: A => Unit): A = {
  val a = implicitly[Manifest[A]].runtimeClass.newInstance.asInstanceOf[A]
  f(a)
  a
}
println(bean[Date](d => d.setTime(0)))


trait Match[A] {
  def getTypeName: String
}

implicit object StringMatch extends Match[String] {
  override def getTypeName: String = "String"
}

implicit object ListStringMatch extends Match[List[String]] {
  override def getTypeName: String = "List[String]"
}

def strange[A: Match](arg: A) = {
  implicitly[Match[A]].getTypeName
}

println(strange("Trololo"))
println(strange(List("Trololo")))
//    strange(1)