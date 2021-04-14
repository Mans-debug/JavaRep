import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.stream.Stream;
//todo how to return int and void or int and string
public class Bot<T>
{
    HashMap<String, Method> commands;

    public Bot() {
        commands = new HashMap<>();

        for (Method m : this.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);
            for (String name : cmd.possibleNames())
                commands.put(name, m);
        }
    }


//    public String processUserInput(String input) {
//        if (input.isEmpty())
//            return "Я вас не понимаю";
//
//        String[] splitted = input.split(" ");
//        String command = splitted[0].toLowerCase();
//        String[] args = Arrays.copyOfRange(splitted, 1, splitted.length);
//
//        Method m = commands.get(command);
//
//        if (m == null)
//            return "Я вас не понимаю";
//
//        try
//        {
//            m.invoke(this, Stream.of(args)
//            .map(x -> Integer.parseInt(x))
//            .toArray());
//        } catch (IllegalAccessException e)
//        {
//            e.printStackTrace();
//        } catch (InvocationTargetException e)
//        {
//            e.printStackTrace();
//        }
//        return "";
//    }


    public <T> T commandProcessor(String input) throws InputMismatchException
    {
        if(input.isEmpty())
            throw new InputMismatchException("Wrong input");

        String[] strings = input.split(" ");
        String command = strings[0].toLowerCase();
        String[] args = Arrays.copyOfRange(strings,1,  strings.length);

        Method m =commands.get(command);

        if(m == null)
            throw new InputMismatchException("There is no such method");
        try
        {
            if(m.getReturnType().equals(Integer.TYPE))
           return ReturnEnum.IntegerType.comeback( Integer.toString( (Integer)
                    m.invoke(this, Stream.of(args)
                    .map(x -> Integer.parseInt(x))
                    .toArray())));
            else if(String.class.equals(m.getReturnType()))
                return ReturnEnum.StringType.comeback((String)
                        m.invoke(this, (Object) args));


        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Command(possibleNames = {"power"},
            args = " int the number, int the power",
    description = "returns a number X to the power Y",
    inProgress = false)
    int power(int x, int power)
    {
        int res = 1;
        if(power == 0)
            return 1;
        for (int i = 0; i < power; i++)
        {
            res *= x;
        }
        return res;
    }

    @Command(possibleNames = {"factorial of", "factorial", "fact"},
    args = "the factorial of X",
    description = "returns factorial of X",
    inProgress = false)
    int factorial(int x)
    {
        int res = 1;

        if (x == 0)
            return 0;
        for (int i = 1; i <= x; i++)
        {
            res *= i;
        }
        System.out.println(res);
        return res;
    }

    @Command(possibleNames = {"help"},
    description = "prints a list of callable commands", inProgress = false)
    public String help(String[] args) {
        StringBuilder builder = new StringBuilder("Я умею в такие команды:\n");

        for (Method m : this.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);
            builder.append(Arrays.toString(cmd.possibleNames())).append(": ").append(cmd.description()).append(" - ").append(cmd.args()).append("\n");
        }

        return builder.toString();
    }




}
