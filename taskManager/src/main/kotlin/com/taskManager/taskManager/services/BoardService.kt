package com.taskManager.taskManager.services

import com.taskManager.taskManager.daoLayer.BoardDAO
import com.taskManager.taskManager.daoLayer.TaskDAO
import com.taskManager.taskManager.exceptionhandlers.ResourceDuplicate
import com.taskManager.taskManager.exceptionhandlers.ResourceNotFoundException
import model.Board
import model.Task
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
class BoardService(val boardDAO: BoardDAO, val taskDAO: TaskDAO){
    fun insert(board: Board): Board {
        if(!boardDAO.existsByName(board.name)){
            return boardDAO.save(board)

        }
        throw ResourceDuplicate("Exista deja un Board cu acelasi titlu");
    }

    fun getAll() : List<Board> = boardDAO.findAll()

    fun getById(id: String) : Optional<Board> = boardDAO.findById(id)

    fun delete(id: String) : Unit = boardDAO.deleteById(id)

    fun getTasks(name: String): List<Task> = taskDAO.findByBoardName(name)


}