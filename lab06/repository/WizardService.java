package com.example.lab06.repository;

import com.example.lab06.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Wizard> retrieveWizards() {
        return (ArrayList<Wizard>) repository.findAll();
    }

    public String saveWizard(Wizard wizard) {
        repository.save(wizard);
        return "Added Successfully";
    }
}