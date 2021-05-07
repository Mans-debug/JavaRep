import java.util.Comparator;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.*;

public class SecondTask {
    public static void main(String[] args) {
        int streamSize = 20;
        new Random().ints(streamSize, 0, 1000)
                .boxed()
                .sorted((x, y) -> Integer.compare(x % 10, y % 10))
                .map(x -> x / 10)
                .collect(Collectors.toSet())
                .forEach(System.out::println);


    }
}
