sealed trait Foo

sealed trait ReadableFoo extends Foo {
  def field: Int
}

sealed trait OReadableFoo extends ReadableFoo {
  override def field: Int = 0
}

trait NonReadableFoo extends Foo

object NonReadableFoo extends NonReadableFoo

object OReadableFoo extends OReadableFoo

trait BarA[+F] {

  def foo: F

  def readField(implicit evidence: F <:< ReadableFoo) = evidence(foo) field
}

case class BarAI[+FooF](override val foo: FooF) extends BarA[FooF]

val a = new BarAI(NonReadableFoo)

//a.readField

val b = new BarAI(OReadableFoo)

b.readField

case class Grill[+F <: Foo, +B <: BarA[F]](bar: B) {
  def readField(implicit evidence: F <:< ReadableFoo) = bar readField
}

trait GrillA[+B] {

  def bar: B

  def readField(implicit evidence: B <:< BarA[ReadableFoo])

}

//case class Grill1[+B <: BarA[_]](bar: B) {
//  def readField(implicit evidence: B <:< BarA[ReadableFoo]) = bar.readField
//}

case class Grill2[+B <: BarA[_]](bar: B) extends GrillA[B] {
  override def readField(implicit evidence: <:<[B, BarA[ReadableFoo]]): Unit = evidence(bar) readField
}

case class Grill3[+B <: BarA[_]](bar: B) extends GrillA[B] {
  override def readField(implicit evidence: B <:< BarA[ReadableFoo]) = implicitly[BarA[ReadableFoo]](bar) readField
}

case class Grill4[+B <: BarA[_]](bar: B) extends GrillA[B] {
  override def readField(implicit evidence: B <:< BarA[ReadableFoo]) = (bar: BarA[ReadableFoo]) readField
}