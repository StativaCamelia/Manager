package com.taskManager.taskManager.exceptionhandlers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class FileExists : RuntimeException {
    constructor() : super() {}
    constructor(message: String, cause: Throwable) : super(message, cause) {}
    constructor(message: String) : super(message) {}
    constructor(cause: Throwable) : super(cause) {}
}