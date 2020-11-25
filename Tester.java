package milestone4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        ShoppingApp shoppingApp = new ShoppingApp(queue);
        shoppingApp.mainLoop();
        queue.clear();
    }
}