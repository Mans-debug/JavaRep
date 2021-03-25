public class Order
{
    String name;
    int quantity;

    public Order(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
