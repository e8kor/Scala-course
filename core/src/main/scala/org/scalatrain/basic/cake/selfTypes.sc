import java.util.Date

trait T[U <: T[U]]

case class Apple(name: String, price: Double) {
  def create(entityData: String): Apple

  def read(id: String): Option[Apple]

  def update(f: Apple => Apple): Apple

  def delete(id: String): Unit
}

case class Bird(name: String, birthday: Date) {
  def create(entityData: String): Bird

  def read(id: String): Option[Bird]

  def update(f: Bird => Bird): Bird

  def delete(id: String): Unit
}

