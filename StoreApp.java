package milestone4;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StoreApp {
    private ArrayList<Item> items;
    private ShoppingCart cart;

    JFrame frame = new JFrame("Store");
    JPanel base = new JPanel();
    JPanel header = new JPanel();
    JScrollPane itemDisplay = new JScrollPane();

    JButton exitButton = new JButton("Cancel");
    JButton cartButton = new JButton(new ImageIcon("/images/shopping_cart.png"));

    public StoreApp(ShoppingCart cart){
        // initialize data structures
        items = new ArrayList<Item>();
        this.cart = cart;

        // base panel
        base.setLayout(new BorderLayout()); // use base border layout

        // header panel
        header.setLayout(new FlowLayout(FlowLayout.RIGHT)); // right justified
        cartButton.addActionListener(event -> cart.showCart());
        exitButton.addActionListener(event -> exit());
        header.add(cartButton);
        header.add(exitButton);
        base.add(header, BorderLayout.NORTH);

        // item display
        itemDisplay.setLayout(new ScrollPaneLayout());
        for(Item i: items) {
            itemDisplay.add(i);
        }
        base.add(itemDisplay, BorderLayout.CENTER);

        // frame visibility
        frame.add(base);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void startNew() {

    }

    public void exit(){

        frame.setVisible(false);
        frame.dispose();
        cart.cancel();
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

    public void clear() {
        this.items = new ArrayList<>();
    }

    public int stock() {
        return items.size();
    }
}
