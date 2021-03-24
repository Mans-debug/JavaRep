import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Console
{
    public void WriteFile(ArrayList<Order> order)
    {
        Gson gson = new Gson();
        try
        {
            FileWriter fileWriter = new FileWriter("./text.txt");
            gson.toJson(order, fileWriter);
            fileWriter.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public ArrayList<Order> ReadFile()
    {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Order>>()
        {
        }.getType();
        ArrayList<Order> orders = null;
        try
        {
            FileReader fileReader = new FileReader("./text.txt");
            orders = gson.fromJson(fileReader, type);
            fileReader.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return orders;
    }

    public ArrayList<Order> AddToArray(){

    }
}

