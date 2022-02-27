package jko.springkafkatest

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Producer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    fun produce(topic: String, message: String) {
        logger.info("Produced, (message: $message), (topic: $topic)")

        kafkaTemplate.send(topic, message)
    }
}
