package com.taskManager.taskManager.controllers

import com.taskManager.taskManager.exceptionhandlers.ResourceNotFoundException
import com.taskManager.taskManager.services.TaskService
import model.Task
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Path
import java.util.*
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("api/task")

class TaskController(private val taskService: TaskService) {
    @GetMapping
    fun getAll(): List<Task> = taskService.getAll()

    @PostMapping
    fun insert(@RequestBody task: Task): Task = taskService.insert(task)

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: String): Optional<Task> = taskService.deleteById(id)

    @DeleteMapping
    fun deleteAll(): Unit = taskService.deleteAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): Optional<Task> = taskService.getById(id)

    @PutMapping
    fun update(@RequestBody task: Task): Task = taskService.updateTask(task)

    @GetMapping("/byStatus/{status}")
    fun getByStatus(@PathVariable status: Task.Status) : List<Task> = taskService.getByStatus(status)

    @GetMapping("/byType/{taskType}")
    fun getByType(@PathVariable taskType: Task.TaskTypes) : List<Task> = taskService.getByType(taskType)

    @GetMapping("/byUser/{username}")
    fun getByUsername(@PathVariable username: String) : List<Task> = taskService.getByUser(username)

    @GetMapping("/estimation/{username}")
    fun getUserEstimation(@PathVariable username: String): Int = taskService.getEstimation(username)

    @GetMapping("/byUser/download/{username}")
    fun downloadFile(@PathVariable username: String): List<Task> = taskService.downloadFile(username)

}