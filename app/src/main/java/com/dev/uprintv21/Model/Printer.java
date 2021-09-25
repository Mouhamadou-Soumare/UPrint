package com.dev.uprintv21.Model;

public class Printer {

    private String Name;
    private String Image;
    private String Description;
    private String PriceNB,PriceC,PriceNM;

    public Printer() {
    }

    public Printer(String name, String image, String description, String priceNB, String priceC, String priceNM) {
        Name = name;
        Image = image;
        Description = description;
        PriceNB = priceNB;
        PriceC = priceC;
        PriceNM = priceNM;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPriceNB() {
        return PriceNB;
    }

    public void setPriceNB(String priceNB) {
        PriceNB = priceNB;
    }

    public String getPriceC() {
        return PriceC;
    }

    public void setPriceC(String priceC) {
        PriceC = priceC;
    }

    public String getPriceNM() {
        return PriceNM;
    }

    public void setPriceNM(String priceNM) {
        PriceNM = priceNM;
    }
}
