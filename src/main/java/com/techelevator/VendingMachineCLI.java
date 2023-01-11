package com.techelevator;

import com.techelevator.view.Machine;
import com.techelevator.view.Menu;
import com.techelevator.view.SalesReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class VendingMachineCLI {

	//Main menu Options
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

	//Selection Menu Options
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	Machine VendoMatic800 = new Machine();




	private final Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		VendoMatic800.stockMachine();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				VendoMatic800.displayStock();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				PurchaseMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){

				System.out.println("***Thank you for using VendoMatic-800***");
				System.exit(0); //shuts down program
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){

				SalesReport.createSalesReport();


			}
		}
	}

	private Menu purchaseMenu;
	public void PurchaseMenu(){
		purchaseMenu = new Menu(System.in, System.out);
		runPurchaseMenu();
	}
	public void runPurchaseMenu() {

		while (true) {
			System.out.printf("****Current Balance: $%.2f ****\n" , VendoMatic800.validator.getBalance());
			String choice = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

				VendoMatic800.feedMoney();


			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

				VendoMatic800.displayStock();
				System.out.println("");
				System.out.printf("****Current Balance: $%.2f ****\n" , VendoMatic800.validator.getBalance());
				Scanner userInput = new Scanner(System.in);
				System.out.print("Please make a selection: ");
				String selection = userInput.nextLine();
				VendoMatic800.makePurchase(selection);


			} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION )){

				VendoMatic800.validator.giveChange();


				break;
			}
		}
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}
