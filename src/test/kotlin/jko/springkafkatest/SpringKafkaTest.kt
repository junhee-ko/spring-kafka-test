package jko.springkafkatest

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092"])
internal class SpringKafkaTest(
    @Autowired private val consumer: Consumer,
    @Autowired private val producer: Producer
) {

    @Test
    internal fun `embedded kafka 를 사용할 때, producer 가 메세지를 보내면 consumer 는 메세지를 받는다`() {
        // given
        val topic = "jko-topic"
        val message = "Messi is the best"

        // when
        producer.produce(topic, message)

        // then
        consumer.await()
        assertTrue(consumer.equalsConsumedMessageWith(message))
    }
}
