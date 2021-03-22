
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("D:\\Downloads\\test.json");
        new FileWriter(file, false).close();// deletes the contents of the file
        ProductList list = new ProductList(file);
        list.add(new Product("Apple", 3));
        list.add(new Product("Laptop", 1));
        list.add(new Product("Apple", 3));
        list.changeQuant("Apple", 53);
        list.printAll();
        list.remove("Apple");
        list.add(new Product("Car", 3));
        list.remove(0);
    }
}
