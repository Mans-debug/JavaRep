import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class ThirdTask {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\mansu\\IdeaProjects\\13.05.21\\task3.txt");
        Scanner sc = new Scanner(file);
        HashMap<String, Integer> map = new HashMap<>();
        //one way

       sc.tokens()
                .map(x -> x.split("[|]"))
                .map(x -> map.containsKey(x[0]) ?
                        map.put(x[0], map.get(x[0]) + Integer.parseInt(x[1])) :
                        map.put(x[0], Integer.parseInt(x[1]))).collect(Collectors.toList());

        map.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
        
        //another way
        /*String[] s = sc.tokens().toArray(String[]::new);
        var map1 = Arrays.stream(s).map(x -> x.split("[|]"))
                .collect(Collectors.groupingBy(x -> x[0]))
                .entrySet()
                .stream()
                .collect
                        (Collectors.toMap(x -> x.getKey(), x -> x.getValue()
                                .stream().map(a -> Integer.parseInt(a[1])).reduce((z, t) -> z + t ).get()));

        map1.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));*/
    }
}
