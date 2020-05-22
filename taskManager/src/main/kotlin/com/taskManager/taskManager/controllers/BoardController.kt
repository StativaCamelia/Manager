package com.taskManager.taskManager.controllers

import com.taskManager.taskManager.services.BoardService
import model.Board
import model.Task
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/board")

class BoardController(private val boardService: BoardService){

    @PostMapping
    fun insert(@RequestBody board: Board): Board = boardService.insert(board)

    @GetMapping
    fun getAll(): List<Board> = boardService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: String) : Optional<Board> = boardService.getById(id)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) : Unit = boardService.delete(id)

    @GetMapping("/tasks/{name}")
    fun getTasks(@PathVariable name: String) : List<Task> = boardService.getTasks(name)

}