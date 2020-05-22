package com.taskManager.taskManager.services

import org.springframework.beans.factory.annotation.Autowired

import com.taskManager.taskManager.daoLayer.UserDAO
import com.taskManager.taskManager.exceptionhandlers.DataValidationFailure
import com.taskManager.taskManager.exceptionhandlers.ResourceDuplicate
import com.taskManager.taskManager.exceptionhandlers.ResourceNotFoundException
import com.taskManager.taskManager.validators.DataValidator
import model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserService(private @field:Autowired val userDAO: UserDAO){



    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Throws(Exception::class)
    fun register(user: User): User {
        val validator =  DataValidator()
        when {
            !userDAO.existsByEmail(user.email) -> when {
                !userDAO.existsByUsername(user.username) -> when {
                    validator.isEmailValid(user.email) -> when {
                        validator.isPasswordValid(user.password) -> {
                            var newUser = User(username = user.username, email = user.email, password = passwordEncoder!!.encode(user.password))
                            userDAO.save(newUser)
                            return newUser}
                        else -> throw DataValidationFailure("Parola introdusa nu este una valida")
                    }
                    else -> throw DataValidationFailure("Email-ul introdus nu este unul valid")
                }
                else -> throw ResourceDuplicate("Username-ul introdus exista deja")
            }
            else -> throw ResourceDuplicate("Email-ul introdus exista deja")
        }

    }
}