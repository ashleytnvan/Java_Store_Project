package milestone4;

import java.util.ArrayList;

public class ShoppingApp {
    private ArrayList<ShoppingCart> carts;
    private int size;

    public ShoppingApp(){
        carts = new ArrayList<ShoppingCart>();
        size = 0;
    }

    public void addCart(){
        carts.add(new ShoppingCart());
        size++;
    }

    public void removeCart(){
        carts.remove((size-1));
        size--;
    }

    public void checkout(){

    }

    public void cancel(){

    }
}
