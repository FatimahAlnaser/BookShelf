package com.example.bookshelf;

public class BookModel {

    private int id=0;
    private String name;
    private int price;
    private String state;


    public BookModel(int id, String name, int price, String state) {
        this.id = id++;
        this.name = name;
        this.price = price;
        this.state = state;
    }

    public BookModel() {
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
    }

//getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
