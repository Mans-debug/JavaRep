import java.util.Comparator;

public class BuyerByOrdersCountComparator implements Comparator<Customer>
{
    @Override
    public int compare(Customer o1, Customer o2)
    {
        Integer sizeF = 0;
        Integer sizeS = 0;
        for (City i : o1.cityList)
            sizeF += i.customerOrders.size();
        for (City i : o2.cityList)
            sizeF += i.customerOrders.size();
        return sizeF.compareTo(sizeS);
    }
}
