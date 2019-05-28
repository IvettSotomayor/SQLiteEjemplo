package com.example.a18372908_k.sqliteejemplo;

public class Car {
    private int ID;
    private String nombre;
    private String color;

    public Car(int ID, String nombre, String color) {
        this.ID = ID;
        this.nombre = nombre;
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
