package com.example.myecomerceapp.Model;

public class Products {
    private String id;
    private String pname;
    private String quantity;
    private String prize;
    private String discription;
    private String image;
    private String number;

    public Products(String id, String pname, String quantity, String prize, String discription, String image) {
        this.id = id;
        this.pname = pname;
        this.quantity = quantity;
        this.prize = prize;
        this.discription = discription;
        this.image = image;
    }
    public Products(){}

    public Products(String id, String pname, String quantity, String prize, String discription) {
        this.id = id;
        this.pname = pname;
        this.quantity = quantity;
        this.prize = prize;
        this.discription = discription;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
