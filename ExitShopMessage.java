package milestone4;

/**
 * ExitShopMessage class is the message for exiting the shop.
 */
public class ExitShopMessage implements Message {
}

/**
 * AddCartMessage class is the message for adding a shopping cart.
 */
class AddCartMessage implements Message {
}

/**
 * SelectACartMessage class is the message for selecting a previous
 * shopping cart.
 */
class SelectACartMessage implements Message {
}

/**
 * SelectPreviousCartMessage class is the message for selecting a
 * previous shopping cart.
 */
class SelectPreviousCartMessage implements Message {
    int i;

    SelectPreviousCartMessage(int i){
        this.i =i;
    }

    public int getCartIndex(){
        return i;
    }
}

/**
 * CloseStoreMessage class is the message for closing the store.
 */
class CloseStoreMessage implements Message {
}

/**
 * CloseCartMessage class is the message for closing the cart.
 */
class CloseCartMessage implements Message {
}

/**
 * BuyCartMessage class is the message for buying a cart and checking out.
 */
class BuyCartMessage implements Message {
}