import java.{util => ju}

import org.scalatrain.basic.third._

//    val j: ju.Collection[ju.Collection[Int]] = new ju.ArrayList[ju.ArrayList[Int]])()
val ls: Traversable[Traversable[Int]] = List(List(1))

class Base[+A, -B] {
  //      def op(arg: A) = println(arg)
  //      def get: B = ???
}

val a: Base[Animal, Cat] = new Base[Cat, Animal]

// Self type and generics
trait User {
  def name: String = getClass.getSimpleName
}

trait Tweeter[A <: User] {
  user: A =>
  def tweet[C](msg: C) = println(s"${user.name}: $msg")

  def as[C] = this.asInstanceOf[C]
}

// trait Wrong extends Tweeter
val user = new User with Tweeter[User]
user.tweet[String]("asdf")