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

public class LetterByLetter
{
    public static void getMatch(String str, int start, int end)
    {
        String res = "";
        for (int i = start; i < end; i++)
        {
            res += str.charAt(i);
        }
        str = res;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Map<Character, Map<Character, Integer>> map = new HashMap<>();
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        File file = new File("C:\\Users\\mansu\\Downloads\\aircon.txt");
        Scanner sc = new Scanner(file);
        String ke;
        while (sc.hasNext())
        {
            String str = sc.next().toLowerCase();
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches())
            {
                matcher.find(0);
                String res = "";
                for (int i = matcher.start(); i < matcher.end(); i++)
                {
                    res += str.charAt(i);
                }
                str = res;
            }
            if (str.length() < 2)
                continue;
            Character key = str.charAt(0);
            Character secondKey = str.charAt(1);
            if (map.containsKey(key))//если такой ключ уже есть
            {
                Map<Character, Integer> temp = map.get(key);
                if (!map.get(key).containsKey(secondKey))
                {
                    temp.put(secondKey, 1);
                    continue;
                }

                if (secondKey != null)
                    temp.put(secondKey, temp.get(secondKey) + 1);

            } else //если ключа еще нет, то создаем ключ и мапу для второй буквы
            {
                Map<Character, Integer> temp = new HashMap<>();
                temp.put(secondKey, 1);
                map.put(key, temp);
            }

        }
        //System.out.println(map);
        System.out.println("Would u like to get the map printed?\n1.Y\n2.N");
        Scanner in = new Scanner(System.in);
        String answer = in.next().toLowerCase();
        if (!answer.equals("y"))
            return;
        Character[] keys = map.keySet().toArray(new Character[0]);
        int counter = 0;
        for (Map i : map.values())
        {
            System.out.println(keys[counter++] + "\t" + i);
        }
    }
}