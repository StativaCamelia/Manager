package com.taskManager.taskManager.services

import com.taskManager.taskManager.daoLayer.BoardDAO
import com.taskManager.taskManager.daoLayer.TaskDAO
import com.taskManager.taskManager.daoLayer.UserDAO
import com.taskManager.taskManager.exceptionhandlers.ResourceDuplicate
import com.taskManager.taskManager.exceptionhandlers.ResourceNotFoundException
import model.Task
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService(val taskDAO: TaskDAO, val userDAO: UserDAO, val boardService: BoardService, val boardDAO: BoardDAO){
    fun getAll(): List<Task> = taskDAO.findAll()

    fun insert(task: Task): Task {
        if(taskDAO.existsByTitle(task.title))
            throw ResourceDuplicate("Exista deja un Task cu acelasi titlu");
        if(!task.assignedTo.isNullOrEmpty()) {
            if(!userDAO.existsByUsername(task.assignedTo!!)){
                throw ResourceNotFoundException("Nu exista acest utilizator in lista de utilizatori")
            }
        }
        if(!task.boardName.isNullOrEmpty()){
            if(!boardDAO.existsByName(task.boardName!!)){
                throw ResourceNotFoundException("Nu exista un board cu acest nume")
            }

        }
        return taskDAO.save(task)

    }

    fun deleteById(id: String): Optional<Task> {
        return taskDAO.findById(id).apply {
            this.ifPresent {
                taskDAO.delete(it)
            }
        }
    }

    fun deleteAll(): Unit = taskDAO.deleteAll()


    fun getById(id: String): Optional<Task>  {
        if(taskDAO.existsById(id))
            return taskDAO.findById(id)
        throw ResourceNotFoundException("Task-ul nu exista")

    }

    @Throws(Exception::class)
    fun updateTask(task : Task): Task {
        if(taskDAO.existsById(task.id!!)) {
            if(!task.assignedTo.isNullOrEmpty()) {
                if(!userDAO.existsByUsername(task.assignedTo!!)){
                    throw ResourceNotFoundException("Nu exista acest utilizator in lista de utilizatori")
                }
            }
            if(!task.boardName.isNullOrEmpty()){
                if(!boardDAO.existsByName(task.boardName!!)){
                    throw ResourceNotFoundException("Nu exista un board cu acest nume")
                }
            }
            if (!taskDAO.existsByTitle(task.title))
                    return taskDAO.save(task)
            else throw ResourceDuplicate("Exista deja un task cu accest nume")
            }
        else
            throw ResourceNotFoundException("Task-ul nu exista")
    }


    @Throws(Exception::class)
    fun getByUser(assignedTo: String): List<Task>{
        if(!userDAO.existsByUsername(assignedTo))
            throw ResourceNotFoundException("User-ul nu exista")
        return taskDAO.findByAssignedTo(assignedTo)
    }


    fun getByStatus(status: Task.Status): List<Task> = taskDAO.findByStatus(status)

    fun getByType(taskType: Task.TaskTypes): List<Task> = taskDAO.findByTaskType(taskType)

    @Throws(Exception::class)
    fun getEstimation(username: String): Int{
        var count = 0
        if(userDAO.existsByUsername(username)){
       val tasks : List<Task> = taskDAO.findByAssignedTo(username)
        for(task in  tasks)
            if(task.estimatedTime != null)
            count += task.estimatedTime!!
        return count
        }
        throw ResourceNotFoundException("User-ul nu exista")

    }

}
