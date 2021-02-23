
import java.util.Iterator;

public class Range implements Iterable<Integer> {
    protected final int length;
    protected int counter;

    public Range(int length) {
        this.length = length;
        counter = 0;
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return counter < length;
            }

            @Override
            public Integer next() {
                return counter++;
            }
        };
    }
}
