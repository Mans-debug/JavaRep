import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BotCommands
{
    @Command(possibleNames = {"/help", "Помощь"},
            description = "prints a list of callable commands", inProgress = false,
            takesArgs = false)
    public String help(String[] args)
    {

        try
        {
            if (args.length != 0)
                throw new Exception();
        } catch (Exception e)
        {
            System.out.println("No arguments expected but found: " + args.length);
            return "";
        }
        StringBuilder builder = new StringBuilder("Callable methods (buttons):\n");

        for (Method m : this.getClass().getDeclaredMethods())
        {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);
            builder.append(Arrays.toString(cmd.possibleNames())).
                    append(": ").
                    append(cmd.description()).
                    append(" - ").
                    append(cmd.args()).
                    append("\n");
        }

        return builder.toString();
    }

    @Command(possibleNames = {"/power", "В_степень"},
            args = " int the number, int the power",
            description = "returns a number X to the power Y",
            inProgress = false)
    String power(String[] args)
    {
        int power = -1;
        int x = -1;
        //args processing
        try
        {
            if (args.length == 2)
            {
                x = Integer.parseInt(args[0]);
                power = Integer.parseInt(args[1]);
            } else
                throw new Exception();
        } catch (Exception e)
        {
            System.out.println("Something went wrong with type cast");
            e.printStackTrace();
        }
        //calculating
        int res = 1;
        if (power == 0)
            return "1";
        for (int i = 0; i < power; i++)
        {
            res *= x;
        }
        return Integer.toString(res);
    }

    @Command(possibleNames = {"/factorialof", "/factorial", "/fact", "Факториал"},
            args = "the factorial of X",
            description = "returns factorial of X",
            inProgress = false)
    String factorial(String[] args)
    {
        int x = -1;
        //data processing
        try
        {
            if (args.length == 1)
            {
                x = Integer.parseInt(args[0]);
            } else
                throw new Exception();
        } catch (Exception e)
        {
            System.out.println("Something went wrong with type cast");
            e.printStackTrace();
        }

        int res = 1;
        if (x == 0)
            return "0";
        for (int i = 1; i <= x; i++)
        {
            res *= i;
        }
        return Integer.toString(res);
    }

    @Command(possibleNames = {"/fibo", "/fibonacci", "Фиббоначи"},
            args = "takes Xth number of fibbonacci sequence",
            description = "returns X(th) number of fibbonacci sequence",
            inProgress = false)
    String fibonacci(String[] args)
    {
        int x = 0;
        try
        {
            if (args.length == 1)
            {
                x = Integer.parseInt(args[0]);
            } else
                throw new Exception();
        } catch (Exception e)
        {
            System.out.println("Something went wrong with type cast in method : ");
            e.printStackTrace();
        }
        int res = -1;
        int[] fibo = {0, 0};
        for (int i = 0; i < x; i++)
        {
            if (fibo[0] + fibo[1] == 0)
            {
                fibo[1] = 1;
            } else
            {
                int temp = fibo[0] + fibo[1];
                fibo[0] = fibo[1];
                fibo[1] = temp;
            }
        }
        return Integer.toString(fibo[1]) + " ";
    }

    @Command(possibleNames = {"/cs", "/countsymbols", "символы_в_строке"},
            args = "String",
            inProgress = false,
            description = "Counts symbols in the string")
    String countSymbols(String[] args)
    {
        String str = String.join(" ", args);
        return Integer.toString(str.length());
    }

    @Command(possibleNames = {"/randomsentence", "/getsentence", "СлучайноеПредложение"},
            args = "number of words in the sentence",
            description = "generates random sentence WORKS, BUT WITH ERRORS",
            inProgress = false)
    public String generateSentence(String[] args)
    {
        int x = 0;
        try
        {
            if (args.length == 1)
            {
                x = Integer.parseInt(args[0]);
            } else
                throw new Exception();
        } catch (Exception e)
        {
            System.out.println("Something went wrong with type cast in method : ");
            return e.toString();
        }
        String url = "https://knigolub.net/uploads/book/Batluk_Akademiya-vlasti_2_Studentka-v-nakazanie.txt";
        File file = downloadFileFromURL(url, new File("./source.txt"));

        downloadFileFromURL(url, file);

        WordGenerator wordGenerator =
                new WordGenerator(file);
        return wordGenerator.createSentence(x);
    }

    public File downloadFileFromURL(String urlString, File destination)
    {
        try
        {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            return destination;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return destination;
    }

    @Command(possibleNames = {"/reverse", "/rvrs", "/rv", "/rs", "Реверс"},
            args = "takes string that is required to be reversed",
            description = "returns reversed string",
            inProgress = false)
    String reverseString(String[] args)
    {
        String res = String.join(" ", args);
        StringBuilder builder = new StringBuilder(res);
        builder.reverse();
        return builder.toString();
    }

    /*@Command(possibleNames = "Дальше", takesArgs = true, inProgress = false)
    public String next(String[] args)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(Arrays.asList(args));
        arrayList.remove(arrayList.size() - 1);

        int page = Integer.parseInt(args[args.length - 1]);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add("Дальше");
        for (int i = page, j = 0; i < arrayList.size(); i++, j++)
        {
            if(j == 4)
                break;
                if(i%2==0)
                    keyboardFirstRow.add(arrayList.get(i));
                else
                    keyboardSecondRow.add(arrayList.get(i));
            if(i + 4>= arrayList.size() - 1)
            {
                i = 0;
            }

        }

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);

        Gson gson = new Gson();
        return gson.toJson(keyboard);
    }
*/

}
