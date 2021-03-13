package Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


public class Main
{

    public static void main(String[] args)
    {
        Map<String, Integer> map = new HashMap<>();
        int counter = 0;

        try
        {
            File file = new File("C:\\Users\\mansu\\Downloads\\aircon.txt");
            Scanner sc = new Scanner(file);
            String key = "";
            while (true)
            {
                try
                {
                    key = "";
                    Pattern pattern = Pattern.compile("[a-zA-Z]+");
                    String temp = sc.next();
                    Matcher matcher = pattern.matcher(temp);
                    matcher.find();

                    for (int i = matcher.start(); i < matcher.end(); i++)
                    {
                        key += temp.toLowerCase().charAt(i);

                    }


                } catch (Exception e)
                {
                    break;
                }
                if (map.containsKey(key))
                {
                    map.put(key, map.get(key) + 1);
                } else
                {
                    map.put(key, 1);
                    counter++;
                }
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            return;
        }

        System.out.println(map);
        System.out.println("Unique words " + counter);
        //код ниже прсосто для проверки и удобство поиска ошибок
        //просто записывает мапу в txt файл
        try
        {
            FileWriter fileWriter = new FileWriter("C:\\Users\\mansu\\Downloads\\test.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(map);
            printWriter.close();
        } catch (Exception e)
        {
            System.out.println("Can't write!");
        }

    }
}
