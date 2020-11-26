package milestone4;
import org.junit.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class ShoppingControllerTests {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Test
    public void testShoppingController(){
        ShoppingController shoppingController = new ShoppingController(queue);
        shoppingController.mainLoop();
        queue.clear();
        shoppingController.dispose();
    }

}
