package org.scalatrain.basic.third


class Animal {
  println(s"I'm Animal constructor")
}

trait Furry extends Animal {
  println(s"I'm Furry constructor")
}

trait HasLegs extends Animal {
  println(s"I'm HasLegs constructor")
}

trait HasHands {
  println(s"I'm HasHands constructor")
}

trait FourLegged extends HasLegs {
  println(s"I'm FourLegged constructor")
}

trait TwoLegged extends HasLegs {
  println(s"I'm TwoLegged constructor")
}

class Cat extends Animal with FourLegged with Furry {
  println(s"I'm Cat constructor")
}

class Config(c: Map[String, String]) {
  def get(n: String) = c(n)
}

trait User {
  def name: String = getClass.getSimpleName
}

trait Tweeter {
  user: User =>

  def tweet(msg: String) = println(s"$name: $msg")
}