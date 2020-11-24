package milestone4;

public class Coupon {
    private int discount;
    private String code;

    public Coupon(int discount, String code){

        this.discount = discount;
        this.code = code;
    }

    public int getDiscount() {return discount;}

    public String getCode() {return code;}

    public void setDiscount(int discount) {this.discount = discount;}
    public void setCode(String code) {this.code = code;}
}