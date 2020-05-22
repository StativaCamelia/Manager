package com.taskManager.taskManager.daoLayer

import model.Board
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardDAO: MongoRepository<Board, String> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Board

}