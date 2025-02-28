package com.waa.waaproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exceptions")
public class ExceptionController {
    @GetMapping
    public void exception(){
        throw new RuntimeException("Exception");
    }
}
