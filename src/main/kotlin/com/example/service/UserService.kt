package com.example.service

import com.example.model.User
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.PathVariable

class UserService {
    val userList = mutableListOf<User>()
    fun storeUser(user:User) : MutableList<User>
    {
        userList.add(user)
        return userList
    }
    fun displayUser() : List<User>{
        return userList
    }
//    fun displayUserById(id : Int) : HttpResponse<User>?{
//        val user = userList.find{ it.id==id}
//        return HttpResponse.ok(user)
//    }
}