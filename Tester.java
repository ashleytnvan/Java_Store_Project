package milestone4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ShoppingController shoppingController = new ShoppingController(queue);
        shoppingController.mainLoop();
        queue.clear();
        shoppingController.dispose();
    }
}