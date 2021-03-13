package HistoryOfPurchases;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args)
    {
        Map<String, Map<String,Integer>> map = new HashMap<>();
        Pattern pattern = Pattern.compile("[0-9]+");

        while (true)//выход из цикла выполняется посредством ввода цифры при запросе имени
        {
            Scanner sc = new Scanner(System.in);
            String name = sc.next();

            Matcher matcher = pattern.matcher(name);
            if(matcher.matches())//условие выхода из цикла
                break;

            String product  = sc.next();
            String quantity = sc.next();
            if(map.containsKey(name))//если пользователь есть в мапе
            {
                if(map.get(name).containsKey(product))//если пользователь когда либо покупал данный товар
                {
                    Map<String, Integer> temp = new HashMap<>();
                    temp.put(product,map.get(name).get(product) + Integer.parseInt(quantity));
                    map.put(name,temp);
                }
                else//пользователь покупает товар впервые
                {
                    if(map.get(name) == null)//если не было ни одного товара в его истории
                    {
                        Map<String, Integer> temp = new HashMap<>();
                        temp.put(product, Integer.parseInt(quantity));
                        map.put(name,temp);
                    }
                    else//если была хотя бы одна покупка
                    {
                        Map<String, Integer> temp = map.get(name);
                        temp.put(product,Integer.parseInt(quantity));
                        map.put(name, temp);
                    }

                }
            }
            else//новый пользователь
            {
                Map<String,Integer> temp = new HashMap<>();
                temp.put(product, Integer.parseInt(quantity));
                map.put(name, temp);

            }
        }
        System.out.println(map);
        /*for (Map i : map.values())
        {
            System.out.println(i);
        }*/
    }
}
