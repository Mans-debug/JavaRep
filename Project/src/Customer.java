import java.util.ArrayList;

public class Customer
{
    String customerName;
    ArrayList<City> cityList;

    public Customer(String customerName)
    {
        this.customerName = customerName;


    }

    Boolean ebaniyMetod(String cityName)
    {
        boolean res = false;
        if(cityName == null)
            return false;
        for (City city: cityList)
        {
            if(city.cityName == cityName)
                return true;
        }
        return res;
    }

    int getCityIndex(String name)
    {
        int res = -1;
        int i = 0;
        for(City j : cityList)
        {
            if(j.cityName == name)
                return i;
                i++;
        }
        return res;
    }
    public String toString()
    {
        String res = "";
        res += customerName + "\n";
        for(City i : cityList)
            res += i.toString();
        return res;
    }
}
