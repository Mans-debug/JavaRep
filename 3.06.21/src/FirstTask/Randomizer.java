package FirstTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public final class Randomizer extends GlobalRandomizer {
    private String getName() {
        try {
            Scanner sc = new Scanner(new File("src/FirstTask/names.txt"));
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

    private Countries getCountry() {
        return Countries.values()[new Random().nextInt(Countries.values().length - 1)];
    }

    @Override
    public Person nextRandom() {
        return new Person(getName(), getAge(), getCountry());
    }
}