package com.techelevator;
public class Gum extends Snacks {
    public Gum(String location, String name, double price, String type) {
        super(location, name, price, type);
    }

    @Override
    public String printOut() {
        return "Chew Chew, Yum!";
    }
}
