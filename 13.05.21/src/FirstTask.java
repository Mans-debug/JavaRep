import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class FirstTask {
    public static void main(String[] args) {
        String str = "А вот так вот работает стрим апи: абезьяна, ачки, авца, акончить, attention, ability";
        Arrays.stream(str.split("\\s"))
                .filter(x -> x.charAt(0) == 'a' || x.charAt(0) == 'а')
                .map(x -> {
                    if(x.matches("[A-Za-zА-Яа-я]+\\W")) {
                        StringBuilder builder = new StringBuilder(x).deleteCharAt(x.length() - 1);
                        return builder.toString();
                    }
                    return x; })
                .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                .forEach(System.out::println);

    }
}
