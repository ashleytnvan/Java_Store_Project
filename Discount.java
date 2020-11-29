package milestone4;

public class Discount {
    private double percentage;
    private String code;

    /**
     * Creates a discount object with percentage and unique code
     * @param percentage the percentage discount
     * @param code the discount code
     */
    public Discount(double percentage, String code) {
        this.percentage = percentage;
        this.code = code.toUpperCase();
    }
}
