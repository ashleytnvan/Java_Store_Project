package milestone4;

import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ShoppingCart {
    private HashMap<Item,Integer> shoppingCart;

    private JLabel label;
    private JButton cancel;
    private JLabel header;
    private JScrollPane itemsScroll;
    private JPanel itemsPanel;
    private JFrame frame;
    private JLabel footer1;
    private JLabel footer2;
    private JLabel footer3;
    private JButton buy;
    private double tax_rate;

    public ShoppingCart(){
        tax_rate = 0.0725;
        label = new JLabel("Shopping Cart");
        cancel = new JButton("Cancel"); //takes back to store
        String header_title = "Items                                ";
        header_title += "Quantity                             ";
        header_title += "Cost                                 ";
        header = new JLabel(header_title);
        itemsPanel = new JPanel();

        double totalCost = calculateTotalCost();
        String footer_title1 = String.format("Total cost: $%.2f", totalCost );
        double tax_due = totalCost * tax_rate;
        String footer_title2 = String.format("Tax Due: $%.2f", tax_due);
        totalCost += tax_due;
        String footer_title3 = String.format("Total Due: $%.2f", totalCost);
        footer1 = new JLabel( footer_title1 );
        footer2 = new JLabel( footer_title2 );
        footer3 = new JLabel( footer_title3 );

        buy = new JButton("Buy"); //takes to checkout.


        //itemsPanel.add();
        itemsPanel.setPreferredSize(new Dimension(375,375));
        addItems();
        //itemsPanel.setLayout(new GridLayout(5,4));
        itemsScroll = new JScrollPane(itemsPanel);

        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 25;
        frame.add(label, c);
        c.gridx = 1;
        c.gridy = 0;
        frame.add(cancel, c);
        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 2;
        c.ipady = 25;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        frame.add(header,c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth = 2;
        c.weightx=1.0;
        c.weighty = 1.0;
        c.ipadx = 400;
        c.ipady = 375;
        frame.add(itemsScroll, c);
        c.gridy = 3;
        c.ipady = 75;
        c.gridwidth = 1;
        frame.add(footer1, c);
        c.gridy = 4;
        frame.add(footer2, c);
        c.gridy = 5;
        frame.add(footer3, c);
        c.gridx=1;
        c.gridy = 3;
        c.gridheight = 3;
        c.ipadx = 200;
        c.ipady = 25;
        frame.add(buy,c);

        frame.setPreferredSize(new Dimension(500,500 ));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void checkout(){

    }

    public void listItems(){
        for (Map.Entry me : shoppingCart.entrySet() ){
            System.out.println("Item = " + me.getKey() +
                    ", Quantity = " + me.getValue());
        }
    }

    public void addItem(Item item){
        if (shoppingCart.containsKey(item))
            shoppingCart.computeIfPresent(item, (k,v)->v+1);
        else
            shoppingCart.put(item,1);
    }

    public void removeItem(Item item){
        if (shoppingCart.containsKey(item))
            if(shoppingCart.get(item) > 0)
                shoppingCart.computeIfPresent(item,(k,v)->v-1);
            else
                shoppingCart.remove(item);
    }

    public void clear(){
        shoppingCart.clear();
    }

    public int size(){
        return shoppingCart.size();
    }

    public void cancel(){

    }

    public double calculateTotalCost(){
        return 5;
    }

    public void addItems(){
        //go through shopping cart.
        itemsPanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        d.ipady=0;
        d.ipadx=0;
        d.weighty = 0.1;
        d.weightx = 0.1;
        for(int i=0; i <= 5; i++){
            d.gridy=i;
            JLabel item_label = new JLabel("item_name");
            d.gridx = 0;
            itemsPanel.add(item_label,d);

            JButton decrease = new JButton("-");

            d.gridx = 1;
            itemsPanel.add(decrease,d);
            d.gridx = 2;

            int quantity = 3;
            String quantity_text = String.format("%d",quantity);
            JLabel quantity_label = new JLabel(quantity_text);

            itemsPanel.add(quantity_label,d);
            JButton increase = new JButton("+");
            d.gridx = 3;
            itemsPanel.add(increase,d);
            double item_cost = 4.25;
            String item_cost_text = String.format("$%.2f",item_cost);
            JLabel cost_label = new JLabel(item_cost_text);
            d.gridx = 4;
            itemsPanel.add(cost_label,d);

            JButton remove = new JButton("Remove");
            d.gridx = 5;
            itemsPanel.add(remove,d);
        }
    }
}
