package milestone4;

public class Coupon {
    private int discount;
    private String code;

    /**
     * Creates a coupon to apply discounts based on unique code
     * @param discount the discount to be applied
     * @param code the unique coupon code
     */
    public Coupon(int discount, String code){

        this.discount = discount;
        this.code = code;
    }

    /**
     * Returns the percentage discount as an integer
     * @return the discount amount
     */
    public int getDiscount() {return discount;}

    /**
     * Returns the coupon code
     * @return the code
     */
    public String getCode() {return code;}

    /**
     * Sets the discount of the coupon to a new value
     * @param discount the new discount value
     */
    public void setDiscount(int discount) {this.discount = discount;}

    /**
     * Sets the coupon code to a new value
     * @param code the new code
     */
    public void setCode(String code) {this.code = code;}
}