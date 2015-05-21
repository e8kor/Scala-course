import scala.concurrent.Future
import scala.language.higherKinds

trait DepValue {
  type V
  val value: V
}

def magic(that: DepValue): that.V = that.value
def mk[T](x: T) = new DepValue {
  type V = T
  val value = x
}
val depInt = mk(1)
val depString = mk("two")
val itWorks: Int = magic(depInt)
val again: String = magic(depString)

trait Foo {

  class Bar

  def doNothing(b: Bar) {}
}

val f1 = new Foo {}
val b1 = new f1.Bar()
val f2 = new Foo {}
val b2 = new f2.Bar()
f1.doNothing(b1)

//fine
//f1.doNothing(b2) //won't compile
// =============================
trait Inner[F] {

  type T

}

object Inner {

  type Aux[F, A] = Inner[F] {type T = A}

  def apply[F](implicit inner: Inner[F]) = inner

  implicit def mk[F[_], A]: Inner[F[A]] with Object {type T = A} = new Inner[F[A]] {
    type T = A
  }
}

trait IsFuture[F] {
  type T

  def apply(f: F): Future[T]
}

object IsFuture {
  def apply[F](implicit isf: IsFuture[F]) = isf

  implicit def mk[A] = new IsFuture[Future[A]] {
    type T = A

    override def apply(f: Future[A]): Future[T] = f
  }
}

// =================


trait Apart[F] {
  type T
  type W[X]

  def apply(f: F): W[T]
}


object Apart {
  def apply[F](implicit apart: Apart[F]) = apart

  implicit def mk[F[_], A] = new Apart[F[A]] {
    type T = A
    type W[X] = F[X]

    def apply(f: F[T]): W[T] = f
  }
}

// ======================

trait Demo[F[_]] {
  type W[X] = F[X]
  type Ignore[X] = F[Int]
  type Identity[X] = X
  type Const[X] = Int
}

object Apart2 {
  def apply[F](implicit apart: Apart[F]) = apart

  type Aux[FA, A, F[_]] = Apart[FA] {type T = A; type W[X] = F[X]}

  implicit def mk[F[_], A] = new Apart[F[A]] {
    type T = A
    type W[X] = F[X]

    override def apply(f: F[A]): W[T] = f
  }
}

//========================

import scalaz.{Functor, Monoid}

def mapZero[Thing, F[_], A](thing: Thing)
                           (implicit apart: Apart2.Aux[Thing, A, F],
                            f: Functor[F],
                            m: Monoid[A]): F[A] =
  f.map(apart(thing))(_ => m.zero)

// =============================
trait EApply[T, F, G] {
  type Out

  def apply(t: T, f: F, g: G): Out
}

object ApplyEither {
  def apply[T, F, G](t: T, f: F, g: G)
                    (implicit ea: EApply[T, F, G]): ea.Out = ea.apply(t, f, g)
}

object EApply extends LowPriorityEApply {
  def apply[T, F, G](implicit ea: EApply[T, F, G]) = ea

  implicit def fapply[Argument, Func, Case] = new EApply[Argument, Argument => Func, Case] {
    type Out = Func

    def apply(t: Argument, f: Argument => Func, g: Case): Out = f(t)
  }

}

trait LowPriorityEApply {
  implicit def gapply[Argument, Case, Func] = new EApply[Argument, Func, Argument => Case] {
    type Out = Case

    def apply(t: Argument, f: Func, g: Argument => Case): Out = g(t)
  }
}

val out = ApplyEither(1, { x: Int => 42 }, { x: Double => "no" })
assert(out == 42)
val out2 = ApplyEither("String", { x: Int => 42 }, { x: String => "no" })
assert(out2 == "no")

// =======================

case class Foo2[V](value: V)

// TODO need to understand ow to provide proper AUX type
//def zero[T, A](t: T)(implicit inner: Inner.Aux[T, A],
//                     m: Monoid[A]): inner.T = m.zero

//zero(Foo2(Foo2(1))) //won't compile!

// =============
trait Unwrap[F] {
  type Inner
}

object Unwrap extends LowPriorityUnwrap {
  def apply[F](implicit unwrap: Unwrap[F]) = unwrap

  implicit def nested[F[_], G](implicit unwrap: Unwrap[G]) =
    new Unwrap[F[G]] {
      type Inner = unwrap.Inner
    }
}

trait LowPriorityUnwrap {
  implicit def bottom[F[_], A] =
    new Unwrap[F[A]] {
      type Inner = A
    }
}

// TODO need to understand ow to provide proper AUX type
//def zero[T, A](t: T)(implicit unwrap: Unwrap.Aux[T, A],
//                     m: Monoid[A]): inner.T = m.zero
//
//val out3 = zero(Foo2(Foo2(1)))
//assert(out == 0)


def annoy[A](that: Future[List[Set[Int]]],
             f: Int => A): Future[List[Set[A]]] =
  that map {
    _ map {
      _ map f
    }
  }

object MapIt {
  def apply[A, B, C](in: A, f: B => C)
                    (implicit mapper: Mapper[A, B, C]): mapper.Out = mapper(in, f)
}

trait Mapper[A, B, C] {
  type Out

  def apply(a: A, f: B => C): Out
}