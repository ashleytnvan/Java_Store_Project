package milestone4;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShoppingApp {
    private ArrayList<ShoppingCart> carts;
    private int size;
    private JLabel label;
    private JFrame frame;
    private JPanel shopPanel;
    private JPanel cartsPanel;
    private JButton exit;
    private JButton addACart;
    private JButton selectACart;


    public ShoppingApp(){
        carts = new ArrayList<ShoppingCart>();
        size = 0;
        shopPanel = new JPanel();
        shopPanel.setLayout(new FlowLayout());
        shopPanel.add(new JLabel("Shopping"), FlowLayout.CENTER);
        exit = new JButton("Exit");
        shopPanel.add(exit, FlowLayout.RIGHT);

        addACart = new JButton("Add a new cart");
        selectACart = new JButton("Select a cart");
        cartsPanel = new JPanel();
        cartsPanel.setLayout(new GridLayout(2,1));
        cartsPanel.add(addACart);
        cartsPanel.add(selectACart);
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(shopPanel,BorderLayout.NORTH);
        frame.add(cartsPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void chooseCart(){

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
