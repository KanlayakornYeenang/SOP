package com.example.lab03;

import org.springframework.web.bind.annotation.*;
import java.lang.Math;

@RestController
public class GeneratePasswordService {
    @RequestMapping(path = "{name:[a-z]+}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name) {
        return "Hi, " + name + "\n" + "Your new password is " + this.getPassword() + ".";
    }
    public String getPassword() {
        String password = "";
        int max = 9;
        int min = 1;
        for (int i = 0; i < 9; i++) {
            int rand = (int)(Math.random() * (max-min+1)) + min;
            password += String.valueOf(rand);
        }
        return password;
    }
}
