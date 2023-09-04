package com.example.sop_lab_4;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Cashier {
    HashMap<String, Integer> jsonObject = new HashMap<>();

    @RequestMapping(value = "getChange/{money}", method = RequestMethod.GET)
    public HashMap<String, Integer> getChange(@PathVariable("money") int money) {
        int[] banks = {1000, 500, 100, 20, 10, 5, 1};
        for (int bank : banks) {
            int change = money / bank;
            money -= bank * change;
            jsonObject.put("b" + bank, change);
        }
        return jsonObject;
    }
}
