import java.util.Comparator;

public class BuyerByCityCountComparator implements Comparator<Customer>
{
    @Override
    public int compare(Customer first, Customer second)
    {
        Integer f =first.cityList.size();
        Integer s =second.cityList.size();
        return f.compareTo(s);
    }
}
