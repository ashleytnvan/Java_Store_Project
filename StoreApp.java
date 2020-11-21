package milestone4;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StoreApp {
    private ArrayList<Item> items;


    public StoreApp(){

        items = new ArrayList<Item>();
    }

    public void startNew(){

    }

    public void exit(){

    }

    public String listItems(){
        String list_items = "";
        for(Item item :  items){
            list_items += item.toString() + " ";
        }
        list_items += "\n";
        return list_items;
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
