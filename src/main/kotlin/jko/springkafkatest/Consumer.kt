package jko.springkafkatest

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class Consumer(
    private val countDownLatch: CountDownLatch = CountDownLatch(1)
) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    private var message: String? = null

    @KafkaListener(topics = ["jko-topic"])
    fun consume(consumerRecord: ConsumerRecord<String, String>) {
        logger.info("Consumed, (record: $consumerRecord)")

        message = consumerRecord.value()
        countDownLatch.countDown()
    }

    fun await() = countDownLatch.await()

    fun equalsConsumedMessageWith(message: String) = this.message == message
}
