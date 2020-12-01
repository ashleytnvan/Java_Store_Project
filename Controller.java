package milestone4;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Controller class has the mainLoop and the valves for actions on the
 * Model (ShoppingApp) and Views (StoreApp and ShoppingCart).
 */
public class Controller {
    private ShoppingCart shoppingCart;
    private StoreApp storeApp;
    private ShoppingApp shopApp;
    private BlockingQueue<Message> queue;
    private List<Controller.Valve> valves = new LinkedList<Controller.Valve>();

    public Controller(BlockingQueue<Message> queue, ShoppingApp shop,
                      ShoppingCart cart, StoreApp store ) {
        shopApp = shop;
        shoppingCart = cart;
        shoppingCart.cancel();
        storeApp = store;
        //storeApp.showStore();
        storeApp.addChangeListener(evt -> {
            shopApp.showShoppingApp();
        });
        //shopApp.closeShop();
        this.queue = queue;
        valves.add(new Controller.AddItemValve());
        valves.add(new Controller.ExitShopValve());
        valves.add(new Controller.AddCartValve());
        valves.add(new Controller.SelectACartValve());
        valves.add(new Controller.SelectPreviousCartValve());
        valves.add(new Controller.CloseStoreValve());
        valves.add(new Controller.CloseCartValve());
        valves.add(new Controller.BuyCartValve());
    }

    /**
     * Main loop of the Shopping controller
     */
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
            for (Controller.Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    /**
     * The Valve from project starter code.
     */
    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        public ValveResponse execute(Message message);
    }

    /**
     * The valve for adding an item to the cart.
     */
    private class AddItemValve implements Valve {
        /**
         * Creates an AddItem valve response
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != AddItemMessage.class) {
                return ValveResponse.MISS;
            }
            storeApp.addItem(((AddItemMessage) message).getItem());
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * The valve for exiting the shop.
     */
    private class ExitShopValve implements Valve {
        /**
         * Creates valve response for exiting the shop
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ExitShopMessage.class) {
                return ValveResponse.MISS;
            }
            return ValveResponse.FINISH;
        }
    }

    /**
     * The valve for adding a cart.
     */
    private class AddCartValve implements Valve {
        /**
         * Creates valve response to add a cart
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != AddCartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            shoppingCart = new ShoppingCart(queue);
            shopApp.addCart(shoppingCart);
            storeApp = new StoreApp(shoppingCart, queue);
            shopApp.addStore(storeApp);
            storeApp.showStore();
            storeApp.addChangeListener(evt -> {
                shopApp.showShoppingApp();
            });
            shopApp.setStoreApp(storeApp);
            shopApp.closeShop();
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * The valve for closing the cart.
     */
    private class CloseCartValve implements Valve {
        /**
         * Creates a valve response for closing a cart
         * @param message the message
         * @return the response
         */
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

    /**
     * The valve for buying the cart.
     */
    private class BuyCartValve implements Valve {
        /**
         * Creates valve response to buy items in the cart
         * @param message the message
         * @return the response
         */
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

    /**
     * The valve for selecting a previous cart.
     */
    private class SelectACartValve implements Valve {
        /**
         * Creates valve response for selecting a cart
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SelectACartMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            shopApp.chooseCart();
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * The valve for selecting a cart.
     */
    private class SelectPreviousCartValve implements Valve {
        /**
         * Creates valve response to select the previous cart
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != SelectPreviousCartMessage.class) {
                return ValveResponse.MISS;
            }
            StoreApp temp;
            temp = shopApp.selectPreviousCart(
                    ((SelectPreviousCartMessage)message).getCartIndex() -1);
            shopApp.setStoreApp(temp);
            shoppingCart = shopApp.getShoppingCart(
                    ((SelectPreviousCartMessage)message).getCartIndex() -1);
            storeApp = shopApp.getStoreApp();
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * The valve for exiting the store.
     */
    private class CloseStoreValve implements Valve {
        /**
         * Creates valve response to closing the store
         * @param message the message
         * @return the response
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != CloseStoreMessage.class) {
                return ValveResponse.MISS;
            }
            //actions
            //storeApp.exit();
            //shopApp.setStoreApp(storeApp);
            //storeApp = shopApp.getStoreApp();
            storeApp.exit();
            if (storeApp.storeShowing()){
                storeApp.closeStore();
            }
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Disposes the frame
     */
    public void dispose(){
        System.exit(0);
    }

    /**
     * Testing: get valves count.
     * @return valves.size() the number of valves
     */
    public int getValvesCount(){
        return valves.size();
    }


}
