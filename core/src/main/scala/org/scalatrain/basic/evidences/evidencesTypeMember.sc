sealed trait Foo

sealed trait ReadableFoo extends Foo {
  def field: Int
}


object ReadableFoo extends ReadableFoo {
  override def field: Int = 0
}

trait NonReadableFoo extends Foo

object NonReadableFoo extends NonReadableFoo

sealed trait BarM {

  type F

  def foo: F

  def readField(implicit ev: F <:< ReadableFoo) = ev(foo).field
}

case class BarMI(override val foo: BarMI#F) extends BarM {
  override type F <: ReadableFoo
}


sealed trait GrillT {

  type B

  def bar: B

  def readBarField(implicit ev1: B <:< BarM) = ev1(bar).readField

}

case class Grill5(bar: Grill5#B) extends GrillT {

  override type B <: BarM

}

//case class Grill6(override val bar: Grill6#B) extends GrillT {
//  override type B <: BarM
//}

