package com.example.sop_lab_4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;

@Route(value = "index2")
public class CashierView extends VerticalLayout {
    WebClient webClient = WebClient.create();
    IntegerField change = new IntegerField("เงินทอน");
    Button calBtn = new Button("คำนวณเงินทอน");
    ArrayList<IntegerField> integerFields = new ArrayList<>();
    String[] spans = {"$1000:", "$500:", "$100:", "$20:", "$10:", "$5:", "$1:"};
    HashMap<String, Integer> jsonObject = new HashMap<>();

    public CashierView() {
        this.change.setPrefixComponent(new Span("$"));
        this.calBtn.addClickListener(buttonClickEvent -> this.perform());

        this.add(change, calBtn);

        for (String span : spans) {
            IntegerField integerField = this.createIntegerField(span);
            this.integerFields.add(integerField);
            this.add(integerField);
        }
    }

    private IntegerField createIntegerField(String span) {
        IntegerField number_field = new IntegerField();
        number_field.setPrefixComponent(new Span(span));
        return number_field;
    }

    private void perform() {
        int money = this.change.getValue();
        String uri = "http://localhost:8080/getChange/" + money;
        this.jsonObject = this.webClient.get().uri(uri).retrieve().bodyToMono(HashMap.class).block();

        for (int i = 0; i < integerFields.size(); i++) {
            integerFields.get(i).setValue(this.jsonObject.get("b" + this.spans[i].substring(1, spans[i].length() - 1)));
        }
    }
}