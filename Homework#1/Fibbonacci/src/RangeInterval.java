import java.util.Iterator;

public class RangeInterval extends Range
{

    public RangeInterval(int start, int end) throws Exception
    {
        super(end);

        try
        {
            if(start<end)
            {
                counter = start;

            }
            else
                throw new Exception();
        }
        catch (Exception e)
        {
            System.out.println("Exception: the start is bigger than the end!");
            counter = end;
        }
    }
    //либо же мне пришлось бы писать еще одну реализацию Iterable, что привело бы
    //к дублированию кода

}
