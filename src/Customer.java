import java.util.List;

public class Customer {
    private double balance;
    private Cart cart = new Cart();
    private String name;

    public Customer( String name ,double balance) {
        this.name = name;
        this.balance = balance;

    }


    public Customer(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deduct(double amount) {
        balance -= amount;
    }

    public void addToCart(Product p, int qty) {
        cart.add(p, qty);
    }

    public void checkout() {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = cart.getCartTotal();

        double shippingFees = 0;

        List<CartItem> shippableItems = cart.getShippableItems();
        double totalWeight = 0;

        System.out.println("** Shipment notice **");
        for (CartItem item : shippableItems) {
            ShippableItem s = (ShippableItem) item.product;
            double itemWeight = s.getWeight() * item.quantity;
            totalWeight += itemWeight;
            System.out.printf("%dx %-12s %.0fg\n", item.quantity, s.getName(), itemWeight);
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000.0);

        // Shipping fee: assume 30 fixed as in the sample
        shippingFees = 30;

        double totalAmount = subtotal + shippingFees;

        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f\n", item.quantity, item.product.getName(), item.getTotalPrice());
        }
        System.out.println("-----------------------------");
        System.out.printf("Subtotal\t%.0f\n", subtotal);
        System.out.printf("Shipping\t%.0f\n", shippingFees);
        System.out.printf("Amount\t\t%.0f\n", totalAmount);

        if (totalAmount > balance) {
            throw new IllegalStateException("Insufficient balance");
        }

        for (CartItem item : cart.getItems()) {
            if (item.product instanceof ExpirableItem) {
                if (((ExpirableItem) item.product).isExpired()) {
                    throw new IllegalStateException(item.product.getName() + " is expired!");
                }
            }
            item.product.reduceQuantity(item.quantity);
        }

        balance -= totalAmount;

        System.out.printf("\nRemaining balance: %.0f\n", balance);
    }
}
