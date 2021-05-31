package ThirdTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.sqrt;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        int number = SecondTask.Main.readNumber() + 1;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ArrayList<Integer> list = new ArrayList<>();
        int mod = number % 4;
        int first = 0;
        int second = 0;

            for (int i = 0; i < 4; i++) {
                second += number / 4;
                if (mod != 0) {
                    second++;
                    mod--;
                }
                int finalFirst = first;
                int finalSecond = second;
                executor.execute(() -> {
                    for (int j = finalFirst; j < finalSecond; j++) {

                        if (isPrime(j)) {
                            list.add(j);
                        }
                    }
                });
                first = second + 1;
            }
        executor.shutdown();
        list.stream().sorted(Integer::compare).forEach(System.out::println);
    }

    public static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        } else if (n <= 1 || (n % 2) == 0 || (n % 3) == 0) {
            return false;
        }

        int i = 5;
        while (i * i <= n) {
            if ((n % i) == 0 || (n % (i + 2)) == 0) {
                return false;
            }
            i += 6;
        }

        return true;
    }
}
