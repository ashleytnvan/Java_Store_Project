package milestone4;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Item,Integer> shoppingCart;

    public ShoppingCart(){

    }

    public void checkout(){

    }

    public void listItems(){
        for (Map.Entry me : shoppingCart.entrySet() ){
            System.out.println("Item = " + entry.getKey() +
                    ", Quantity = " + entry.getValue());
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
}
