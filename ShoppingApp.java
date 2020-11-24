package milestone4;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ShoppingApp {
    private ArrayList<ShoppingCart> carts;
    private ArrayList<JButton> cart_buttons;
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


    public ShoppingApp(){
        carts = new ArrayList<ShoppingCart>();
        cart_buttons = new ArrayList<JButton>();
        size = 0;
        cart_index = -1;
        shopPanel = new JPanel();
        shopPanel.setLayout(new FlowLayout());
        shopPanel.add(new JLabel("Shopping"));
        exit = new JButton("Exit");
        shopPanel.add(exit);

        addACart = new JButton("Add a new cart");
        selectACart = new JButton("Select a cart");
        addACart.addActionListener(event -> {
            //create new ShoppingCart and add it to carts ArrayList
        	ShoppingCart cart = new ShoppingCart();
        	addCart(cart);
        	StoreApp storeApp = new StoreApp(cart);
        	storeApp.showStore();
        });
        selectACart.addActionListener(event -> {
            chooseCart();
        });
        exit.addActionListener(event -> {
            System.exit(0);
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

    public void chooseCart(){
        cartsPanel.removeAll();
        cartsPanel.revalidate();
        cartsPanel.repaint();
        cartsPanel.setLayout(new BoxLayout(cartsPanel, BoxLayout.PAGE_AXIS));

        cartsPanel.setPreferredSize(new Dimension(200,300 ));
        JButton temp;
        for (int i=1; i <= size; i++){
            temp = new JButton("Cart " + i);
            //temp.setPreferredSize(new Dimension(100,50 ));
            int finalI = i;
            temp.addActionListener(event -> {
                cart_index = finalI -1;
                StoreApp storeApp = new StoreApp(carts.get(cart_index));
                storeApp.showStore();
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
        //cartsPanel.getComponent(0)
        cartsPane = new JScrollPane(cartsPanel);
        frame.remove(cartsPanel);
        frame.add(cartsPane, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void addCart(ShoppingCart s){
        carts.add(s);
        cart_index = size;
        size++;
    }

    private void updateExit(){
        exit.addActionListener(event -> {
            System.exit(0);
        });
    }

}
