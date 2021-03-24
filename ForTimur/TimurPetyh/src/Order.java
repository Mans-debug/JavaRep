import java.io.Serializable;

public class Order implements Serializable
{
    String nameOfProduct;
    int count;

    public Order(String nameOfProduct, int count)
    {
        this.nameOfProduct = nameOfProduct;
        this.count = count;
    }
}
