package milestone4;

public class Item {
    private String item_name;
    private String picture;
    private double price;
    private double discounted_price;

    public Item(String item_name, String picture, double price, double discounted_price){
        this.item_name = item_name;
        this.picture = picture;
        this.price = price;
        this.discounted_price = discounted_price;

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
}
