package com.example.sop_lab_4;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Change implements Serializable {
    int b1000, b500, b100, b20, b10, b5, b1;
    public int getB1000() {
        return this.b1000;
    }
    public int getB500() {
        return this.b500;
    }
    public int getB100() {
        return this.b100;
    }
    public int getB20() {
        return this.b20;
    }
    public int getB10() {
        return this.b10;
    }
    public int getB5() {
        return this.b5;
    }
    public int getB1() {
        return this.b1;
    }
    public void setB1000(int b1000) {
        this.b1000 = b1000;
    }
    public void setB500(int b500) {
        this.b500 = b500;
    }
    public void setB100(int b100) {
        this.b100 = b100;
    }
    public void setB20(int b20) {
        this.b20 = b20;
    }
    public void setB10(int b10) {
        this.b10 = b10;
    }
    public void setB5(int b5) {
        this.b5 = b5;
    }
    public void setB1(int b1) {
        this.b1 = b1;
    }
}
