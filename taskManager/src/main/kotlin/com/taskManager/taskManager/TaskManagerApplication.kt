package com.taskManager.taskManager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2

class TaskManagerApplication : WebMvcConfigurerAdapter()

fun main(args: Array<String>) {
	runApplication<TaskManagerApplication>(*args)
}
