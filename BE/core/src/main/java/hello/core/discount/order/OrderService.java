package hello.core.discount.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
