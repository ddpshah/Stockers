package com.example.myapplication;

public class Exchange {
    private String name,prince;
    public Exchange(){

    }
    public Exchange(String name, String prince) {
        this.name = name;
        this.prince = prince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrince() {
        return prince;
    }

    public void setPrince(String prince) {
        this.prince = prince;
    }
}
