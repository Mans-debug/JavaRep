package LetterByLetter;

import sun.plugin2.message.MarkTaintedMessage;

import java.io.*;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeDebug.map;
import static jdk.nashorn.internal.objects.NativeDebug.setEventQueueCapacity;

public class LetterByLetter
{

    public static String getMatch(String str)
    {
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches())
        {
            if (matcher.find(0))
                return str.substring(matcher.start(),matcher.end());
            else
                return "-";
        }
        return str;

    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        File file = new File("C:\\Users\\mansu\\Downloads\\test.txt");
        Scanner sc = new Scanner(file);
        Pattern pattern = Pattern.compile("[a-zA-zа-яА-я]+");
        String key = "";
        while (sc.hasNext())//first key
        {
            key = sc.next().toLowerCase();
            Matcher matcher = pattern.matcher(key);
            if(matcher.find())
                break;
        }
        while (sc.hasNext())
        {
            String secondKey = sc.next().toLowerCase();
            if ("1234567890".contains(secondKey))
                break;


            if (map.containsKey(key))//если такой ключ уже есть
            {
                Map<String, Integer> temp = map.get(key);
                if (!map.get(key).containsKey(secondKey))
                {
                    temp.put(secondKey, 1);
                }
                else
                    temp.put(secondKey, temp.get(secondKey) + 1);

            } else //если ключа еще нет, то создаем ключ и мапу для второй буквы
            {
                Map<String, Integer> temp = new HashMap<>();
                temp.put(secondKey, 1);
                map.put(key, temp);
            }
            key = secondKey;
        }
        System.out.println(map);
        System.out.println("Would u like to get the map printed?\n1.Y\n2.N");
        Scanner in = new Scanner(System.in);
        String answer = in.next().toLowerCase();
        if (!answer.equals("y"))
            return;
        String[] keys = map.keySet().toArray(new String[0]);
        int counter = 0;
        for (Map i : map.values())
        {
            System.out.println(keys[counter++]+ "\t=" + "\t" + i);
        }
    }
}
