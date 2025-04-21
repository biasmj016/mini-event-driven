package com.biasmj.producer.controller;

import com.biasmj.producer.controller.dto.ProducerRequest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerApiController {

    private final StreamBridge streamBridge;

    public ProducerApiController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping("/api/publish")
    public ResponseEntity<String> publish(@RequestBody ProducerRequest request) {
        boolean sent = streamBridge.send("output-out-0", request.message());
        if (sent) {
            return ResponseEntity.ok("Sent → " + request.message());
        } else {
            return ResponseEntity.status(500).body("Failed to send");
        }
    }
}