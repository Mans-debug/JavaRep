/*
import java.util.stream.Stream;

public class Class{
    public static final Integer c = 0;
    public static void main(String[] args) {
        Stream.iterate(0, x -> x++).limit(100).parallel().forEach(x ->{
            synchronized (c){
                c = c + 1;
            }
        } );
    }
}*/
