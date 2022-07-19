import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Integer> {

    final private int end;
    final private int start;
    final private int[] array;

    public MyRecursiveTask(int end, int start, int[] array) {
        this.end = end;
        this.start = start;
        this.array = array;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        return switch (diff) {
            case 0 -> 0;
            case 1 -> array[start];
            case 2 -> array[start] + array[start + 1];
            default -> forkTasksAndGetResult();
        };
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        MyRecursiveTask task1 = new MyRecursiveTask(start, middle, array);
        MyRecursiveTask task2 = new MyRecursiveTask(middle, end, array);
        task1.fork();
        task2.fork();
        return task1.join() + task2.join();
    }
}
