import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Example
{
    public static void main(String[] args)
    {
        Gson gson = new Gson();
        Product  product = new Product("Apple", 4);
        String str = gson.toJson(product);
        System.out.println(str);






    }
}
