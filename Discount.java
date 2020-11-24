package milestone4;

public class Discount {
    private double percentage;
    private String code;

    public Discount(double percentage, String code) {
        this.percentage = percentage;
        this.code = code.toUpperCase();
    }
}
