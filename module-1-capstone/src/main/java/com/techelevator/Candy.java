package com.techelevator;

public class Candy extends Snacks{
    public Candy(String location, String name, double price, String type) {
        super(location, name, price, type);

    }

    @Override
    public String printOut() {
        return "Munch Munch, Yum!";
    }
}
