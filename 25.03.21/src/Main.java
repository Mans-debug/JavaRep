
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
        File file = new File("C:\\Users\\mansu\\IdeaProjects\\25.03.21\\src\\productList.json");
        ProductList list = new ProductList(file);
       list.add(new Product("Санек", 2024));

    }
}
//  Gson gson  = new Gson();
//  String str = gson.toJson(MyObject);
//  MyObject myObject = gson.fromJson(str, MyObject.class);