package com.example.appNotificador.controller;

import com.example.appNotificador.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotifyController {

    private final NotificationService service;

    public NotifyController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/channel")
    public String channel() {
        return service.channel();
    }

    @PostMapping
    public String send(@RequestParam String to, @RequestParam String msg) {
        service.notify(to, msg);
        return "Canal activo: " + service.channel();
    }
}
