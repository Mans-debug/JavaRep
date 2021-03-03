import java.util.Iterator;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Stack stack = new Stack();
        stack.push(123);
        stack.push(412);
        stack.push(234);
        stack.push(65);
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
