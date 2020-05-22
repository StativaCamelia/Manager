package model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
data class User(@Id var id: String? = null, val email: String, var username: String, val password: String, val roles: MutableList<String> = mutableListOf("USER")){


}
