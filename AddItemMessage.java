package milestone4;

public class AddItemMessage implements Message {
    Item i;
    AddItemMessage(Item i){
        this.i =i;
    }

    public Item getItem(){
        return i;
    }
}