package org.scalatrain.basic.four

/**
 * Created by evgeniikorniichuk on 25/06/15.
 */
class CakePatternDITutorial {

  case class User(firstName: String, lastName: String, supervisorId: Int, email: String)

  trait UserRepository {
    def get(id: Int): User

    def find(username: String): User
  }

  trait Users {

    def getUser(id: Int)(implicit userRepository: UserRepository) = {
      userRepository.get(id)
    }

    def findUser(username: String)(implicit userRepository: UserRepository) = {
      userRepository.find(username)
    }
  }

  object UserInfo extends Users {

    def userEmail(id: Int)(implicit userRepository: UserRepository) = {
      getUser(id).email
    }

    def userInfo(username: String)(implicit userRepository: UserRepository) = {
      val user = findUser(username)
      val boss = getUser(user.supervisorId)
      Map(
        "fullName" -> s"${user.firstName} ${user.lastName}",
        "email" -> s"${user.email}",
        "boss" -> s"${boss.firstName} ${boss.lastName}"
      )
    }
  }
}
