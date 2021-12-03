package com.example.loginsignupapp;

enum psCategory
{
    Turbo, Supercharger, Intake, Exhaust
}

public class Part {
    private int id;
    private int brand;
    private int size;
    private int modelYear;
    private String photo;


    public Part(int parseInt1, int i, int parseInt, int anInt, int i1, psCategory psCategory) {
    }

    public Part(int id, int brand, int size, int modelYear, String photo) {
        this.id = id;
        this.brand = brand;
        this.size = size;
        this.modelYear = modelYear;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", brand=" + brand +
                ", size=" + size +
                ", modelYear=" + modelYear +
                ", photo='" + photo + '\'' +
                '}';
    }

}