import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohammmed
 */
public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void add(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public void printItems() {
        for (CartItem item : items) {
            System.out.println("Product: " + item.getProduct().getName()
                    + ", Quantity: " + item.getQuantity()
                    + ", Total: " + item.getTotalPrice());
        }
    }
    public List<CartItem> getShippableItems() {
        List<CartItem> list = new ArrayList<>();
        for (CartItem item : items) {
            if (item.product instanceof ShippableItem) {
                list.add(item);
            }
        }
        return list;
    }

    public double getCartTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
