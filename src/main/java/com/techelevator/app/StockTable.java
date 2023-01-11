package com.techelevator.app;

import com.techelevator.view.Item;
import com.techelevator.view.Machine;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockTable {
    private JTable stockTable;
    private Map<String, Integer> productInventory = new HashMap<>();
    private Map<String, Item> stockMap = new TreeMap<>();
    private Machine mac;
    final int MAX_ITEM_STOCK = 25;
    final int COL_FIELD_COUNT = 5;

    private Object[][] dataArray =
            new Object[MAX_ITEM_STOCK][COL_FIELD_COUNT];
    final String[] COLUMN_NAMES = {
                "Item Slot",
                "Item Name",
                "Item Price",
                "Item Type",
                "Item Quantity"
    };

    public StockTable(Machine mac) {
        this.mac = mac;
        buildDataArray();
        //stockTable = new JTable(dataArray, COLUMN_NAMES);

        JFrame jf = new JFrame("StockTable");
        jf.setContentPane((Container) new JScrollPane().add(new JTable(dataArray, COLUMN_NAMES)));
        jf.setSize(750,750);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        jf.setAlwaysOnTop(true);
    }

    void buildDataArray(){
        stockMap = mac.getStockMap();
        productInventory = mac.getProductInventory();

        int count = 0;
        for (Item i : stockMap.values()){
            String s = i.toString() + "| " + productInventory.get(i.getSlot());
            System.out.println(s);
            String[] itemData = s.split("\\|");
            dataArray[count] = itemData;
            count++;
        }
    }
}
