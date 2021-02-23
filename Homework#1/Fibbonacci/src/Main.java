import java.util.Iterator;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        RangeInterval interval = new RangeInterval(10 , 4);
        
        Iterator<Integer> iterator = interval.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

    }
}
