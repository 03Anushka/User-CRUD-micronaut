package com.example.controller
import com.example.exeption.UserAlreadyPresentException
import com.example.exeption.UserNotFoundException
import com.example.model.User
import com.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated

import javax.validation.Valid

@Validated
@Controller("/user")
class UserName {
private var userList = listOf<User>()
    private val userService : UserService = UserService()
@Post("/")
fun createUser(@Body @Valid user : User) : HttpResponse<String> {
    val existingUser = userList.find { it.id == user.id }
    if (existingUser != null) {
       throw UserAlreadyPresentException()
    }
    userList = userService.storeUser(user)
    return HttpResponse.created("User Created Successfully")
}
    @Get("/")
    fun displayUser() : HttpResponse<List<User>>{
        return HttpResponse.ok(userService.displayUser())
    }
    @Get("/{id}")
    fun displayUserById(@PathVariable id : Int) : HttpResponse<User>{
      val user = userList.find{ it.id==id}
        if(user !=null)
        {
            return HttpResponse.ok(user)
        }
        else
        {

            throw UserNotFoundException()
        }
    }
    @Error(exception = UserNotFoundException::class)
     fun handleUserNotFoundException(e : UserNotFoundException):HttpResponse<String>
      {
        return HttpResponse.notFound("User is not present in the list")
      }

    @Error(exception = UserAlreadyPresentException::class)
    fun handleUserAlreadyPresentException(e:UserAlreadyPresentException):HttpResponse<String>{
        return HttpResponse.notFound("User is already present")
    }

    @Put("/{id}")
    fun updateById(@PathVariable id : Int,@Body updateUser : User) : HttpResponse<User>{
        val user = userList.find { it.id==id }
       return if (user != null)
        {
            user.id = updateUser.id
            user.name = updateUser.name
            user.age = updateUser.age

             HttpResponse.ok(user)
        }
        else {
           HttpResponse.notFound()
       }
    }

    @Delete("/{id}")
    fun deleteUser(@PathVariable id: Int): HttpResponse<String> {
        val deletedUser = userList.find { it.id == id }
        if (deletedUser != null) {
           val updateList =  userList.filterNot { it.id == id }
            userList = updateList
            return HttpResponse.ok("User with ID $id deleted successfully")
        }
        return HttpResponse.notFound("User not found for ID: $id")
    }

}