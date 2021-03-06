package milestone4;

import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.concurrent.BlockingQueue;

/**
 * ShoppingCart class has a shopping cart with item and item quantity.
 * It displays a cart with the items in it and users can checkout.
 */
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
    private ChangeListener listener;
    private BlockingQueue queue;

    /**
     * Creates shopping cart with BlockingQueue for messages
     * @param queue the BlockingQueue
     */
    public ShoppingCart(BlockingQueue<Message> queue){
        this.queue = queue;
        shoppingCart = new HashMap<Item,Integer>();
        tax_rate = 0.0725;
        label = new JLabel("Shopping Cart");
        cancel = new JButton("Store"); //takes back to store
        cancel.addActionListener(event -> {
            try{
                queue.put(new CloseCartMessage());
            }
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        String header_title = "      Items                                   ";
        header_title += "Quantity                         ";
        header_title += "Cost                                 ";
        header = new JLabel(header_title);
        itemsPanel = new JPanel();
        double totalCost = calculateTotalCost();
        String footer_title1 = String.format("  Total cost: $%.2f", totalCost );
        double tax_due = totalCost * tax_rate;
        String footer_title2 = String.format("  Tax Due: $%.2f", tax_due);
        totalCost += tax_due;
        String footer_title3 = String.format("  Total Due: $%.2f", totalCost);
        footer1 = new JLabel( footer_title1 );
        footer2 = new JLabel( footer_title2 );
        footer3 = new JLabel( footer_title3 );
        buy = new JButton("Buy"); //takes to checkout.
        buy.addActionListener(event -> {
            if(!shoppingCart.isEmpty())
                try{
                    queue.put(new BuyCartMessage());
                }
                catch(InterruptedException exception) {
                    exception.printStackTrace();
                }
        });
        frame = new JFrame();
    }

    /**
     * Takes user to the checkout window with current cart
     */
    public void checkout(){
        Iterator cartIterator = checkoutCart();
        Checkout c = new Checkout(cartIterator);
        c.addChangeListener(evt -> {
            showCart();
        });
        frame.setVisible(false); //you can't see me!
        frame.dispose();
    }

    /**
     * Adds an item to the shopping cart
     * @param item the item to add
     */
    public void addItem(Item item){
        if (shoppingCart.containsKey(item))
            shoppingCart.computeIfPresent(item, (k,v)->v+1);
        else
            shoppingCart.put(item,1);
    }

    /**
     * Removes an item from the cart
     * @param item the item to be removed
     */
    public void removeItem(Item item){
        if (shoppingCart.containsKey(item))
            if(shoppingCart.get(item) > 0)
                shoppingCart.computeIfPresent(item,(k,v)->v-1);
            else
                shoppingCart.remove(item);
    }

    /**
     * Cancels the shopping cart and disposes the GUI window
     */
    public void cancel(){
        frame.setVisible(false); //you can't see me!
        frame.dispose();
        ChangeEvent changeEvent = new ChangeEvent(this);
        listener.stateChanged(changeEvent);
    }

    /**
     * Shows the shopping cart GUI
     */
    public void showCart(){
        label = new JLabel("Shopping Cart");
        cancel = new JButton("Store"); //takes back to store
        cancel.addActionListener(event -> {
            try{
                queue.put(new CloseCartMessage());
            }
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        String header_title = "      Items                                   ";
        header_title += "Quantity                         ";
        header_title += "Cost                                 ";
        header = new JLabel(header_title);
        itemsPanel = new JPanel();
        double totalCost = calculateTotalCost();
        String footer_title1 = String.format("  Total cost: $%.2f", totalCost );
        double tax_due = totalCost * tax_rate;
        String footer_title2 = String.format("  Tax Due: $%.2f", tax_due);
        totalCost += tax_due;
        String footer_title3 = String.format("  Total Due: $%.2f", totalCost);
        footer1 = new JLabel( footer_title1 );
        footer2 = new JLabel( footer_title2 );
        footer3 = new JLabel( footer_title3 );
        buy = new JButton("Buy"); //takes to checkout.
        buy.addActionListener(event -> {
            if(!shoppingCart.isEmpty())
                try{
                    queue.put(new BuyCartMessage());
                }
                catch(InterruptedException exception) {
                    exception.printStackTrace();
                }
        });
        showItems();
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
        c.ipadx = 725;
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
        frame.setPreferredSize(new Dimension(750,500 ));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Returns the total cost of the items in the cart
     * @return the total cost
     */
    private double calculateTotalCost(){
        double totalCost = 0;
        for ( Map.Entry<Item, Integer> e : shoppingCart.entrySet() ) {
            Item temp = e.getKey();
            totalCost += temp.getPrice() * e.getValue();
        }
        return totalCost;
    }

    /**
     * Shows the items that have been added to the shopping cart
     */
    public void showItems(){
        itemsPanel.removeAll();
        itemsPanel.validate();
        itemsPanel.repaint();
        itemsPanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        d.ipady=0;
        d.ipadx=0;
        d.weighty = 0;
        d.weightx = 0.1;
        updateTotal();
        int i = 0;
        for ( Map.Entry<Item, Integer> e : shoppingCart.entrySet() ) {
            d.gridy=i++;
            Item temp = e.getKey();
            JLabel item_label = new JLabel(temp.getItemName());
            d.gridx = 0;
            itemsPanel.add(item_label,d);
            JButton decrease = new JButton("-");
            d.gridx = 1;
            itemsPanel.add(decrease,d);
            JLabel quantity_label = new JLabel(String.format("%d",e.getValue()));
            d.gridx = 2;
            itemsPanel.add(quantity_label,d);
            JButton increase = new JButton("+");
            d.gridx = 3;
            itemsPanel.add(increase,d);
            double item_cost = temp.getPrice() * e.getValue();
            JLabel cost_label = new JLabel(String.format("$%.2f",item_cost));
            d.gridx = 4;
            itemsPanel.add(cost_label,d);
            JButton remove = new JButton("Remove");
            d.gridx = 5;
            itemsPanel.add(remove,d);
            temp.setOpaque(true);
            d.gridx = 6;
            if (temp.hasPicture())
                itemsPanel.add(temp, d);
            decrease.addActionListener(event -> {
                if (e.getValue() > 0) {
                    e.setValue(e.getValue() - 1);
                    quantity_label.setText(String.format("%d",e.getValue()));
                    double item_cost2 = temp.getPrice() * e.getValue();
                    cost_label.setText(String.format("$%.2f",item_cost2));
                    updateTotal();
                    itemsPanel.revalidate();
                    itemsPanel.repaint();
                }
            });
            increase.addActionListener(event -> {
                e.setValue(e.getValue()+1);
                quantity_label.setText(String.format("%d",e.getValue()));
                double item_cost2 = temp.getPrice() * e.getValue();
                cost_label.setText(String.format("$%.2f",item_cost2));
                updateTotal();
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
            remove.addActionListener(event -> {
                itemsPanel.remove(item_label);
                itemsPanel.remove(decrease);
                itemsPanel.remove(quantity_label);
                itemsPanel.remove(increase);
                itemsPanel.remove(cost_label);
                if (temp.hasPicture())
                    itemsPanel.remove(temp);
                itemsPanel.remove(remove);
                shoppingCart.remove(e.getKey());
                updateTotal();
                Dimension itemsDim = new Dimension(700, 0);
                for ( Map.Entry<Item, Integer> f : shoppingCart.entrySet() ) {
                    if (f.getKey().hasPicture())
                        itemsDim.height += f.getKey().getPictureDimension().height;
                }
                itemsPanel.setPreferredSize(itemsDim);
                itemsPanel.revalidate();
                itemsPanel.repaint();
            });
        }
        Dimension itemsDim = new Dimension(700, 0);
        for ( Map.Entry<Item, Integer> e : shoppingCart.entrySet() ) {
            if (e.getKey().hasPicture())
                itemsDim.height += e.getKey().getPictureDimension().height;
        }
        itemsPanel.setPreferredSize(itemsDim);
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    /**
     * Calculates the total cost of the items in the cart before and after taxes and shows them in the GUI
     */
    private void updateTotal(){
        double totalCost = calculateTotalCost();
        String footer_title1 = String.format("  Total cost: $%.2f", totalCost );
        double tax_due = totalCost * tax_rate;
        String footer_title2 = String.format("  Tax Due: $%.2f", tax_due);
        totalCost += tax_due;
        String footer_title3 = String.format("  Total Due: $%.2f", totalCost);
        footer1.setText( footer_title1 );
        footer2.setText( footer_title2 );
        footer3.setText( footer_title3 );
    }

    /**
     * Returns the cart iterator
     * @return the cart iterator
     */
    public Iterator checkoutCart(){
        Iterator cartIterator = shoppingCart.entrySet().iterator();
        return cartIterator;
    }

    /**
     * Adds a ChangeListener object
     * @param listener the ChangeListener to add
     */
    public void addChangeListener(ChangeListener listener)
    {
        this.listener = listener;
    }

    /**
     * Test: calculate total cost of the items in the shopping cart.
     */
    public double getTotalCost(){
        return calculateTotalCost();
    }

}