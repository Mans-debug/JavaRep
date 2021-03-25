import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Launch
{
    public static int getCustomer(ArrayList<Customer> customers,String name)
    {
        int res = 0;
        int i = 0;
        for (Customer cust : customers)
        {
            if (cust.customerName == name)
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
            Scanner sc = new Scanner(new File("C:\\Users\\mansu\\IdeaProjects\\Project\\src\\orders.txt"));

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
                    if(custNames.contains(custName))//если в списке есть покупатель
                    {
                        int indexOfCustomer = getCustomer(customers, custName);
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
                            customers.get(indexOfCustomer).cityList = new ArrayList<>();
                            customers.get(indexOfCustomer).cityList.add(city);
                        }
                    }
                    else //добавить нового кастомера
                    {
                        customer.cityList = new ArrayList<>();
                        customer.cityList.add(city);
                        int indexOfCity = customer.cityList.indexOf(city);
                        customer.cityList.get(indexOfCity).customerOrders = new ArrayList<>();
                        customer.cityList.get(indexOfCity).customerOrders.add(order);
                        customers.add(customer);
                    }
                    System.out.println(i++);
                }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int x = 4;
        for (Customer i: customers)
            System.out.println(i);
        /*System.out.println(customers);*/


    }
}
