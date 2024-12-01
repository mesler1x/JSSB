package org.example.mod6_skillbox.controller;

import lombok.RequiredArgsConstructor;
import org.example.mod6_skillbox.dto.OrderDTO;
import org.example.mod6_skillbox.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/send")
    public void sendOrderEvent(@RequestBody OrderDTO request){
        orderService.send(request);
    }
}
