package com.example.lab06.repository;

import com.example.lab06.pojo.Wizard;
import com.example.lab06.pojo.Wizards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface WizardRepository extends MongoRepository<Wizard, String> {

}
