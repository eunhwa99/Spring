package hello.core.order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPRice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPRice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPRice = itemPRice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPRice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPRice() {
        return itemPRice;
    }

    public void setItemPRice(int itemPRice) {
        this.itemPRice = itemPRice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPRice=" + itemPRice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
