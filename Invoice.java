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
    private double totalCost;
    private String card;

    /**
     * Creates the frame for the Invoice
     * @param cartIterator the Iterator to traverse user carts
     * @param name customer name
     * @param address customer address
     * @param city customer city
     * @param state customer state
     * @param zip customer zip code
     * @param phone customer phone number
     * @param email customer email address
     * @param card customer credit/debit card number
     * @param ccExp customer card expiration date
     * @param ccType customer credit provider
     * @param coupon customer coupon code
     */
    public Invoice(Iterator cartIterator, String name, String address,
                   String city, String state, String zip,
                   String phone, String email, String card,
                   String ccExp, String ccType, String coupon ){
        tax_rate = 0.0725;
        this.cartIterator = cartIterator;
        itemCartCost = 0;
        invoiceArea = new JTextArea(22, 40);
        invoiceArea.setText(createInvoice(cartIterator, name, address, city,
                        state, zip, phone, email, card, ccExp, ccType, coupon));
        frame = new JFrame();
        frame.add(new JScrollPane(invoiceArea),
                BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Returns the String for Invoice Area
     * @param cartIterator the Iterator for user carts
     * @param name customer name
     * @param address customer address
     * @param city customer city
     * @param state customer state
     * @param zip customer zip
     * @param phone customer phone
     * @param email customer email
     * @param card customer credit/debit card
     * @param ccExp customer card expiration date
     * @param ccType customer credit provider
     * @param coupon customer coupon code
     * @return the String to fill in Invoice text
     */
    private String createInvoice(Iterator cartIterator, String name,
                                 String address,
                                 String city, String state, String zip,
                                 String phone, String email, String card,
                                 String ccExp, String ccType, String coupon){
        String r = "               Invoice\n  ";
        r += "Thank you for shopping with us, " + name + "\n\n";
        if (coupon.equals("milestone4")){
            r += String.format("Discount applied!\n");
        }
        r += "Items          Quantity          Cost\n";
        itemCartCost = 0;
        while(cartIterator.hasNext()){
            Map.Entry e = (Map.Entry)cartIterator.next();
            Item temp = (Item)e.getKey();
            if (coupon.equals("milestone4")){
                itemCartCost += temp.getDiscount() * (int)e.getValue();
                r += String.format("%-15s %-15d $%.2f\n", temp.getItemName(), (int)e.getValue(), temp.getDiscount());
            }
            else {
                itemCartCost += temp.getPrice() * (int) e.getValue();
                r += String.format("%-15s %-15d $%.2f\n", temp.getItemName(), (int) e.getValue(), temp.getPrice());
            }
        }
        r += "Total Cost: " + String.format("$%.2f\n",itemCartCost);
        double temp = itemCartCost * tax_rate;
        r += "Tax Due: " + String.format("$%.2f\n", temp);
        temp += itemCartCost;
        r += "Total Due: " + String.format("$%.2f\n\n", temp);
        r += "Billing Information\n";
        r += "XXXXXXXXXXXX" + card.substring(card.length()-4) + "\n";
        r += ccType + " Expires on " + ccExp + "\n\n";
        r += "Shipping Address\n";
        r += address + "\n";
        r += city + ", " + state + " " + zip + "\n\n";
        r += "Contact Information\n";
        r += phone + "\n";
        r += email + "\n";
        totalCost = temp;
        this.card = card;
        return r;
    }

    /**
     * Test: get total cost of the invoice.
     */
    public double getTotalCost(){
        return totalCost;
    }

    /**
     * Test: get the card number passed from Checkout.
     */
    public String getCardNumber(){
        return card;
    }

}