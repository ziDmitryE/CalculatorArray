import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        int[] array = getInitArray(100000000);
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        System.out.println("Результат: " + sum);
        System.out.println("однопоточное решение заняло = " + (System.currentTimeMillis() - start));

        MyRecursiveTask task = new MyRecursiveTask(100000000, 0, array);
        long start2 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("Результат: " + forkJoinPool.invoke(task));
        System.out.println("многопоточное решение заняло = " + (System.currentTimeMillis() - start2));
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = new Random().nextInt(1000000000);
        }
        return array;
    }
}
