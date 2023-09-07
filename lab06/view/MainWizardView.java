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
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
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
    ArrayList<Wizard> wizards;
    int current = 0;

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

        wizards = this.getWizards();

        this.setTextField(wizards.get(current));

        rightBtn.addClickListener(buttonClickEvent -> { this.forward(); });

        leftBtn.addClickListener(buttonClickEvent -> { this.backward(); });

        createBtn.addClickListener(buttonClickEvent -> {
           Wizard wizard = new Wizard(String.valueOf(gender.getValue()), fullname.getValue(), String.valueOf(school.getValue()), String.valueOf(house.getValue()), String.valueOf(dollars.getValue()), String.valueOf(position.getValue()));
           String output = String.valueOf(webClient.post().uri("http://localhost:8080/addWizard").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(wizard)).retrieve().bodyToMono(String.class).block());;
           wizards = this.getWizards();
           current = getWizards().size()-1;
           System.out.println(output);
        });

        updateBtn.addClickListener(buttonClickEvent -> {
            Wizard wizard = new Wizard(String.valueOf(gender.getValue()), fullname.getValue(), String.valueOf(school.getValue()), String.valueOf(house.getValue()), String.valueOf(dollars.getValue()), String.valueOf(position.getValue()));
            String output = String.valueOf(webClient.post().uri("http://localhost:8080/updateWizard/"+wizards.get(current).get_id()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(wizard)).retrieve().bodyToMono(String.class).block());;
            wizards = this.getWizards();
            System.out.println(output);
        });

        deleteBtn.addClickListener(buttonClickEvent -> {
            String output = String.valueOf(webClient.post().uri("http://localhost:8080/deleteWizard/"+wizards.get(current).get_id()).retrieve().bodyToMono(String.class).block());
            wizards = this.getWizards();
            this.backward();
            System.out.println(output);
        });

        this.add(fullname, gender, position, dollars, school, house, hl);
    }

    public ArrayList<Wizard> getWizards() {
        ArrayList<Wizard> response = webClient.get().uri("http://localhost:8080/wizards").retrieve().bodyToMono(ArrayList.class).block();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Wizard> wizards = mapper.convertValue(response, new TypeReference<ArrayList<Wizard>>() {});
        return wizards;
    }

    public void forward() {
        current++;
        if (current >= wizards.size()) { current = wizards.size()-1; }
        this.setTextField(wizards.get(current));
    }

    public void backward() {
        current--;
        if (current < 0) { current = 0; }
        this.setTextField(wizards.get(current));
    }

    public void setTextField(Wizard wizard) {
        fullname.setValue(wizard.getName());
        String gender;
        if (wizard.getSex().equals("m")) {gender = "Male";}
        else {gender = "Female";}
        this.gender.setValue(gender);
        String position = wizard.getPosition();
        this.position.setValue(position.substring(0,1).toUpperCase()+position.substring(1, position.length()));
        dollars.setValue(Double.valueOf(wizard.getMoney()));
        school.setValue(wizard.getSchool());
        house.setValue(wizard.getHouse());
    }
}
