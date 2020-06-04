package com.taskManager.taskManager.services

import com.taskManager.taskManager.exceptionhandlers.FileExists
import model.Task
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Path
import java.util.*


@Service
class FileService{

    private val fileStorageLocation: Path? = null

    @Throws(Exception::class)
    fun createFile(username: String): String{
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val fileName = "C:\\Users\\stati\\Desktop\\Manager\\taskManager\\resources\\${username}_${uuid}.txt"
        var file = File(fileName)
        val isNewFileCreated: Boolean = file.createNewFile()
        if (!isNewFileCreated) {
            throw FileExists("\"$fileName already exists.\"")
        }
        return fileName;
    }


    fun writeToFile(fileName: String, tasks:List<Task> ){
        val myfile = File(fileName)
        print(fileName)

        myfile.printWriter().use { out ->
            tasks.forEach{
                out.println("$it")
            }
        }
    }


}