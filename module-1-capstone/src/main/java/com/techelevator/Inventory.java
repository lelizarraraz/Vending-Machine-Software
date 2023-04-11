package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

    private List<Snacks> allSnacks = new ArrayList<>();

    public void inventory() {
        File vendingInventory = new File("vendingmachine.csv"); // created an object so that Scanner has something to read through

        try (Scanner scan = new Scanner(vendingInventory)) { //looks through the actual scanner
            while (scan.hasNextLine()) {

                String line = scan.nextLine();

                String[] inventoryToAdd = line.split("\\|"); // use escape operator

                Snacks snacks = null; //need a snack item because it is abstract, so we create variable for future created snacks
                if (inventoryToAdd[3].equals("Candy")) { // creating if for every type
                    allSnacks.add(snacks = new Candy(inventoryToAdd[0], inventoryToAdd[1], Double.parseDouble(inventoryToAdd[2]), inventoryToAdd[3]));
                } else if (inventoryToAdd[3].equals("Chip")) { // creating if for every type
                    allSnacks.add(snacks = new Chip(inventoryToAdd[0], inventoryToAdd[1], Double.parseDouble(inventoryToAdd[2]), inventoryToAdd[3]));
                } else if (inventoryToAdd[3].equals("Drink")) { // creating if for every type
                    allSnacks.add(snacks = new Drinks(inventoryToAdd[0], inventoryToAdd[1], Double.parseDouble(inventoryToAdd[2]), inventoryToAdd[3]));
                } else if (inventoryToAdd[3].equals("Gum")) { // creating if for every type
                    allSnacks.add(snacks = new Gum(inventoryToAdd[0], inventoryToAdd[1], Double.parseDouble(inventoryToAdd[2]), inventoryToAdd[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid input.");
        }
    }

    public List<Snacks> getAllSnacks() {
        return allSnacks;
    }

    public void displayInventory() { //call that same item for select item

        for (Snacks snack : allSnacks) {
            System.out.print(snack.getLocation() + " ");
            System.out.print(snack.getProductName() + " ");
            System.out.print(snack.getPrice() + " ");
            System.out.print(snack.getQuantity() + "\n");
        }
    }

}


