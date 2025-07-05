import java.util.Date;

/**
 *
 * @author mohammmed
 */
class Biscuits extends Product implements ExpirableItem, ShippableItem {
    private Date expiryDate;
    private double weight;

    public Biscuits(String name, double price, int quantity, Date expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public boolean isExpired() {
        return new Date().after(expiryDate);
    }

    public double getWeight() { return weight; }
}
