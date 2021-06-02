import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
            case 0 -> System.exit(0);
            case 1 -> new Thread(Main::task1).start();
            case 2 -> new Thread(Main::task2).start();
            case 3 -> new Thread(Main::task3).start();
            default -> System.out.println("Чет не то");
        }
    }
    public static void task1()  {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\mansu\\IdeaProjects\\Work\\task1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc.tokens()
                .map(x -> new Customer(x.split("[|]")[0], x.split("[|]")[1], Integer.parseInt(x.split("[|]")[2])))
                .collect(Collectors.groupingBy(x -> x.name()));
        var map1 = map.entrySet().stream().collect(Collectors.toMap(
                x-> x.getKey(),
                x->x.getValue().stream().collect(Collectors.groupingBy(z -> z.prod()))
        ));
        var map3 = map1.entrySet().stream()
                .collect(Collectors.toMap(
                        x->x.getKey(),
                        x -> x.getValue().entrySet().stream().map(z -> z.getValue() + " " +
                                z.getValue().stream().mapToInt(t -> t.quant()).sum())
                                .toList()
                ));
        map3.entrySet().stream().map(x -> x.getKey() + " " +
                x.getValue().stream().)


    }
    public static void task2()  {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("task2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc.tokens().map(x -> x.split("[|]"))
                .collect(Collectors.groupingBy(x -> x[0]))
                .entrySet()
                .stream()
                .map(x -> x.getKey() + " " + x.getValue().stream()
                        .map(y -> Integer.parseInt(y[1]))
                        .reduce(Integer::sum).get().toString())
                .collect(Collectors.toMap(x -> x.split(" ")[0], x -> Integer.parseInt(x.split(" ")[1])));
        var average = map.values().stream().mapToInt(integer -> integer).average().getAsDouble();
        map.entrySet().stream().filter(x -> x.getValue() > average).forEach(x -> System.out.println(x.getKey() +" " + x.getValue()));


    }
    public static void task3() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("C:\\Users\\mansu\\IdeaProjects\\Work\\task1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = sc.tokens()
                .map(x -> new Customer(x.split("[|]")[0], x.split("[|]")[1], Integer.parseInt(x.split("[|]")[2])))
                .collect(Collectors.groupingBy(x -> x.name()));
       var map1 = map.entrySet().stream().collect(Collectors.toMap(
               x-> x.getKey(),
               x->x.getValue().stream().collect(Collectors.groupingBy(z -> z.prod()))
       ));

    }

}
record Customer(String name, String prod, Integer quant){}
