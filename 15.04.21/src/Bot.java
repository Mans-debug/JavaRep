import javax.jws.Oneway;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.stream.Stream;
//todo how to return int and void or int and string
public class Bot
{
    private HashMap<String, Method> commands;

    public Bot() {
        commands = new HashMap<>();

        Class botCommandsClass = new BotCommands().getClass();
        for (Method m : botCommandsClass.getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Command.class))
                continue;

            Command cmd = m.getAnnotation(Command.class);

            //if a method is still in progress, we don't include it in the map
            if(cmd.inProgress())
                continue;

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


    public String commandProcessor(String input) throws InputMismatchException
    {
        if(input.isEmpty())
            throw new InputMismatchException("Wrong input");

        String[] strings = input.split(" ");
        String command = strings[0].toLowerCase();
        String[] args = Arrays.copyOfRange(strings,1,  strings.length);

        Method m = commands.get(command);

        if(m == null)
            throw new InputMismatchException("There is no such method");

        try
        {
           return (String) m.invoke(new BotCommands(), (Object) args);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while calling the method: " + m.getName());
        }
            return "Something went wrong while processing the input command";
    }






}
