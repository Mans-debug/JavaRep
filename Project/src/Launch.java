import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Launch
{
    public static int getCustomer(ArrayList<Customer> customers,String name)
    {
        int res = 0;
        int i = 0;
        for (Customer cust : customers)
        {
            if (cust.customerName.equals(name))
                return i;
                i++;
        }
        return res;
    }
    public static void main(String[] args)
    {
        ArrayList<String> custNames = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        try
        {
            Scanner sc = new Scanner(new File("C:\\Users\\mansu\\IdeaProjects\\JavaRep\\Project\\src\\orders.txt"));

                int i = 0;
                while (true)
                {
                    String[] fromFile = null;
                    try
                    {
                        fromFile = sc.nextLine().split("[|]");
                    }
                    catch (Exception e)
                    {
                        System.out.println(i);
                        break;
                    }
                    String custName = fromFile[0];
                    String cityName = fromFile[1];
                    String productName = fromFile[2];
                    int quantity = Integer.parseInt(fromFile[3]);
                    Customer customer = new Customer(custName);
                    City city = new City(cityName);
                    Order order = new Order(productName, quantity);
                    int indexOfCustomer = getCustomer(customers, custName);
                    if(custNames.contains(custName))//если в списке есть покупатель
                    {

                        if(customers.get(indexOfCustomer).ebaniyMetod(cityName))//если был в этом городе
                        {
                            int cityIndex = customers.get(indexOfCustomer).getCityIndex(cityName);

                            if(customers.get(indexOfCustomer).cityList.get(cityIndex).isOrder(productName))//если до этого был такой заказ
                            {
                                int orderIndex =customers.get(indexOfCustomer).cityList.get(cityIndex).getOrder(productName);
                                customers.get(indexOfCustomer).cityList.get(cityIndex).customerOrders.get(orderIndex).quantity +=quantity;
                            }
                            else
                            {
                                customers.get(indexOfCustomer).cityList.get(cityIndex).customerOrders.add(order);
                            }
                        }
                        else
                        {
                            city.customerOrders = new ArrayList<>();
                            city.customerOrders.add(order);
                            customers.get(indexOfCustomer);
                            customers.get(indexOfCustomer).cityList.add(city);

                        }
                    }
                    else //добавить нового кастомера
                    {
                        custNames.add(custName);

                        city.customerOrders = new ArrayList<>();
                        city.customerOrders.add(order);
                        customer.cityList = new ArrayList<>();
                        customer.cityList.add(city);



                        customers.add(customer);
                    }

                }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        System.out.println(customers);
        Comparator<Customer> nameCityComparator =
                new BuyerByNameComparator().
                thenComparing(new BuyerByCityCountComparator()).
                thenComparing(new BuyerByOrdersCountComparator());

        TreeSet<Customer> sortedByName = new TreeSet<Customer>(nameCityComparator);
        sortedByName.addAll(customers);

        Comparator<Customer> orderCityName = new BuyerByOrdersCountComparator().
                thenComparing(new BuyerByCityCountComparator()).
                thenComparing(new BuyerByNameComparator());
        TreeSet<Customer> sortedByOrder = new TreeSet<>(orderCityName);
        sortedByOrder.addAll(customers);

        Gson gson = new Gson();

        try
        {
            File nameSort = new File(".\\nameSort.txt");
            File orderSort = new File(".\\orderSort.txt");
            FileWriter writer = new FileWriter(nameSort);
            gson.toJson(sortedByName,writer);
            writer.close();
            writer = new FileWriter(orderSort);
            gson.toJson(sortedByOrder, writer);
            writer.close();
        }
        catch (IOException e)
        {
            e.fillInStackTrace();
        }



    }
}
