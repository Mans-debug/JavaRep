import java.util.Iterator;

public class Main
{
    public static void main(String[] args)
    {
        Fibonacci fib = new Fibonacci(155);
        Iterator iterator = fib.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
