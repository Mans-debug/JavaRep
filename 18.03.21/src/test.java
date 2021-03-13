import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test
{
    public static String goodKey(String str)
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
    public static void main(String[] args)
    {
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        Matcher matcher = pattern.matcher("!s!03asd");
        System.out.println(matcher.find());


    }
}
