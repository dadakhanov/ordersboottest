package com.example.demo.controllers;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public Iterable<Order> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Order findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping(value = "/",consumes = "application/json")
    public Order create(@RequestBody String body){
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(body);
        int customerId =  Integer.parseInt((String)map.get("customer_id"));

        return service.create(customerId);
    }

}
