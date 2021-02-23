import java.util.Iterator;

public class Fibonacci implements Iterable<Integer>
{
    final int length;
    int counter = 1;
    private int previous = 0;
    public Fibonacci(int lenght)
    {
        this.length = lenght;
    }

    public Iterator iterator()
    {
        return new Iterator()
        {
            @Override
            public boolean hasNext()
            {
                return counter < length;
            }

            @Override
            public Object next()
            {
                int temp = counter;
                counter += previous;
                previous = temp;
                return counter;
            }
        };
    }

}
