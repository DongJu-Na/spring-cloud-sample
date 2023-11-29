package com.example.controller;

import com.example.dto.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    public final Config config;

    @GetMapping("/config")
    public ResponseEntity<String> config(){
        System.out.println(config);
        return ResponseEntity.ok(config.toString());
    }
}
