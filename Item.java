package milestone4;

public class Item {
    private String item_name;
    private String picture;
    private int price;
    private int discounted_price;

    public Item(String item_name, String picture, int price, int discounted_price){

    }

    public Item getItem(){
        return this;
    }

    public String ItemName(){
        return item_name;
    }

    public String getPicture(){
        return picture;
    }

    public int getDiscount(){
        return discounted_price;
    }

    public int getPrice(){
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
