package com.example.carfirebase;

public class CarInfo {
    String   uri , Model , Name ;

    public CarInfo(String uri2, String model, String name) {
        Model = model;
        Name = name;
        uri = uri2;
    }

    public String getModel() {
        return Model;
    }

    public String getName() {
        return Name;
    }

    public String getUri(){return  uri;}
}
