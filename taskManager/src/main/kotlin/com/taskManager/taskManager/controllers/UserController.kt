package com.taskManager.taskManager.controllers

import com.taskManager.taskManager.JWT.JWTLogin
import com.taskManager.taskManager.services.UserService
import model.User
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("api/user")
class UserController(private val userService: UserService){
    @PostMapping("/register")
    fun register(@Valid @RequestBody user: User): User  = userService.register(user)

}
