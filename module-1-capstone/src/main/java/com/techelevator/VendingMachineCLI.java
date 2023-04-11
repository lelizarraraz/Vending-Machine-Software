package com.techelevator;

import com.techelevator.view.Menu;

import javax.swing.*;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static com.techelevator.TransactionLog.log;


public class VendingMachineCLI{


	private double money = 0.00; //need to add a zero for money value

	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTIONS_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTIONS_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTIONS_FEED_MONEY, PURCHASE_MENU_OPTIONS_SELECT_PRODUCT, PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION};


	private Menu menu;

	private static Inventory inventory = new Inventory();



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			System.out.println(choice);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { // display vending machine items
				inventory.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { // do purchase
				while (true) { //allows us to stay in the loop until broken
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					System.out.println(purchaseChoice);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FEED_MONEY)) {
						currentMoneyProvided();
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_SELECT_PRODUCT)) {
						inventory.displayInventory();
						selectingProducts();
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION)) {
						changeDue();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				break;
			}
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		System.out.println("************************************");
		System.out.println("*       Vendo-Matic 800            *");
		System.out.println("************************************");
		System.out.println();
		inventory.inventory(); // calls the method that generates the inventory from the CSV
		cli.run();
	}

	public void selectingProducts() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter item number here: ");
		String itemNumber = userInput.nextLine().toUpperCase().trim();
		boolean isValid = false;
		for (Snacks snacks : inventory.getAllSnacks()) {
				if (itemNumber.equals(snacks.getLocation())) {
					isValid = true;
					if (snacks.getQuantity() < 1){
						System.out.println("Sold out!");
						break;
					}
					if (money >= snacks.getPrice()) {
						System.out.println(snacks.getProductName());
						System.out.println(snacks.getPrice());
						money -= snacks.getPrice();
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String moneyString = formatter.format(money);
						System.out.println("Your current balance is: " + moneyString);
						snacks.setQuantity(1); // removes one item
						System.out.println(snacks.printOut());
//						snacks.notify();
					} else {
						System.out.println("Not enough money. Please insert more money.");

					}
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					log(dateFormatter.format(now) +" Snacks : " + snacks.getProductName() + " " + snacks.getLocation()+ " " + formatter.format(money));
				}
			}
		if (!isValid){
			System.out.println("INVALID INPUT! TRY AGAIN.");
		}
	}

	public void currentMoneyProvided(){
		//Method to calculate total money user has entered into machine.
		Scanner scanner = new Scanner(System.in);
		double moneyProvided = scanner.nextDouble();
		money += moneyProvided;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(money);
		System.out.println(moneyString);

		log(dateFormatter.format(now) + " Feed Money: " + formatter.format(money));
	}

	public void changeDue() {
		double amountSpent = 0.00;
		amountSpent = money;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		log(dateFormatter.format(now) + "GIVE CHANGE: " + formatter.format(money));


		int quarters = (int) (money / .25);
		money -= quarters * .25;
		int dimes = (int) (money / .10);
		money -= dimes * .10;
		int nickels = (int) (money / .05);
		money -= nickels * .05;

		System.out.println("Please accept your change in the amount of: " + quarters + " quarters "
				+ dimes + " dimes " + nickels + " nickels ");

//		log(dateFormatter.format(now) + "GIVE CHANGE: $ " + money);
	}

}

