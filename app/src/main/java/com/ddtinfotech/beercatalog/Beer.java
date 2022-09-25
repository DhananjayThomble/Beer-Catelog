package com.ddtinfotech.beercatalog;

public class Beer {
    private String name;
    private String brand;
    private String alcoholContent;

    public Beer(String name, String brand, String alcoholContent) {
        this.name = name;
        this.brand = brand;
        this.alcoholContent = alcoholContent;
    }

    public String getName() {
        return name;
    }


    public String getBrand() {
        return brand;
    }


    public String getAlcoholContent() {
        return alcoholContent;
    }

}
