import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SecondTask {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner sc = new Scanner(new File("src/input.txt"));
        List<String> words = Arrays.stream(sc.tokens()
                .collect(Collectors.joining(" ", "", ""))
                .split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        var t1 = new Thread(new Sorter("odd", x -> x % 2 == 1, words));
        var t2 = new Thread(new Sorter("even", x -> x % 2 == 0, words));
        t1.start();
//        t1.join();
        t2.start();
//        t2.join();
    }
}

class Sorter implements Runnable {
    List<String> list;
    Predicate<Integer> predicate;
    List<String> res = new ArrayList<>();
    String id;
    int counter = 0;

    public Sorter(String id, Predicate<Integer> predicate, List<String> list) {
        this.id = id;
        this.list = list;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        System.out.println("Started adding " + id);
        list.stream()
                .filter(x -> predicate.test(x.chars().boxed().collect(Collectors.toSet()).size()))
                .forEach(x -> {
                    System.out.println("Thread " + id
                            + " added " + x);
                    counter++;
                    res.add(x);
                });
        System.out.println("Thread " + id + " word counter " + counter);

    }
}