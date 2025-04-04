package xyz.sadiulhakim.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class EndpointController {
    @Value("${site.name:''}")
    private String siteName;

    @GetMapping("/ping")
    public String pong() {
        return "Pong from " + siteName;
    }
}
