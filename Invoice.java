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

    private Iterator cartIterator;

    public Invoice(Iterator cartIterator ){
        tax_rate = 0.0725;
        this.cartIterator = cartIterator;
        itemCartCost = 0;
        invoiceArea = new JTextArea(20, 40);
        invoiceArea.setText(createInvoice(cartIterator));
        frame = new JFrame();
        frame.add(new JScrollPane(invoiceArea),
                BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private String createInvoice(Iterator cartIterator){
        String r = "               Invoice\n  ";
        r += "Thank you for shopping with us\n\n";
        r += "Items          Quantity          Cost\n";
        itemCartCost = 0;
        while(cartIterator.hasNext()){
            Map.Entry e = (Map.Entry)cartIterator.next();
            Item temp = (Item)e.getKey();
            itemCartCost += temp.getPrice() * (int)e.getValue();
            r += String.format("%-15s %-15d $%.2f\n", temp.getItemName(), (int)e.getValue(), temp.getPrice());
        }
        r += "Total Cost: " + String.format("$%.2f\n",itemCartCost);
        double temp = itemCartCost * tax_rate;
        r += "Tax Due: " + String.format("$%.2f\n", temp);
        temp += itemCartCost;
        r += "Total Due: " + String.format("$%.2f\n", temp);
        return r;
    }
}