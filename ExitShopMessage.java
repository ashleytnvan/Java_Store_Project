package milestone4;

public class ExitShopMessage implements Message {
}

class AddCartMessage implements Message {
}

class SelectACartMessage implements Message {
}

class SelectPreviousCartMessage implements Message {
    int i;

    SelectPreviousCartMessage(int i){
        this.i =i;
    }

    public int getCartIndex(){
        return i;
    }
}

class CloseStoreMessage implements Message {
}

class CloseCartMessage implements Message {
}

class BuyCartMessage implements Message {
}