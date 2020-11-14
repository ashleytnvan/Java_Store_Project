package milestone4;

import java.util.ArrayList;

public class StoreApp {
    private ArrayList<Item> items;


    public StoreApp(){
        items = new ArrayList<Item>();
    }

    public void startNew(){

    }

    public void chooseCart(){

    }

    public void exit(){

    }

    public String listItems(){
        String items = "";
        for(Item item :  items){
            items += item.toString() + " ";
        }
        items += "\n";
        return items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(items.indexOf(item));
    }

    public void clear(){

    }

    public int size(){
        return items.size();
    }
}
