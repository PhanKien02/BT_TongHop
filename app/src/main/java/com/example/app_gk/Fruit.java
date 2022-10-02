package com.example.app_gk;

public class Fruit {
    private String title;
    private String description;
    private int image;

     public Fruit(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImag(int imag) {
        this.image = imag;
    }
}
