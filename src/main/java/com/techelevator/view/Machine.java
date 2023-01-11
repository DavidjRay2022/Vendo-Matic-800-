package com.techelevator.view;

import com.techelevator.app.StockTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static com.techelevator.view.ToLog.log;

public class Machine {

        final int ITEM_PROPERTY_COUNT = 4;
        final int MACHINE_MAX_SLOT = 16;

        public BillValidator validator = new BillValidator();

        //Items in the machine
        static Map<String, Item> stockMap = new TreeMap<>();
        //Holds inventory amount
        static Map<String, Integer> productInventory = new HashMap<>();

    public Map<String, Item> getStockMap() {
        return stockMap;
    }


    public static Map<String, Integer> getProductInventory() {
        return productInventory;
    }

    //Display items
        public void displayStock() {
            for (String stock : stockMap.keySet()) {
                System.out.println(stockMap.get(stock).toString() + " " +
                        ((productInventory.get(stock) > 0) ?
                                productInventory.get(stock) + " remaining " : "SOLD OUT"));
            }
        }

        /*
        Need to figure out how best to run this without object instantiation/initialization
         */

        //adds item to Machine
        public void stockItem(String position, Item item) {
            stockMap.put(position, item);
        }

        //adds amount of the item to Machine
        public void addInventory(String position) {
             final int defaultStockLevel = 5;
            productInventory.put(position, defaultStockLevel);
        }

        //decreases the stock of given item
        public void vendItem(String position) {
            productInventory.put(position, productInventory.get(position) - 1);
            System.out.println("Vending " + stockMap.get(position).getName() + " $" + stockMap.get(position).getPrice());
            stockMap.get(position).itemMessage();
            validator.decrementPurchaseCost(stockMap.get(position).getPrice());
            log(String.format(stockMap.get(position).getName() + " " + stockMap.get(position).getSlot() + " " + stockMap.get(position).getPrice() + " $%.2f", validator.getBalance()));
        }


        public void feedMoney() {

            validator.validatorMenu();


        }


        public void makePurchase(String itemSelection) {

            //check to see if item is in stock exists
            if (!stockMap.containsKey(itemSelection)) { //need to know if the item exists.
                System.out.println("Invalid selection. please select something else."); //TODO workshop this output

            } else if (productInventory.get(itemSelection) <= 0) {
                System.out.println("That item is sold out. Please select something else."); //TODO workshop this output
            } else {
                if (stockMap.get(itemSelection).getPrice() > validator.getBalance()) {
                    System.out.println("Insufficient Funds");
                    System.out.println("The item you selected costs " + stockMap.get(itemSelection).getPrice());
                    System.out.printf("Your current balance is: %.2f\n", validator.getBalance());
                    System.out.println("Insert more money.");
                } else {
                    vendItem(itemSelection);

                }
            }


        }

        //Initial stocking of the machine
        /*
        @Ron // @David
        Ron created and David simplified. Initial design on this was to
        have a running Map for items but ultimately it was decided that an
        item is a concept and each item needed to be it's own object
         */
        public void stockMachine() {

            String manifest = "vendingmachine.csv"; // TODO: 12/16/2022 Need to split method from doing both updating manifest AND stocking
            File fileName;
            Scanner in = new Scanner(System.in);
            System.out.print("Overwrite stock manifest (y/n)? ");

            while (true) {
                String str = in.nextLine();
                char a = str.toLowerCase().charAt(0);
                if (a == 'y') {
                    System.out.println("Stock manifest file location: ");
                    manifest = in.nextLine();
                }
                fileName = new File(manifest);
                if (!fileName.canRead()) {
                    System.out.println("Unable to access file");
                    throw new ToLogException("Unable to read from file name " + fileName);
                } else {
                    new ToLogException("Stocking inventory from manifest " + fileName);
                    break;
                }

            }


            try (Scanner readFile = new Scanner(fileName)) {

                while (readFile.hasNext()) {
                    String s = readFile.nextLine();
                    String[] productData = s.split("\\|");

                    Item item = ItemFactory.createItem(productData);
                    addInventory(item.getSlot());
                    stockItem(item.getSlot(), item);
                }

            } catch (FileNotFoundException fnf) {
                throw new ToLogException(fnf.getMessage());
            }
        }
    }