package com.example.lab06.repository;

import com.example.lab06.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

    public String updateWizard(String _id, Wizard updateWizard) {
        Optional<Wizard> findingWizard = repository.findById(_id);
        if (findingWizard.isPresent()) {
            Wizard wizard = findingWizard.get();
            wizard.setName(updateWizard.getName());
            wizard.setHouse(updateWizard.getHouse());
            wizard.setSchool(updateWizard.getSchool());
            wizard.setMoney(updateWizard.getMoney());
            wizard.setSex(updateWizard.getSex());
            wizard.setPosition(updateWizard.getPosition());
            repository.save(wizard);
            return "Updated Successfully";
        }
        return "Wizard not found";
    }

    public String deleteWizard(String _id) {
        Optional<Wizard> findingWizard = repository.findById(_id);
        if (findingWizard.isPresent()) {
            repository.deleteById(_id);
            return "Deleted Successfully";
        }
        return "Wizard not found";
    }
}