import java.util.Random;
import java.util.function.Predicate;

public class SecondTask {
    public static void main(String[] args) {
        int size = 20;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(1000);
        }
        arr = filterArray(arr, x -> x % 2 == 0);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    public static int[] filterArray(int arr[], Predicate<Integer> predicate) {
        int res[];
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {

            if (predicate.test(arr[i]))
                counter++;
        }
        res = new int[counter];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (predicate.test(arr[i])) {
                res[j] = arr[i];
                j++;
            }
        }
        return res;
    }
}
