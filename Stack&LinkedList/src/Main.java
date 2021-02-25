import java.util.Iterator;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(12);
        stack.push(43);
        stack.push(67);
        Iterator iterator = stack.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
