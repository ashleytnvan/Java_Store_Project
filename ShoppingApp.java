package milestone4;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import javax.swing.event.*;
import java.util.LinkedList;
import java.util.List;

/**
 * ShoppingApp class has option to open new and previous
 * StoreApp and ShoppingCart.
 */
public class ShoppingApp {
    private ArrayList<ShoppingCart> carts;
    private ArrayList<StoreApp> stores;
    private int size;
    private int cart_index;
    private JLabel label;
    private JFrame frame;
    private JPanel shopPanel;
    private JPanel cartsPanel;
    private JButton exit;
    private JButton addACart;
    private JButton selectACart;
    private JScrollPane cartsPane;
    private BlockingQueue<Message> queue;
    private ShoppingCart shoppingCart;
    private StoreApp storeApp;

    /**
     * The Controller for the MVC pattern
     * @param queue the BlockingQueue of messages
     */
    public ShoppingApp(BlockingQueue<Message> queue, ShoppingCart cart, StoreApp store){
        shoppingCart = cart;
        storeApp = store;

        this.queue = queue;
        carts = new ArrayList<ShoppingCart>();
        carts.add(shoppingCart);
        stores = new ArrayList<StoreApp>();
        stores.add(storeApp);
        size = 1;
        cart_index = 0;
    }

    /**
     * Display the shopping carts menu
     */
    public void showShoppingApp(){
        shopPanel = new JPanel();
        shopPanel.setLayout(new FlowLayout());
        shopPanel.add(new JLabel("Shopping"));
        exit = new JButton("Exit");
        shopPanel.add(exit);
        addACart = new JButton("Add a new cart");
        selectACart = new JButton("Select a cart");
        addACart.addActionListener(event -> {
            try{
                queue.put(new AddCartMessage());
            }
            catch(InterruptedException exception){
                exception.printStackTrace();
            }
        });
        selectACart.addActionListener(event -> {
            try{
                queue.put(new SelectACartMessage());
            }
            catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        exit.addActionListener(event -> {
            try{
                queue.put(new ExitShopMessage());
            }
            catch(InterruptedException exception){
                exception.printStackTrace();
            }
        });
        cartsPanel = new JPanel();
        cartsPanel.setLayout(new GridLayout(2,1));
        cartsPanel.add(addACart);
        cartsPanel.add(selectACart);
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(shopPanel,BorderLayout.NORTH);
        frame.add(cartsPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(375,350 ));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Selects a previous cart
     * @param index is the cart index.
     */
    public StoreApp selectPreviousCart(int index){
        //actions
        shoppingCart = carts.get(index);
        storeApp = stores.get(index);
        storeApp.setCart(shoppingCart);
        storeApp.showStore();
        storeApp.addChangeListener(evt -> {
            showShoppingApp();
            if (storeApp.storeShowing()){
                storeApp.closeStore();
            }
        });

        closeShop();
        return storeApp;
    }

    public StoreApp getStoreApp() {
        return storeApp;
    }

    public ShoppingCart getShoppingCart(int index){
        return carts.get(index);
    }

    public StoreApp getStoreApp(int index){
        return stores.get(index);
    }

    public void setStoreApp(StoreApp store){
        storeApp = store;
    }

    /**
     * Paints the cart selection panel
     */
    public void chooseCart(){
        cartsPanel.removeAll();
        cartsPanel.revalidate();
        cartsPanel.repaint();
        cartsPanel.setLayout(new BoxLayout(cartsPanel, BoxLayout.PAGE_AXIS));
        cartsPanel.setPreferredSize(new Dimension(200,300 ));
        JButton temp;
        for (int i=1; i <= size; i++){
            temp = new JButton("Cart " + i);
            int finalI = i;
            temp.addActionListener(event -> {
                try{
                    queue.put(new SelectPreviousCartMessage(finalI));
                }
                catch(InterruptedException exception){
                    exception.printStackTrace();
                }
            });
            cartsPanel.add(temp);
        }
        for( ActionListener al : exit.getActionListeners() ) {
            exit.removeActionListener( al );
        }
        exit.addActionListener(event -> {
            frame.remove(cartsPane);
            cartsPanel.removeAll();
            cartsPanel.revalidate();
            cartsPanel.repaint();
            cartsPanel.add(addACart);
            cartsPanel.add(selectACart);
            frame.add(cartsPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            updateExit();
        });
        cartsPane = new JScrollPane(cartsPanel);
        frame.remove(cartsPanel);
        frame.add(cartsPane, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Adds a cart to the shopping app
     * @param s the shopping cart
     */
    public void addCart(ShoppingCart s){
        carts.add(s);
        cart_index = size;
        size++;
    }

    /**
     * Adds a cart to the shopping app
     * @param s the shopping cart
     */
    public void addStore(StoreApp s){
        stores.add(s);
    }

    /**
     * Exits the shopping app
     */
    private void updateExit(){
        exit.addActionListener(event -> {
            System.exit(0);
        });
    }

    public void closeShop(){
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Testing: adding a cart.
     * @param s the ShoppingCart
     */
    public void createTestCarts(ShoppingCart s){
        carts.add(s);
        cart_index = size;
        size++;
    }

    /**
     * Testing: get count of shopping carts.
     * @return carts.size() the number of shopping carts.
     */
    public int getShoppingCartsCount(){
        return carts.size();
    }

}
