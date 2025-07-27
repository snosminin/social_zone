package name.snosminin.postingservice.helper;

import jakarta.validation.constraints.NotNull;
import name.snosminin.postingservice.utils.CorrelationIdContextHolder;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.KafkaHeaders;

import java.nio.charset.StandardCharsets;

public class ProducerRecordHelper {

    public static <V> ProducerRecord<String, V> getProducerRecord(@NotNull String topic, @NotNull V data) {
        ProducerRecord<String, V> record = new ProducerRecord<>(topic, data);
        record
                .headers()
                .add(KafkaHeaders.CORRELATION_ID,
                        CorrelationIdContextHolder.getContext().getCorrelationId()
                                .getBytes(StandardCharsets.UTF_8));

        return record;
    }
}
