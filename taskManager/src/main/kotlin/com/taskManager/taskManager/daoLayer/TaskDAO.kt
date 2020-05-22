package com.taskManager.taskManager.daoLayer

import model.Task
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import javax.print.attribute.standard.OutputDeviceAssigned

@Repository
interface TaskDAO: MongoRepository<Task, String> {
    fun existsByTitle(title: String): Boolean
    fun findByStatus(status: Task.Status): List<Task>
    fun findByTaskType(taskType: Task.TaskTypes): List<Task>
    fun findByAssignedTo(assignedTo: String): List<Task>
    fun findByBoardName(boardName: String): List<Task>
}