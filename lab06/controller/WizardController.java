package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.example.lab06.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;

    @RequestMapping(value ="/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizards() {
        ArrayList<Wizard> wizard = wizardService.retrieveWizards();
        return ResponseEntity.ok(wizard);
    }

    @RequestMapping(value ="/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> addWizard(@RequestParam("sex") String sex, @RequestParam("name") String name, @RequestParam("school") String school, @RequestParam("house") String house, @RequestParam("money") String money, @RequestParam("position") String position) {
        Wizard wizard = new Wizard(sex, name, school, house, money, position);
        String response = wizardService.saveWizard(wizard);
        return ResponseEntity.ok(response);
    }
}
