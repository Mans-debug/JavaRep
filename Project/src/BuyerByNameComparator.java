import java.util.Comparator;

public class BuyerByNameComparator implements Comparator<Customer>
{

    @Override
    public int compare(Customer first, Customer second)
    {
        return first.customerName.compareToIgnoreCase(second.customerName);
    }
}
