package name.snosminin.postingservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import name.snosminin.postingservice.events.model.TestEventModel;
import name.snosminin.postingservice.helper.ProducerRecordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final KafkaTemplate<String, TestEventModel> kafkaTemplate;

    @Autowired
    public TestController(KafkaTemplate<String, TestEventModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping
    @Operation(summary = "Send hello")
    public ResponseEntity<String> send() {
        var model = new TestEventModel("hello");
        var record =
                ProducerRecordHelper.getProducerRecord("test-topic", model);
        kafkaTemplate.send(record);
        return new ResponseEntity<>("hello from posting service", HttpStatus.OK);
    }
}