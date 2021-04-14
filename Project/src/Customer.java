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
        if(cityList == null)
            return false;
        for (City city: cityList)
        {
            if(city.cityName.equals(cityName))
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
            if(j.cityName.equals(name))
                return i;
                i++;
        }

        return res;
    }

    @Override
    public String toString()
    {
        String res = "";
        res += customerName + ":\n";
                for(City i : cityList)
                    res += i.toString()+"\n";
                res += "_________________________________________________________\n";
        return res;
    }

    Boolean equals(String name)
    {
        return this.customerName.equals(name);
    }
}
