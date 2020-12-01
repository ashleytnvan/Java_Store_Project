package milestone4;

import javax.swing.*;
import java.awt.*;

/**
 * Item class has the properties of items such as picture,
 * name, price, and discounted price. It uses ImageIcon.
 */
public class Item extends JPanel {
    private String item_name;
    private String picture;
    private double price;
    private double discounted_price;
    private JLabel pictureLabel;
    private JLabel costLabel;
    private JLabel discountLabel;
    private JLabel nameLabel;

    /**
     * Constructs the Item object with name, Icon, price and discounted price
     * @param item_name name of the item
     * @param picture path for item image
     * @param price item price
     * @param discounted_price discounted price
     */
    public Item(String item_name, String picture, double price, double discounted_price){
        this.item_name = item_name;
        this.picture = picture;
        this.price = price;
        this.discounted_price = discounted_price;
        pictureLabel = new JLabel();
        updatePicture();
        add(pictureLabel, BorderLayout.CENTER);
    }

    /**
     * Creates icon based on image path, or else provides a placeholder if the image isn't found
     */
    protected void updatePicture() {
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon("images/"
                + getItemName() + ".gif");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(getItemName());

        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
        pictureLabel.setPreferredSize(new Dimension(225,225));
    }

    /**
     * Returns an ImageIcon to be used in updatePicture()
     * @param path the file path for the image
     * @return the ImageIcon for the item picture
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Item.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Returns the object
     * @return this item
     */
    public Item getItem(){
        return this;
    }

    /**
     * Returns the item name
     * @return the item name
     */
    public String getItemName(){
        return item_name;
    }

    /**
     * Returns the item picture
     * @return the picture for the item
     */
    public ImageIcon getPicture(){
        String path = "images/" + getItemName() + ".gif";
        java.net.URL imgURL = Item.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Returns the dimension of the image
     * @return the image dimension
     */
    public Dimension getPictureDimension(){
        String path = "images/" + getItemName() + ".gif";
        java.net.URL imgURL = Item.class.getResource(path);
        ImageIcon image = new ImageIcon(imgURL);
        return new Dimension(image.getIconWidth(), image.getIconHeight());
    }

    /**
     * Returns the discounted price of the item
     * @return the discounted price
     */
    public double getDiscount(){
        return discounted_price;
    }

    /**
     * Returns the regular item price
     * @return the regular price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Returns whether the item has a discounted price
     * @return true if the item can be discounted
     */
    public boolean hasDiscount(){
        return (discounted_price != 0);
    }

    /**
     * Returns whether the item has a picture
     * @return true if the item has a picture
     */
    public boolean hasPicture(){
        if (picture != null)
            return true;
        else
            return false;
    }

    /**
     * Set the discounted price to a new value
     * @param discounted_price the new discounted price
     */
    public void setDiscount(int discounted_price){
        this.discounted_price = discounted_price;
    }

    /**
     * Sets the price to a new value
     * @param price the new price
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Sets the item name to a new value
     * @param item_name the new name
     */
    public void setItemName(String item_name){
        this.item_name = item_name;
    }

    /**
     * Sets the item picture to a new image path
     * @param picture the new path to the image file
     */
    public void setPicture(String picture){
        this.picture = picture;
    }

    /**
     * Returns the item object as a string
     * @return the object as a string
     */
    @Override
    public String toString(){
        return "Item[name="+item_name+",picture="+picture+
                ",price="+price+",discounted_price="+
                discounted_price+"]";
    }
}
