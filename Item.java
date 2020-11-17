package milestone4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Item extends JPanel{
    private String item_name;
    private String picture;
    private double price;
    private double discounted_price;
    
    JLabel pictureLabel;
    JLabel costLabel;
    JLabel discountLabel;
    JLabel nameLabel;
    JPanel jp;

    public Item(String item_name, String picture, double price, double discounted_price){
        this.item_name = item_name;
        this.picture = picture;
        this.price = price;
        this.discounted_price = discounted_price;

        
    	pictureLabel = new JLabel();
    	nameLabel = new JLabel(getItemName());
    	
    	float total = (float)getPrice();
    	
    	costLabel = new JLabel();
    	String s = Float.toString(total);
    	costLabel.setText(s);
    	
    	float total2 = (float)getDiscount();
    	discountLabel = new JLabel();
    	String s2 = Float.toString(total2);
    	discountLabel.setText(s2);
    	
        updatePicture();
        add(nameLabel,BorderLayout.CENTER);
        add(pictureLabel, BorderLayout.CENTER);
        add(costLabel, BorderLayout.CENTER);
        add(discountLabel, BorderLayout.CENTER);
        
    }

    public Item getItem(){
        return this;
    }

    public String getItemName(){
        return item_name;
    }

    public String getPicture(){
        return picture;
    }

    public double getDiscount(){
        return discounted_price;
    }

    public double getPrice(){
        return price;
    }

    public boolean hasDiscount(){
        return (discounted_price != 0);
    }

    public void setDiscount(int discounted_price){
        this.discounted_price = discounted_price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setItemName(String item_name){
        this.item_name = item_name;
    }

    public void setPicture(String picture){
        this.picture = picture;
    }

    public String toString(){
        return "Item[name="+item_name+",picture="+picture+
                ",price="+price+",discounted_price="+
                discounted_price+"]";
    }
    
    protected void updatePicture() {
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon("images/"
        		+ getItemName() + ".gif");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(getItemName().toString());
        
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Item.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Item");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Item("banana","banana",1.50,1.50);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
    	//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
