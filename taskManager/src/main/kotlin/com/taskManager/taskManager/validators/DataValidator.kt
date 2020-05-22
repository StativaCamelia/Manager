package com.taskManager.taskManager.validators

import sun.security.util.Password
import java.util.regex.Matcher
import java.util.regex.Pattern

class DataValidator(){

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
        ).matcher(password).matches()
    }
}