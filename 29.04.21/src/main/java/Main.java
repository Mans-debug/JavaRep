
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File(".\\productList.json");
        new FileWriter(file, false).close();// deletes the contents of the file
        ProductList list = new ProductList(file);
        list.add(new Product("Apple", 2));
        list.add(new Product("Car", 1));
        list.add(new Product("Chair", 12));
        list.add(new Product("Bags of weed", 2));
        System.out.println(list);
        System.out.println("Now we will remove Apple");
        list.remove(0);
        list.changeQuant("Car", 4);
        System.out.println(list);
    }
}
