package com.example.sop_lab_4;

import org.springframework.web.bind.annotation.*;

@RestController
public class MathAPI {
    @RequestMapping(value = "plus/{n1}/{n2}", method = RequestMethod.GET)
    public Double myPlus(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1+n2;
    }
    @RequestMapping(value = "minus/{n1}/{n2}", method = RequestMethod.GET)
    public Double myMinus(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1-n2;
    }
    @RequestMapping(value = "divide/{n1}/{n2}", method = RequestMethod.GET)
    public Double myDivide(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1/n2;
    }
    @RequestMapping(value = "multi/{n1}/{n2}", method = RequestMethod.GET)
    public Double myMulti(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1*n2;
    }
    @RequestMapping(value = "mod/{n1}/{n2}", method = RequestMethod.GET)
    public Double myMod(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1%n2;
    }
    @RequestMapping(value = "max/{n1}/{n2}", method = RequestMethod.GET)
    public Double myMax(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return Math.max(n1, n2);
    }
}
