package FirstTask;

import java.util.ArrayList;
import java.util.logging.*;
import java.io.*;

public class WriterReader implements Runnable {
    private final Logger logger = Logger.getLogger("Logger");
    int parameter;
    private final File file = new File("src/FirstTask/people.txt");

    public WriterReader(String mode) {
        if (mode.toLowerCase().equals("writer")) {
            parameter = 1;
        }
        if (mode.toLowerCase().equals("reader")) {
            parameter = 2;
        }

    }

    public void serialize(Person person) {
        ArrayList<Person> people = getArray();
        if (people == null) {
            people = new ArrayList<>();
        }
        people.add(person);
        try (FileOutputStream fileOut =
                     new FileOutputStream(file);
             ObjectOutputStream outputStream =
                     new ObjectOutputStream(fileOut)) {
            outputStream.writeObject(people);
            outputStream.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "A person has been added " + person.name());
    }

    public void deserialize() {
        var people = getArray();
        logger.log(Level.INFO, people.get(people.size() - 1).toString());
    }

    public ArrayList<Person> getArray() {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(file);
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(fileInputStream);
            var list = (ArrayList<Person>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        if(parameter == 1){
            Randomizer randomizer = new Randomizer();
            serialize(randomizer.nextRandom());
        }
        if(parameter == 2){
            deserialize();
        }
    }
}
