public class Product {
    private String name;
    private Integer quantity;

    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        Product product = (Product) o;
        return this.name.equals(product.name);
    }


    public Boolean equals(String name) {
        if (name == null)
            return false;
        if (this.name.equals(name))
            return true;
        else
            return false;
    }

    public void incQuant(int quantity) {
        this.quantity += quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String toString() {

        return name + " " + quantity;
    }

    public String getName() {
        return name;
    }


}
