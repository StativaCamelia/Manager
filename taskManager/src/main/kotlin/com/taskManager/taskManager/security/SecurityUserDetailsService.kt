package com.taskManager.taskManager.security

import com.taskManager.taskManager.daoLayer.UserDAO
import com.taskManager.taskManager.exceptionhandlers.ResourceNotFoundException
import model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class SecurityUserDetailsService @Autowired
constructor(private val userDAO: UserDAO) : UserDetailsService {
    @Throws(Exception:: class)
    override fun loadUserByUsername(username: String): UserDetails {
        if(!userDAO.existsByUsername(username))
            throw ResourceNotFoundException("User-ul nu exista")
        val user: User = userDAO.findByUsername(username)
        return SecurityUserDetails(user)
    }
}