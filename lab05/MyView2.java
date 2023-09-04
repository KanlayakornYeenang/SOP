package com.example.sop_lab_5;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "index2")
public class MyView2 extends FormLayout {
    WebClient webClient = WebClient.create();
    TextField addWord = new TextField("Add Word");
    Button addGoodWordBtn = new Button("Add Good Word");
    Button addBadWordBtn = new Button("Add Bad Word");
    ComboBox goodWord = new ComboBox("Good Words");
    ComboBox badWord = new ComboBox("Bad Words");
    TextField addSentence = new TextField("Add Sentence");
    Button addSentenceBtn = new Button("Add Sentence");
    TextArea goodSentence = new TextArea("Good Sentences");
    TextArea badSentence = new TextArea("Bad Sentences");
    Button showSentenceBtn = new Button("Show Sentence");
    FormLayout fl1 = new FormLayout();
    FormLayout fl2 = new FormLayout();
    WordPublisher wordPublisher = new WordPublisher();
    Sentence sentences = new Sentence();

    public MyView2() {
        goodWord.setItems(this.wordPublisher.words.goodWords);
        badWord.setItems(this.wordPublisher.words.badWords);

        addGoodWordBtn.addClickListener(buttonClickEvent -> {
            ArrayList<String> output = webClient.post().uri("http://localhost:8080/addGood/" + this.addWord.getValue()).retrieve().bodyToMono(ArrayList.class).block();
            goodWord.setItems(output);
        });

        addBadWordBtn.addClickListener(buttonClickEvent -> {
            ArrayList<String> output = webClient.post().uri("http://localhost:8080/addBad/" + this.addWord.getValue()).retrieve().bodyToMono(ArrayList.class).block();
            badWord.setItems(output);
        });

        addSentenceBtn.addClickListener(buttonClickEvent -> {
            String output = webClient.post().uri("http://localhost:8080/proof/" + this.addSentence.getValue()).retrieve().bodyToMono(String.class).block();
            Notification notification = Notification.show(output);
        });

        showSentenceBtn.addClickListener(buttonClickEvent -> {
            Sentence output = webClient.get().uri("http://localhost:8080/getSentence").retrieve().bodyToMono(Sentence.class).block();
            goodSentence.setValue(String.valueOf(output.goodSentences));
            badSentence.setValue(String.valueOf(output.badSentences));
        });

        fl1.add(addWord, addGoodWordBtn, addBadWordBtn, goodWord, badWord);
        fl2.add(addSentence, addSentenceBtn, goodSentence, badSentence, showSentenceBtn);

        fl1.setResponsiveSteps(new ResponsiveStep("1px", 1));
        fl2.setResponsiveSteps(new ResponsiveStep("1px", 1));

        this.setResponsiveSteps(new ResponsiveStep("1px", 2));
        this.add(fl1, fl2);
    }
}
