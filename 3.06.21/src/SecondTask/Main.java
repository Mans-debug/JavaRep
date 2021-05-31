package SecondTask;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] alphabet = {"абвгдеёжзийклмнопрстуфхцчшщъыьэюя", "abcdefghijklmnopqrstuvwxyz"};
        int x = readNumber();
        Random rand = new Random();
        var executor = Executors.newScheduledThreadPool(x);
        for (int i = 0; i < x; i++) {
            final int num = i;
            executor.schedule(() -> {
                int rus_eng = rand.nextInt(1000) % 2;
                int numberOfsymbols = rand.nextInt(6);
                for (int j = 0; j < numberOfsymbols; j++) {
                    Character ch = alphabet[rus_eng].charAt(rand.nextInt(alphabet[rus_eng].length()));
                    stringBuilder.append(ch.toString());
                }
                System.out.println("Thread #" + num + " has added new chars! \n" + stringBuilder.toString());
            }, 3, TimeUnit.SECONDS);
        }
        executor.shutdown();
        while (!executor.awaitTermination(24L, TimeUnit.HOURS)) {
            System.out.println("Not yet. Still waiting for termination");
        }
        System.out.println("The final result is " + stringBuilder.toString());

    }

    public static int readNumber() {
        Scanner sc = new Scanner(System.in);
        var num = sc.next();
        int x = Integer.parseInt(num);
        if (x < 0)
            throw new InvalidParameterException("Вы ввели отрицательное число!");
        return x;
    }
}