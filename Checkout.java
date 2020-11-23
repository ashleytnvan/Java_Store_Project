package milestone4;

import java.util.HashMap;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Checkout {
    private String name;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String card;
    private String cc_type;
    private String cc_exp;
    private String coupon;

    private JButton checkoutButton;
    private JButton cancelButton;
    private Iterator cartIterator;

    private JFrame frame;

    public Checkout(Iterator cartIterator){
        this.cartIterator = cartIterator;
        JTextField nameField = new JTextField(10);
        JTextField cityField = new JTextField(10);
        JTextField stateField = new JTextField(10);
        JTextField zipField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(10);
        JTextField cardField = new JTextField(10);
        JTextField cTypeField = new JTextField(10);
        JTextField cExpField = new JTextField(10);
        JTextField couponField = new JTextField(10);
        JLabel nameLab = new JLabel("Name: ");
        JLabel cityLab= new JLabel("City: ");
        JLabel stateLab= new JLabel("State: ");
        JLabel zipLab= new JLabel("Zip: ");
        JLabel phoneLab= new JLabel("Phone: ");
        JLabel emailLab= new JLabel("Email: ");
        JLabel cardLab= new JLabel("Card Number: ");
        JLabel cTypeLab= new JLabel("Card Type: ");
        JLabel cExpLab= new JLabel("Card Expiration: ");
        JLabel couponLab= new JLabel("Coupon: ");

        nameLab.setLabelFor(nameField);
        cityLab.setLabelFor(cityField);
        stateLab.setLabelFor(stateField);
        zipLab.setLabelFor(zipField);
        phoneLab.setLabelFor(phoneField);
        emailLab.setLabelFor(emailField);
        cardLab.setLabelFor(cardField);
        cTypeLab.setLabelFor(cTypeField);
        cExpLab.setLabelFor(cExpField);
        couponLab.setLabelFor(couponField);

        JPanel infoPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        infoPane.setLayout(gridbag);

        JLabel[] labels = {nameLab, cityLab, stateLab, zipLab,
                           phoneLab, emailLab, cardLab, cTypeLab, cExpLab,
                           couponLab};
        JTextField[] textFields = {nameField, cityField, stateField, zipField,
                                   phoneField, emailField, cardField, cTypeField,
                                   cExpField, couponField};
        addLabelTextRows(labels, textFields, gridbag, infoPane);

        checkoutButton = new JButton("Checkout");
        cancelButton = new JButton("Cancel");

        checkoutButton.addActionListener(event -> {
            name = nameField.getText();
            city = cityField.getText();
            state = stateField.getText();
            zip = zipField.getText();
            phone = phoneField.getText();
            email = emailField.getText();
            card = cardField.getText();
            cc_type = cTypeField.getText();
            cc_exp = cExpField.getText();
            coupon = couponField.getText(); // pass into invoice
            createInvoice(cartIterator);
        });
        cancelButton.addActionListener(event -> { //Close window.
            cancel();
        });

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = GridBagConstraints.LINE_END;
        infoPane.add(checkoutButton, c);
        infoPane.add(cancelButton, c);
        infoPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Billing Information"),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        frame = new JFrame();
        frame.add(infoPane);
        frame.setPreferredSize(new Dimension(300,300 ));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void applyCoupon( Coupon coupon){

    }

    public void cancel(){
        frame.setVisible(false); //you can't see me!
        frame.dispose();
    }

    public void createInvoice(Iterator cartIterator){
        Invoice i = new Invoice( cartIterator );
    }

    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }
}
