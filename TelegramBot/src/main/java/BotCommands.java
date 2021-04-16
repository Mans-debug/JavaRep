import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class BotCommands
{
    @Command(possibleNames = {"power"},
            args = " int the number, int the power",
            description = "returns a number X to the power Y",
            inProgress = false)
    String power(String[] args)
    {
        int power = -1;
        int x = - 1;
        //args processing
        try
        {
            if(args.length == 2)
            {
                x = Integer.parseInt(args[0]);
                power = Integer.parseInt(args[1]);
            }
            else
                throw new Exception();
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong with type cast");
            e.printStackTrace();
        }
        //calculating
        int res = 1;
        if(power == 0)
            return "1";
        for (int i = 0; i < power; i++)
        {
            res *= x;
        }
        return Integer.toString(res);
    }

    @Command(possibleNames = {"factorial of", "factorial", "fact"},
            args = "the factorial of X",
            description = "returns factorial of X",
            inProgress = false)
    String factorial(String[] args)
    {
        int x = -1;
        //data processing
        try
        {
            if(args.length == 1)
            {
                x = Integer.parseInt(args[0]);
            }
            else
                throw new Exception();
        }
        catch (Exception e)
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

    @Command(possibleNames = {"fibo" , "fibonacci"},
            args = "takes Xth number of fibbonacci sequence",
    description = "returns X(th) number of fibbonacci sequence",
    inProgress = false)
    String fibonacci(String[] args)
    {
        int x = 0;
        try
        {
            if(args.length == 1)
            {
                x = Integer.parseInt(args[0]);
            }
            else
                throw new Exception();
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong with type cast in method : ");
            e.printStackTrace();
        }
        int res = -1;
        int[] fibo = {0, 0};
        for (int i = 0; i < x; i++)
        {
            if(fibo[0] + fibo[1] == 0)
            {
                fibo[1] = 1;
            }
            else
            {
                int temp = fibo[0] + fibo[1];
                fibo[0] = fibo[1];
                fibo[1] = temp;
            }
        }
        return Integer.toString(fibo[1]) + " ";
    }

    @Command(possibleNames = {"cs" ,"count symbols"},
    args = "Takes string as an argument",
    inProgress = false,
    description = "Counts the quantity of symbols in the string")
    String countSymbols(String[] args)
    {
        String str = String.join(" ", args);
        return Integer.toString(str.length());
    }

    @Command(possibleNames = {"reverse", "rvrs", "rv", "rs"},
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

    @Command(possibleNames = {"help"},
            description = "prints a list of callable commands", inProgress = false)
    public String help(String[] args) {

            try
            {
                if(args.length != 0)
                    throw new Exception();
            }
            catch (Exception e)
            {
                System.out.println("No arguments expected but found: " + args.length);
                return "";
            }
        StringBuilder builder = new StringBuilder("Callable methods:\n");

        for (Method m : this.getClass().getDeclaredMethods()) {
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
}
