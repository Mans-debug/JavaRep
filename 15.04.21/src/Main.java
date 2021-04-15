public class Main
{
    public static void main(String[] args)
    {
        Bot bot = new Bot();
        String string = bot.commandProcessor("reverse hello world");
        System.out.println(string);
    }
}
