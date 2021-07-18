package hello.itemservice.domain.item;

import lombok.Data;

@Data // @Data 는 주의해서 사용
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // null
    private Integer quantity; // null

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
