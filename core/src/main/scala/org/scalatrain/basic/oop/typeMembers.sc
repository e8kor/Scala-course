import org.scalatrain.basic.oop._

trait List {
  type Item <: Animal
  type Mapping[A] = Map[A, Item]

  class Dependent

  // Path dependent type
  def create = new Dependent

  def printDependent(i: Dependent) = println(i)

}

class CatList extends List {
  type Item = Cat
  type IntMapping = Mapping[Int]

  def getItem = new Cat
}

val l1 = new CatList
val l2 = new CatList
l2.printDependent(l2.create)
//    l2.printDependent(l1.create)