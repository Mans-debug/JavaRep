import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class ThirdTask {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\mansu\\IdeaProjects\\13.05.21\\task3.txt");
        Scanner sc = new Scanner(file);
        HashMap<String, Integer> map = new HashMap<>();

        sc.tokens()
                .map(x -> x.split("[|]"))
                .map(x -> map.containsKey(x[0]) ?
                        map.put(x[0], map.get(x[0]) + Integer.parseInt(x[1])) :
                        map.put(x[0], Integer.parseInt(x[1])));

        map.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
    }
}
