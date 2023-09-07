package com.example.lab06.controller;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import com.example.lab06.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addWizard(@RequestBody Wizard wizard)  {
        String response = wizardService.saveWizard(wizard);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value ="/updateWizard/{_id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateWizard(@PathVariable("_id") String _id, @RequestBody Wizard wizard) {
        String response = wizardService.updateWizard(_id, wizard);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value ="/deleteWizard/{_id}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWizard(@PathVariable("_id") String _id) {
        String response = wizardService.deleteWizard(_id);
        return ResponseEntity.ok(response);
    }
}
