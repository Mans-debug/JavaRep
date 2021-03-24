public class Product
{
    private String name;
    private Integer quantity;

    public Product(String name, Integer quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public Boolean equals(Product product)
    {
        if (this.name.equals(product.name))
            return true;
        else
            return false;
    }

    public Boolean equals(String name)
    {
        if (this.name.equals(name))
            return true;
        else
            return false;
    }

    public void incQuant(int quantity)
    {
        this.quantity += quantity;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public String toString()
    {

        return name + " " + quantity;
    }
}
