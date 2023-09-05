package com.example.lab06.view;

import com.example.lab06.pojo.Wizard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route("mainPage.it ")
public class MainWizardView extends VerticalLayout {
    WebClient webClient = WebClient.create();
    TextField fullname = new TextField();
    RadioButtonGroup gender = new RadioButtonGroup("Gender :");
    ComboBox position = new ComboBox();
    NumberField dollars = new NumberField("Dollars");
    ComboBox school = new ComboBox();
    ComboBox house = new ComboBox();
    int current = -1;

    public MainWizardView() {
        fullname.setPlaceholder("Fullname");
        gender.setItems("Male", "Female");
        position.setPlaceholder("Position");
        position.setItems("Student", "Teacher");
        dollars.setPrefixComponent(new Span("$"));
        school.setPlaceholder("School");
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        house.setPlaceholder("House");
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");

        HorizontalLayout hl = new HorizontalLayout();
        Button leftBtn = new Button("<<");
        Button createBtn = new Button("Create");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button rightBtn = new Button(">>");
        hl.add(leftBtn, createBtn, updateBtn, deleteBtn, rightBtn);

        ArrayList<Wizard> response = webClient.get().uri("http://localhost:8081/wizards").retrieve().bodyToMono(ArrayList.class).block();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Wizard> wizards = mapper.convertValue(response, new TypeReference<ArrayList<Wizard>>() {});

        rightBtn.addClickListener(buttonClickEvent -> {
            current++;
            if (current >= wizards.size()) { current = 0; }
            this.setValue(wizards.get(current));
        });

        this.add(fullname, gender, position, dollars, school, house, hl);
    }

    public void setValue(Wizard wizard) {
        fullname.setValue(wizard.getName());
        String gender;
        if (wizard.getSex().equals("m")) {
            gender = "Male";
        }
        else {
            gender = "Female";
        }
        this.gender.setValue(gender);
        String position = wizard.getPosition();
        this.position.setValue(position.substring(0,1).toUpperCase()+position.substring(1, position.length()));
        dollars.setValue(Double.valueOf(wizard.getMoney()));
        school.setValue(wizard.getSchool());
        house.setValue(wizard.getHouse());
    }
}
