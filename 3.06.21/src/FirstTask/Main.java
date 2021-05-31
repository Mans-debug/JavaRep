package FirstTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Randomizer randomizer = new Randomizer();
        WriterReader writerI = new WriterReader("writer");
        WriterReader readerI = new WriterReader("reader");
        for (int i = 0; i < 10; i++) {
            var ser = new Thread(writerI);
            var deser = new Thread(readerI);
            ser.start();
            ser.join();
            deser.start();
            deser.join();
        }

    }
}
