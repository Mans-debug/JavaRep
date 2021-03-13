import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test
{
    public static void main(String[] args)
    {
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        Matcher matcher = pattern.matcher("school's");
        matcher.find();
        System.out.println(matcher.start());
        System.out.println(matcher.end());

    }
}
