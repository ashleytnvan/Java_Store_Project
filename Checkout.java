package milestone4;

import java.util.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Checkout {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String card;
    private String cc_type;
    private String cc_exp;
    private String coupon;
    private JLabel nameLab;
    private JLabel addressLab;
    private JLabel cityLab;
    private JLabel stateLab;
    private JLabel zipLab;
    private JLabel phoneLab;
    private JLabel emailLab;
    private JLabel cardLab;
    private JLabel cTypeLab;
    private JLabel cExpLab;
    private JLabel couponLab;
    private JLabel required;
    //private HashMap<String, Double> promoCodes;
    private JButton checkoutButton;
    private JButton cancelButton;
    private Iterator cartIterator;
    private JFrame frame;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField cardField;
    private JTextField cTypeField;
    private JTextField cExpField;
    private JTextField couponField;
    private ChangeListener listener;
    private int itemCount;

    /**
     * Constructs a Checkout panel that provides required information fields
     * @param cartIterator an iterator to go through all user carts
     */
    public Checkout(Iterator cartIterator){
        this.cartIterator = cartIterator;
        nameLab = new JLabel("Name: ");
        addressLab = new JLabel("Address: ");
        cityLab= new JLabel("City: ");
        stateLab= new JLabel("State: ");
        zipLab= new JLabel("Zip: ");
        phoneLab= new JLabel("Phone: ");
        emailLab= new JLabel("Email: ");
        cardLab= new JLabel("Card Number: ");
        cTypeLab= new JLabel("Card Type: ");
        cExpLab= new JLabel("Card Expiration: ");
        couponLab= new JLabel("Coupon: ");
        nameLab.setLabelFor(nameField);
        addressLab.setLabelFor(addressField);
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
        nameField = new JTextField(10);
        addressField = new JTextField(10);
        cityField = new JTextField(10);
        stateField = new JTextField(10);
        zipField = new JTextField(10);
        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        cardField = new JTextField(10);
        cTypeField = new JTextField(10);
        cExpField = new JTextField(10);
        couponField = new JTextField(10);
        couponField.setText("milestone4");
        required = new JLabel();
        required.setForeground(Color.RED);
        JLabel[] labels = {nameLab, addressLab, cityLab, stateLab, zipLab,
                           phoneLab, emailLab, cardLab, cTypeLab, cExpLab,
                           couponLab,required};
        JTextField[] textFields = {nameField, addressField, cityField,
                                   stateField, zipField,
                                   phoneField, emailField, cardField, cTypeField,
                                   cExpField, couponField};
        addLabelTextRows(labels, textFields, gridbag, infoPane);
        checkoutButton = new JButton("Checkout");
        cancelButton = new JButton("Return to cart");
        boolean [] validated = new boolean[10];
        checkoutButton.addActionListener(event -> {
            if(validate()) {
        		name = nameField.getText();
        		address = addressField.getText();
        		city = cityField.getText();
        		state = stateField.getText();
        		zip = zipField.getText();
        		phone = phoneField.getText();
        		email = emailField.getText();
        		card = cardField.getText();
            	cc_type = cTypeField.getText();
            	cc_exp = cExpField.getText();
            	coupon = couponField.getText(); // pass into invoice
            	createInvoice(cartIterator, name, address, city, state,
                              zip, phone, email, card, cc_exp, cc_type, coupon);
                frame.setVisible(false); //you can't see me!
                frame.dispose();
            }
            else {
                required.setText("Missing Required Fields");
                if (nameField.getText().length() == 0 && !validated[0] ){
                    nameLab.setText(nameLab.getText() + "*");
                    nameLab.setForeground(Color.RED);
                    validated[0] = true;
                }
                else if (nameField.getText().length() != 0 && validated[0]){
                    nameLab.setText(nameLab.getText().substring(0,nameLab.getText().length()-1));
                    nameLab.setForeground(Color.BLACK);
                    validated[0] = false;
                }
                if (addressField.getText().length() == 0 && !validated[1] ){
                    addressLab.setText(addressLab.getText() + "*");
                    addressLab.setForeground(Color.RED);
                    validated[1] = true;
                }
                else if (addressField.getText().length() != 0 && validated[1]){
                    addressLab.setText(addressLab.getText().substring(0,addressLab.getText().length()-1));
                    addressLab.setForeground(Color.BLACK);
                    validated[1] = false;
                }
                if (cityField.getText().length() == 0 && !validated[2] ){
                    cityLab.setText(cityLab.getText() + "*");
                    cityLab.setForeground(Color.RED);
                    validated[2] = true;
                }
                else if (cityField.getText().length() != 0 && validated[2]){
                    cityLab.setText(cityLab.getText().substring(0,cityLab.getText().length()-1));
                    cityLab.setForeground(Color.BLACK);
                    validated[2] = false;
                }
                if (stateField.getText().length() == 0 && !validated[3] ){
                    stateLab.setText(stateLab.getText() + "*");
                    stateLab.setForeground(Color.RED);
                    validated[3] = true;
                }
                else if (stateField.getText().length() != 0 && validated[3]){
                    stateLab.setText(stateLab.getText().substring(0,stateLab.getText().length()-1));
                    stateLab.setForeground(Color.BLACK);
                    validated[3] = false;
                }
                if (zipField.getText().length() == 0 && !validated[4] ){
                    zipLab.setText(zipLab.getText() + "*");
                    zipLab.setForeground(Color.RED);
                    validated[4] = true;
                }
                else if (zipField.getText().length() != 0 && validated[4]){
                    zipLab.setText(zipLab.getText().substring(0,zipLab.getText().length()-1));
                    zipLab.setForeground(Color.BLACK);
                    validated[4] = false;
                }
                if (phoneField.getText().length() == 0 && !validated[5] ){
                    phoneLab.setText(phoneLab.getText() + "*");
                    phoneLab.setForeground(Color.RED);
                    validated[5] = true;
                }
                else if (phoneField.getText().length() != 0 && validated[5]){
                    phoneLab.setText(phoneLab.getText().substring(0,phoneLab.getText().length()-1));
                    phoneLab.setForeground(Color.BLACK);
                    validated[5] = false;
                }
                if (emailField.getText().length() == 0 && !validated[6] ){
                    emailLab.setText(emailLab.getText() + "*");
                    emailLab.setForeground(Color.RED);
                    validated[6] = true;
                }
                else if (emailField.getText().length() != 0 && validated[6]){
                    emailLab.setText(emailLab.getText().substring(0,emailLab.getText().length()-1));
                    emailLab.setForeground(Color.BLACK);
                    validated[6] = false;
                }
                if (cardField.getText().length() <4 && !validated[7]  ){
                    cardLab.setText(cardLab.getText() + "*");
                    cardLab.setForeground(Color.RED);
                    validated[7] = true;
                }
                else if (cardField.getText().length() != 0 && validated[7]){
                    cardLab.setText(cardLab.getText().substring(0,cardLab.getText().length()-1));
                    cardLab.setForeground(Color.BLACK);
                    validated[7] = false;
                }
                if (cTypeField.getText().length() == 0 && !validated[8] ){
                    cTypeLab.setText(cTypeLab.getText() + "*");
                    cTypeLab.setForeground(Color.RED);
                    validated[8] = true;
                }
                else if (cTypeField.getText().length() != 0 && validated[8]){
                    cTypeLab.setText(cTypeLab.getText().substring(0,cTypeLab.getText().length()-1));
                    cTypeLab.setForeground(Color.BLACK);
                    validated[8] = false;
                }
                if (cExpField.getText().length() == 0 && !validated[9] ){
                    cExpLab.setText(cExpLab.getText() + "*");
                    cExpLab.setForeground(Color.RED);
                    validated[9] = true;
                }
                else if (cExpField.getText().length() != 0 && validated[9]){
                    cExpLab.setText(cExpLab.getText().substring(0,cExpLab.getText().length()-1));
                    cExpLab.setForeground(Color.BLACK);
                    validated[9] = false;
                }
                if (couponField.getText().length() == 0){
                    couponLab.setText(couponLab.getText() + "*");
                    couponLab.setForeground(Color.RED);
                }
            }
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
        frame.setPreferredSize(new Dimension(300,330 ));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        itemCount = 0;
    }

    /*
    public void applyCoupon(){
        if (promoCodes.containsKey(coupon)) {
            double discount = promoCodes.get(coupon);
        };
    }

    public void addPromoCode(String code, double discount) {
        promoCodes.put(code, discount);
    }
    */

    /**
     * Cancels checkout and disposes the checkout frame.
     */
    private void cancel(){
        frame.setVisible(false); //you can't see me!
        frame.dispose();
        ChangeEvent changeEvent = new ChangeEvent(this);
        listener.stateChanged(changeEvent);
    }

    /**
     * Creates an invoice based on cart contents and provided information fields
     * @param cartIterator the iterator for user carts
     * @param name customer name
     * @param address customer address
     * @param city customer city
     * @param state customer state
     * @param zip customer zip code
     * @param phone customer phone number
     * @param email customer email address
     * @param card customer credit/debit card number
     * @param ccExp customer card expiration date
     * @param ccType customer credit card provider
     * @param coupon customer coupon promo code
     */
    private void createInvoice(Iterator cartIterator, String name,
                              String address,
                              String city, String state, String zip,
                              String phone, String email, String card,
                              String ccExp, String ccType,
                              String coupon){
        Invoice i = new Invoice( cartIterator, name,
                address,
                city, state, zip,
                phone, email, card, ccExp, ccType,
                coupon);
    }

    /**
     * Adds labels and textfields to a GUI container for ease of formatting
     * @param labels the JLabels to be added
     * @param textFields the JTextFields to be added
     * @param gridbag the gridbag layout to be used
     * @param container the container to be used
     */
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        c.gridwidth = GridBagConstraints.REMAINDER;
        container.add(labels[labels.length-1], c);

        for (int i = 0; i < numLabels-1; i++) {
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

    /**
     * Determines if all required text fields have been filled
     * @return true if all required fields are filled, false otherwise
     */
    private boolean validate() {
    	if(nameField.getText().length()==0 ||
                addressField.getText().length()==0 ||
    			cityField.getText().length()==0 ||
    			stateField.getText().length()==0 ||
    			phoneField.getText().length()==0 ||
    			cardField.getText().length() < 4 ||
    			cTypeField.getText().length()==0 ||
    			cExpField.getText().length()==0 ) {
    		return false;
    	}
    	return true;
    }

    /**
     * Adds a ChangeListener
     * @param listener the listener to be added
     */
    public void addChangeListener(ChangeListener listener)
    {
        this.listener = listener;
    }

    /**
     * Test: makes sure all items are passed into checkout.
     */
    public int getItemCount(){
        while(cartIterator.hasNext()){
            Map.Entry e = (Map.Entry)cartIterator.next();
            itemCount++;
        }
        return itemCount;
    }
}
