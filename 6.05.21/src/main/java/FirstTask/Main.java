import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        TreeSet<String> treeSet = new TreeSet<>((x,y) -> x.length() - y.length());
        String str = "a sfsdfasd fs sdff sdfwe asdfrtg adf s df sd f df as df sadfsadfsadf  asdfasdfasdfsadf asdfsadfasdfsadfasdfsadfasd asdfds";
        for (String word:
             str.split(" ")) {
            treeSet.add(word);
        }
        for (String word:
             treeSet) {
            System.out.println(word);
        }
    }
}
