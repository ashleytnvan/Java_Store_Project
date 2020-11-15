package milestone4;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShoppingApp {
    private ArrayList<ShoppingCart> carts;
    private ArrayList<JButton> cart_buttons;
    private int size;
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
        shopPanel = new JPanel();
        shopPanel.setLayout(new FlowLayout());
        shopPanel.add(new JLabel("Shopping"));
        exit = new JButton("Exit");
        shopPanel.add(exit);

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
        frame.setPreferredSize(new Dimension(375,350 ));
        frame.pack();
        frame.setVisible(true);
        chooseCart();
    }

    public void chooseCart(){
        cartsPanel.removeAll();
        cartsPanel.revalidate();
        cartsPanel.repaint();
        cartsPanel.setLayout(new BoxLayout(cartsPanel, BoxLayout.PAGE_AXIS));

        cartsPanel.setPreferredSize(new Dimension(200,300 ));
        JButton temp;
        for (int i=1; i <= 5; i++){
            temp = new JButton("Cart " + i);
            temp.setPreferredSize(new Dimension(100,50 ));
            cartsPanel.add(temp);
        }
        //cartsPanel.getComponent(0)
        cartsPane = new JScrollPane(cartsPanel);
        frame.remove(cartsPanel);
        frame.add(cartsPane, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
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
