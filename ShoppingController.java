package milestone4;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;
import javax.swing.event.*;
import java.util.LinkedList;
import java.util.List;

public class ShoppingController {
    private ArrayList<ShoppingCart> carts;
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
    private List<Valve> valves = new LinkedList<Valve>();

    public ShoppingController(BlockingQueue<Message> queue){
        this.queue = queue;
        carts = new ArrayList<ShoppingCart>();
        size = 0;
        cart_index = -1;
        showShoppingController();
        valves.add(new AddItemValve());
        valves.add(new ExitShopValve());
        valves.add(new AddCartValve());
        valves.add(new SelectACartValve());
        valves.add(new SelectPreviousCartValve());
        valves.add(new CloseStoreValve());
        valves.add(new CloseCartValve());
        valves.add(new BuyCartValve());
    }

    public void mainLoop(){
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take(); // <--- take next message from the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Look for a Valve that can process a message
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        public ValveResponse execute(Message message);
    }

    private class AddItemValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != AddItemMessage.class) {
                return ValveResponse.MISS;
            }
            storeApp.addItem(((AddItemMessage) message).getItem());
            return ValveResponse.EXECUTED;
        }
    }

    private class ExitShopValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ExitShopMessage.class) {
                return ValveResponse.MISS;
            }
            return ValveResponse.FINISH;
        }
    }

    private class AddCartValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != AddCartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            shoppingCart = new ShoppingCart(queue);
            addCart(shoppingCart);
            storeApp = new StoreApp(shoppingCart, queue);
            storeApp.showStore();
            storeApp.addChangeListener(evt -> {
                showShoppingController();
            });
            frame.setVisible(false);
            frame.dispose();
            return ValveResponse.EXECUTED;
        }
    }

    private class CloseCartValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CloseCartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            shoppingCart.cancel();
            return ValveResponse.EXECUTED;
        }
    }

    private class BuyCartValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != BuyCartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            shoppingCart.checkout();
            return ValveResponse.EXECUTED;
        }
    }

    private class SelectACartValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SelectACartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            chooseCart();
            return ValveResponse.EXECUTED;
        }
    }

    private class SelectPreviousCartValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SelectPreviousCartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            cart_index = ((SelectPreviousCartMessage)message).getCartIndex() -1;
            StoreApp storeApp = new StoreApp(carts.get(cart_index), queue);
            storeApp.showStore();
            storeApp.addChangeListener(evt -> {
                showShoppingController();
            });
            frame.setVisible(false);
            frame.dispose();
            return ValveResponse.EXECUTED;
        }
    }

    private class CloseStoreValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CloseStoreMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            storeApp.exit();
            return ValveResponse.EXECUTED;
        }
    }

    private void showShoppingController(){
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

    private void chooseCart(){
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

    private void addCart(ShoppingCart s){
        carts.add(s);
        cart_index = size;
        size++;
    }

    private void updateExit(){
        exit.addActionListener(event -> {
            System.exit(0);
        });
    }

    public void dispose(){
        System.exit(0);
    }
}
