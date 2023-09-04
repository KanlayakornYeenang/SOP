package com.example.sop_lab_4;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index1")
public class MathView extends FormLayout {
    private final WebClient webClient = WebClient.create();
    private final TextField n1 = new TextField("Number 1");
    private final TextField n2 = new TextField("Number 2");
    private final TextField ans = new TextField("Answer");
    public MathView() {
        Text button_label = new Text("Operator");
        HorizontalLayout hl = new HorizontalLayout();
        Button plus = createButton("+", "plus");
        Button minus = createButton("-", "minus");
        Button multiply = createButton("x", "multi");
        Button divide = createButton("/", "divide");
        Button mod = createButton("Mod");
        Button max = createButton("Max");
        hl.add(plus, minus, multiply, divide, mod, max);

        this.setResponsiveSteps(new ResponsiveStep("1px", 1));
        this.add(n1, n2);
        this.add(button_label);
        this.add(hl);
        this.add(ans);
    }
    private Button createButton(String operator) {
        Button button = new Button(operator);
        button.addClickListener(buttonClickEvent -> performButton(operator.toLowerCase()));
        return button;
    }
    private Button createButton(String operator, String path) {
        Button button = this.createButton(operator);
        button.addClickListener(buttonClickEvent -> performButton(path));
        return button;
    }
    private void performButton(String path) {
        double num1 = Double.parseDouble(this.n1.getValue());
        double num2 = Double.parseDouble(this.n2.getValue());

        String uri = "http://localhost:8080/"+path+"/"+num1+"/"+num2;
        String output = String.valueOf(this.webClient.get().uri(uri).retrieve().bodyToMono(String.class).block());
        this.ans.setValue(output);
    }
}
