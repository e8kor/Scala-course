
trait CrudEntity {
  def create(entityData: String): CrudEntity = ???

  def read(id: String): Option[CrudEntity] = ???

  def update(f: CrudEntity => CrudEntity): CrudEntity = ???

  def delete(id: String): Unit = ???
}

case class Apple(name: String, age: Int) extends CrudEntity

case class Bird(name: String, hobby: String) extends CrudEntity