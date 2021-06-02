import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

final class Randomizer {
    private String getName() {
        try {
            Scanner sc = new Scanner(new File("src/main/java/names.txt"));
            var names = sc.tokens().toArray(String[]::new);
            return names[new Random().nextInt(names.length - 1)];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find names.txt");
        }
        return "ERROR! FILE NOT FOUND";
    }

    private int getAge() {
        return new Random().nextInt(100);
    }


    public Person nextRandom() {
        return new Person(getName(), getAge());
    }
}