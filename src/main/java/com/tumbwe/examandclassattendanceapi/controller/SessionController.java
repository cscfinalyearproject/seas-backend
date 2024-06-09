package com.tumbwe.examandclassattendanceapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

//    private final WebClient.Builder webClientBuilder;
//    webClientBuilder.build()
//            .get()
//                .uri(externalAPIUrl)
//                .retrieve()
//                .bodyToMono(String.class);

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> downloadStudents(@RequestParam String course, @RequestParam String sessionType) {
//        String externalAPIUrl = "https://dummyjson.com/quotes";
        return ResponseEntity.ok("Hello");
    }
}
