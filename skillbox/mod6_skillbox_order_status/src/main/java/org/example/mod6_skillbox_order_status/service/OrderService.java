package org.example.mod6_skillbox_order_status.service;

import lombok.extern.log4j.Log4j2;
import org.example.mod6_skillbox_order_status.dto.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class OrderService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic")
    public void listenOrder(OrderDTO request) {
        log.info("Received order: {}", request);
        sendInfo(request);
    }

    public void sendInfo(OrderDTO request) {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        var status = List.of("CREATED", "PROCESS").get(new Random().nextInt(3));
        stringBuilder.append(status);
        stringBuilder.append(" time: ");
        stringBuilder.append(format.format(LocalDate.now()));
        kafkaTemplate.send("order-status-topic", stringBuilder.toString());
    }
}
