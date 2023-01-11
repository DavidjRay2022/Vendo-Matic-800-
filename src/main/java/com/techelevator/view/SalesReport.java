package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SalesReport {
    static final int ORIGINAL_STOCK_AMOUNT = 5;
    static double totalSales = 0;
    //Date time formatter
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyy hh-mm-ss a");
    static LocalDateTime now = LocalDateTime.now();
    static Scanner scan = new Scanner(System.in);

    public static void createSalesReport(){

        File newFile;
        while (true) {
            System.out.print("Do you want to specify where sales report will be printed? (y/n)");
            String yesOrN = scan.nextLine();

            if (yesOrN.equals("y")) {
                System.out.print("Please enter the directory you would like to sales report saved to: ");
                String dir = scan.nextLine();
                newFile = nameFile(dir);

            } else {

                newFile = nameFile("SalesReports/");
            }
            try (PrintWriter write = new PrintWriter(new FileOutputStream(newFile),true)) {
                for (String position : Machine.stockMap.keySet()) {
                    write.println(Machine.stockMap.get(position).getName() + "|" + (ORIGINAL_STOCK_AMOUNT - Machine.productInventory.get(position)));
                    totalSales += (ORIGINAL_STOCK_AMOUNT - Machine.productInventory.get(position)) * Machine.stockMap.get(position).getPrice();
                }
                write.println();
                write.printf("**TOTAL SALES** $%.2f", totalSales);
                break;

            } catch (FileNotFoundException e) {
                System.err.println("invalid file path, please re-enter");
            }
        }


    }

    public static File nameFile(String dir ){


            return new File(dir, "Sales_Report_" + dtf.format(now));
        }

    }

