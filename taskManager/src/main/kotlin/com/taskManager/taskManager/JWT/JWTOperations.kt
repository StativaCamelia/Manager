package com.taskManager.taskManager.JWT

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import model.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher.SECRET_KEY
import javax.xml.bind.DatatypeConverter




internal object JWTOperations {
    private val secret = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6ImNmM2FlZmM4LWUyNDMtNDQ5NS1hOTYyLWU1NTYzNDFhMTkzYiIsImlhdCI6MTU4ODk2MzQzNCwiZXhwIjoxNTg4OTY3MDM0fQ.m0yu"
    private val header = "Authorization"

    fun User.createJwt(): String {
        val signatureAlgorithm = SignatureAlgorithm.HS256
        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
        val claims = HashMap<String, Any>()
        claims.put("roles", this.roles)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(this.username)
                .signWith(signatureAlgorithm, signingKey).compact()
    }

    fun addToken(response: HttpServletResponse, user: User) {
        val jwt = user.createJwt()
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081")
        response.writer.write(jwt)
        response.writer.flush()
        response.writer.close()
    }

    fun getToken(request: HttpServletRequest): Authentication? {
        val signatureAlgorithm = SignatureAlgorithm.HS256
        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
        val token = request.getHeader(header) ?: return null
        val tokenBody = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .body
        val username: String = tokenBody.subject
        val roles = tokenBody["roles"] as List<String>
        val res = roles.mapTo(LinkedList<GrantedAuthority>()) { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(username, null, res)
    }
}