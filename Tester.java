package milestone4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Tester class has the main function and the BlockingQueue.
 */
public class Tester {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    /**
     * The main function.  Creates a controller and calls mainLoop, then
     * clears the queue and disposes the controller when valve is finished.
     * Also, creates the ShoppingApp and StoreApp models.  And, the ShoppingCart view.
     */
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(queue);
        StoreApp store = new StoreApp(cart, queue);
        ShoppingApp shop = new ShoppingApp(queue, cart, store);
        Controller controller = new Controller(queue, shop, cart, store);
        controller.mainLoop();
        queue.clear();
        controller.dispose();
    }
}