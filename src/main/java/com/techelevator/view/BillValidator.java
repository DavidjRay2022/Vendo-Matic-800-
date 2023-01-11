package com.techelevator.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.techelevator.view.ToLog.log;

public class BillValidator {

    private static final String ONE_DOLLAR = "$1";
    private static final String FIVE_DOLLAR = "$5";
    private static final String TEN_DOLLAR = "$10";
    private static final String DONE = "Done";
    private static final String[] BILL_VALIDATOR_OPTIONS = { ONE_DOLLAR, FIVE_DOLLAR, TEN_DOLLAR, DONE};

    private BigDecimal balance = BigDecimal.valueOf(0);

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    String feed = "FEED MONEY:";

    public double getBalance() {
        double doubleBalance = balance.doubleValue();
        return doubleBalance;
    }

    public void decrementPurchaseCost(double cost){
        balance = balance.subtract(BigDecimal.valueOf(cost));
    }

    public void giveChange(){
        double doubleBalance = balance.doubleValue();
        // define variables for coins
        double quarter = 0.25;
        double nickel = 0.05;
        double dime = 0.10;
        double penny = 0.01;

        log("GIVE CHANGE: $" + getBalance());

        System.out.printf("Returning $%.2f\n",getBalance());

        // Convert the balance into coins``
        int quarters = (int) (doubleBalance / quarter);
        doubleBalance = doubleBalance % 0.25;
        int dimes = (int) ( doubleBalance / dime);
        doubleBalance =  doubleBalance % 0.1;
        int nickels = (int) ( doubleBalance / nickel);
        doubleBalance =  doubleBalance % 0.05;
        int pennies = (int) ( doubleBalance / penny);

        // Print the number of coins for each type
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        balance = BigDecimal.valueOf(0);
    }

    private Menu validatorMenu;
    public void validatorMenu(){
        validatorMenu = new Menu(System.in, System.out);
        runValidatorMenu();
    }
    public void runValidatorMenu() {

        while (true) {
            System.out.println("****Current Balance: $" +  getBalance() + "****");
            System.out.println("Please select amount to enter: ");
            String choice = (String) validatorMenu.getChoiceFromOptions(BILL_VALIDATOR_OPTIONS);


            if (choice.equals(ONE_DOLLAR)) {
                balance = balance.add(BigDecimal.valueOf(1.00));
                log(feed + " $1.00 " + " $" + getBalance());

            } else if (choice.equals(FIVE_DOLLAR)) {
                balance = balance.add(BigDecimal.valueOf(5.00));
                log(feed + " $5.00 " + " $" + getBalance());


            } else if (choice.equals(TEN_DOLLAR )){
                balance = balance.add(BigDecimal.valueOf(10.00));
                log(feed + " $10.00 " + " $" + getBalance());

            } else if (choice.equals(DONE)) {
                break;

            }

        }
    }
}
