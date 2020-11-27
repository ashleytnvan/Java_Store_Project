package milestone4;

import org.junit.*;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.Assert.*;

public class ShoppingControllerTests {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Test
    public void testShoppingController(){
        ShoppingController shoppingController = new ShoppingController(queue);
        assertEquals(shoppingController.getValvesCount(), 8);
        ShoppingCart shoppingCart1 = new ShoppingCart(queue);
        shoppingController.createTestCarts(shoppingCart1);
        assertEquals(shoppingController.getShoppingCartsCount(), 1);
        ShoppingCart shoppingCart2 = new ShoppingCart(queue);
        ShoppingCart shoppingCart3 = new ShoppingCart(queue);
        shoppingController.createTestCarts(shoppingCart2);
        shoppingController.createTestCarts(shoppingCart3);
        assertEquals(shoppingController.getShoppingCartsCount(), 3);
    }

    @Test
    public void testStoreApp(){
        ShoppingCart shoppingCart = new ShoppingCart(queue);
        StoreApp storeApp = new StoreApp(shoppingCart, queue);
        storeApp.showStore();
        storeApp.addChangeListener(evt -> {});
        assertTrue(storeApp.hasItems());
        assertTrue(storeApp.storeShowing());
        storeApp.exit();
    }

    @Test
    public void testShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart(queue);
        Item lemon = new Item("lemon","lemon",1.89,1.50);
        shoppingCart.addItem(lemon);
        assertEquals(shoppingCart.getTotalCost(), 1.89,0.01);
        shoppingCart.showItems();
        shoppingCart.removeItem(lemon);
        shoppingCart.addChangeListener(event -> {});
        shoppingCart.cancel();
        shoppingCart.showCart();
        shoppingCart.checkout();
        assertEquals(shoppingCart.getTotalCost(), 0.0,0.01);
    }

    @Test
    public void testCheckout(){
        ShoppingCart shoppingCart = new ShoppingCart(queue);
        Item grape = new Item("grape","grape",1.99,1.49);
        Item banana = new Item("banana","banana",1.50,1.50);
        Item kiwi = new Item("kiwi","kiwi",2.49,1.99);
        Item lemon = new Item("lemon","lemon",1.89,1.50);
        Item orange = new Item("orange","orange",2.49,1.99);
        Item strawberry = new Item("strawberry","strawberry",1.75,1.50);
        shoppingCart.addItem(grape);
        shoppingCart.addItem(banana);
        shoppingCart.addItem(kiwi);
        shoppingCart.addItem(lemon);
        shoppingCart.addItem(orange);
        shoppingCart.addItem(strawberry);
        Checkout checkout = new Checkout(shoppingCart.checkoutCart());
        assertEquals(checkout.getItemCount(), 6);
    }

    @Test
    public void testInvoice() {
        String name = "";
        String address= "";
        String city= "";
        String state= "";
        String zip= "";
        String phone= "";
        String email= "";
        String card= "1234";
        String ccType= "";
        String ccExp= "";
        String coupon= "";
        ShoppingCart shoppingCart = new ShoppingCart(queue);
        Item grape = new Item("grape","grape",1.99,1.49);
        Item banana = new Item("banana","banana",1.50,1.50);
        Item kiwi = new Item("kiwi","kiwi",2.49,1.99);
        Item lemon = new Item("lemon","lemon",1.89,1.50);
        Item orange = new Item("orange","orange",2.49,1.99);
        Item strawberry = new Item("strawberry","strawberry",1.75,1.50);
        shoppingCart.addItem(grape);
        shoppingCart.addItem(banana);
        shoppingCart.addItem(kiwi);
        shoppingCart.addItem(lemon);
        shoppingCart.addItem(orange);
        shoppingCart.addItem(strawberry);
        Invoice invoice = new Invoice(shoppingCart.checkoutCart(), name,
                address,
                city, state, zip,
                phone, email, card,
                ccExp, ccType, coupon);
        assertEquals(invoice.getTotalCost(), 12.98,0.01);
        assertEquals(invoice.getCardNumber(), "1234");
    }

    @Test
    public void testItem() {
        Item grape = new Item("grape","grape",1.99,1.49);
        assertEquals(grape.getItemName(),"grape");
        assertEquals(grape.getPrice(),1.99,0.01);
        assertEquals(grape.getItem(),grape);
        assertEquals(grape.getDiscount(), 1.49,0.01);
        assertEquals(grape.getPicture().getIconHeight(),196,0.1);
        assertEquals(grape.getPicture().getIconWidth(),202,0.1);
    }
}
