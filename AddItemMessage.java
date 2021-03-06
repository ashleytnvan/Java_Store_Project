package milestone4;

/**
 * AddItemMessage class is the message for adding an item to
 * the cart.
 */
public class AddItemMessage implements Message {
    Item i;

    /**
     * Constructs an AddItemMessage from given Item
     * @param i the item to be used in the message
     */
    AddItemMessage(Item i){
        this.i =i;
    }

    /**
     * Returns the item from an AddItemMessage
     * @return the item
     */
    public Item getItem(){
        return i;
    }
}