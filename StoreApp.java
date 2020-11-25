package milestone4;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private ChangeListener listener;
    private BlockingQueue queue;

    public StoreApp(ShoppingCart cart, BlockingQueue<Message> queue){
        this.queue = queue;
        items = new ArrayList<Item>();
        this.cart = cart;
        createInventory();
        cart.addChangeListener(event -> {
            showStore();
        });
    }

    private void createInventory() {
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
        exitButton = new JButton("Return to carts");
        imgURL = Item.class.getResource("/images/shopping_cart.png");
        boolean imgFound = false;
        try {
            cartButton = new JButton(new ImageIcon(imgURL));
        }
        catch (Exception e){
            imgFound = false;
        }
        imgURL = Item.class.getResource("images/shopping_cart.png");
        cartButton = new JButton(new ImageIcon(imgURL));
        cartButton.addActionListener(event -> {
            cart.showCart();
            frame.setVisible(false); //you can't see me!
            frame.dispose();
        });
        exitButton.addActionListener(event -> {
            try{
                queue.put(new CloseStoreMessage());
            }
            catch(InterruptedException exception){
                exception.printStackTrace();
            }
        });
        header.add(cartButton);
        header.add(exitButton);
        base.add(header, BorderLayout.NORTH);
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
            custom.add(label);
            custom.setPreferredSize(i.getPictureDimension());
            itemDisplay.add( custom );
            custom.addActionListener(event->{
                try{
                    queue.put(new AddItemMessage(i));
                }
                catch(InterruptedException exception){
                    exception.printStackTrace();
                }
                //                cart.addItem(i);
//                cart.showItems();
            });
            /*
            i.addToCart.addActionListener(event -> {
                int number = Integer.parseInt(i.quantity.getText());
                for (int j = 0; j < number; j++)
                    cart.addItem(i);
            });
            itemDisplay.add(i);
             */
        }
        itemScroll = new JScrollPane(itemDisplay);
        itemScroll.setPreferredSize(new Dimension(700,450) );
        base.add(itemScroll, BorderLayout.CENTER);
        frame = new JFrame("Store");
        frame.add(base);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void addItem(Item i){
        cart.addItem(i);
        cart.showItems();
    }

    public void exit(){
        frame.setVisible(false);
        frame.dispose();
        ChangeEvent changeEvent = new ChangeEvent(this);
        listener.stateChanged(changeEvent);
    }

    public void addChangeListener(ChangeListener listener)
    {
        this.listener = listener;
    }
}
