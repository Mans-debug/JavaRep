

import java.util.ArrayList;

public class City
{
    String cityName;
    ArrayList<Order> customerOrders;

    public City(String cityName)
    {
        this.cityName = cityName;
    }

    boolean isOrder(String orderName)
    {
        boolean res = false;
        if(customerOrders == null)
            return false;
        for(Order i: customerOrders)
        {
            if(i.name == orderName)
                return true;
        }
        return res;
    }
    int getOrder(String name)
    {
        int j = 0;
        for(Order i: customerOrders)
        {
            if(i.name == name)
                return j;
                j++;
        }
        return -1;
    }

    public String toString()
    {
        String res = "";
        res += cityName + "\n";
        for(Order i : customerOrders)
            res += i.toString();
        return res;
    }
}
