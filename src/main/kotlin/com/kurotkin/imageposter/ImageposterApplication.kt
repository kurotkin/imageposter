package com.kurotkin.imageposter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ImageposterApplication

fun main(args: Array<String>) {
    runApplication<ImageposterApplication>(*args)
}
