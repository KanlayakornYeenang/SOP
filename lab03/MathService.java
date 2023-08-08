package com.example.lab03;

import org.springframework.web.bind.annotation.*;

@RestController
public class MathService {
    @RequestMapping(path = "/add/{num1:[0-9]}/{num2:[0-9]}", method = RequestMethod.GET)
    public double add(@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1+num2;
    }

    @RequestMapping(path = "/minus/{num1:[0-9]}/{num2:[0-9]}", method = RequestMethod.GET)
    public double minus(@PathVariable("num1") double num1, @PathVariable("num2") double num2) {
        return num1-num2;
    }

    @RequestMapping(path = "/multiply", method = RequestMethod.GET)
    public double multiply(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        return num1*num2;
    }

    @RequestMapping(path = "/divide", method = RequestMethod.GET)
    public double divide(@RequestParam("num1") double num1, @RequestParam("num2") double num2) {
        return num1/num2;
    }
}
