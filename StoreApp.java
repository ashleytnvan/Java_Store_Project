package milestone4;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

public class StoreApp {
    private ArrayList<Item> items;
    private ShoppingCart cart;
    private JFrame frame;
    private JPanel base;
    private JPanel header;
    private JPanel itemDisplay;
    private JScrollPane itemScroll;

    private JButton exitButton;
    private java.net.URL imgURL;
    private JButton cartButton;

    public StoreApp(ShoppingCart cart){
        items = new ArrayList<Item>();
        this.cart = cart;
        createInventory();
    }

    public void createInventory() {
        Item grape = new Item("grape","grape",1.99,1.49);
        Item banana = new Item("banana","banana",1.50,1.50);
        Item kiwi = new Item("kiwi","kiwi",2.49,1.99);
        Item lemon = new Item("lemon","lemon",1.89,1.50);
        Item orange = new Item("orange","orange",2.49,1.99);
        Item strawberry = new Item("strawberry","strawberry",1.75,1.50);

        items.add(grape);
        items.add(banana);
        items.add(kiwi);
        items.add(lemon);
        items.add(orange);
        items.add(strawberry);
    }

    public void showStore(){
        base = new JPanel();
        base.setLayout(new BorderLayout()); // use base border layout
        header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.RIGHT)); // right justified
        exitButton = new JButton("Cancel");
        imgURL = Item.class.getResource("images/shopping_cart.png");
        cartButton = new JButton(new ImageIcon(imgURL));
        cartButton.addActionListener(event -> {
            cart.showCart();
        });
        exitButton.addActionListener(event -> {
            exit();
        });
        header.add(cartButton);
        header.add(exitButton);
        base.add(header, BorderLayout.NORTH);

        // item display
        itemDisplay = new JPanel();
        itemDisplay.setLayout(new GridLayout((int)Math.round(Math.sqrt(items.size())),
                (int)Math.round(Math.sqrt(items.size()))));
        for(Item i: items) {
            i.setOpaque(true);
            JButton custom = new JButton(i.getPicture());
            JLabel label = new JLabel(i.getItemName());
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setFont(new Font("Verdana", Font.PLAIN, 24));
            //label.setBorder(BorderFactory.createLineBorder(Color.black));
            custom.add(label);
            custom.setPreferredSize(i.getPictureDimension());
            //custom.setText(i.getItemName());
            itemDisplay.add( custom );
            custom.addActionListener(event->{
                cart.addItem(i);
                cart.showItems();
            });
        }


        itemScroll = new JScrollPane(itemDisplay);
        itemScroll.setPreferredSize(new Dimension(700,450) );
        base.add(itemScroll, BorderLayout.CENTER);
        frame = new JFrame("Store");
        // frame visibility
        frame.add(base);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
