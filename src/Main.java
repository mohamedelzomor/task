
import java.util.Date;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 100, 5, new Date(System.currentTimeMillis() + 86400000), 200);
        Biscuits biscuits = new Biscuits("Biscuits", 150, 5, new Date(System.currentTimeMillis() + 86400000), 700);
        MobileScratchCard scratchCard = new MobileScratchCard("ScratchCard", 50, 5);

        Customer customer = new Customer("Ahmed", 1000);

        customer.addToCart(cheese, 2);
        customer.addToCart(biscuits, 1);
        customer.addToCart(scratchCard, 1);

        customer.checkout();
   }
}