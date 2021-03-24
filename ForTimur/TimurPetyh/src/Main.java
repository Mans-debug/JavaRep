import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException
    {
        new FileWriter(new File(".\\text.txt"), false).close();// deletes the contents of the file
        ArrayList<Order> orders = new ArrayList<>();

        Console console = new Console();
        console.WriteFile(orders);
        console.ReadFile();
    }

}
