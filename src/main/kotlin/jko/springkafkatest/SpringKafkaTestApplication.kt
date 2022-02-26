package jko.springkafkatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKafkaTestApplication

fun main(args: Array<String>) {
    runApplication<SpringKafkaTestApplication>(*args)
}
