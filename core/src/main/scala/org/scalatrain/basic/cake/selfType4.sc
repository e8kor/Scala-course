trait CrudEntity_3[E <: CrudEntity_3[E]] {
  def create(entityData: String): E

  def read(id: String): Option[E]

  def update(f: E => E): E

  def delete(id: String): Unit
}

case class Apple(name: String, age: Int) extends CrudEntity_3[Apple]

case class Bird(name: String, hobby: String) extends CrudEntity_3[Bird]

case class Orange(name: String, age: Int) extends CrudEntity_3[Apple]