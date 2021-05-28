public class FirstTask {
    public static Integer result = 1;

    public static void main(String[] args) throws InterruptedException {
        var x = find(5);
        System.out.println("The final result is " + x);
    }

    public static int find(int x) throws InterruptedException {
        int mod = x % 4;
        int first = 1;
        int second = 0;
        for (int i = 0; i < 4; i++) {
            second += x / 4;
            if (mod != 0) {
                second++;
                mod--;
            }
            var thread = new Thread(new FactorialThread(i, first, second));
            thread.start();
            thread.join();
            first = second + 1;
        }
        int temp = 1;
        temp = result;
        FirstTask.result = 1;
        return temp;
    }
}

class FactorialThread implements Runnable {
    int id;
    int first;
    int second;

    public FactorialThread(int id, int first, int second) {
        this.id = id;
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        int res = 1;
        System.out.println("Thread #" + id + " calculating");
        for (int i = first; i <= second; i++) {
            res *= i;
        }
        System.out.println("Result of the thread #" + id + " is " + res);
        FirstTask.result *= res;
        System.out.println("The result after thread #" + id + "is " + FirstTask.result);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

