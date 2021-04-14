import java.util.Objects;

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
        return name + " " + quantity;
    }

    public boolean equals(String name)
    {
        return this.name.equals(name);
    }
}
