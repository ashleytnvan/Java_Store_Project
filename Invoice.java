package milestone4;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Invoice {
    private ArrayList<Item> items;
    private double itemCartCost;
    private double tax_rate;
    private JTextArea invoiceArea;
    private JFrame frame;

    public Invoice(){//ArrayList<Item> items){
        tax_rate = 0.0725;
        itemCartCost = 3; //
        this.items = items;
        invoiceArea = new JTextArea(20, 40);
        invoiceArea.setText(createInvoice());
        frame = new JFrame();
        frame.add(new JScrollPane(invoiceArea),
                BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String createInvoice(){
        String r = "               Invoice\n  ";
        r += "Thank you for shopping with us\n\n";
        r += "Items          Quantity          Cost\n";
        for(int i=1; i <= 5; i++){
            // String.format("%-15.s%-15d $%.2f\n, item_name, item_quantity, item_cost);
            r += "item #" + i +  "        " + i + "              " + "$1.00\n";
        }
        r += "Total Cost: " + String.format("$%.2f\n",itemCartCost);
        double temp = itemCartCost * tax_rate;
        r += "Tax Due: " + String.format("$%.2f\n", temp);
        temp += itemCartCost;
        r += "Total Due: " + String.format("$%.2f\n", temp);
        return r;
    }

    public Item getItem(){
        return null;
    }

    public int getQuantity(){
        return 0;
    }

    public int getItemCost(){
        return 0;
    }

    public int getCartCost(){
        return 0;
    }
}