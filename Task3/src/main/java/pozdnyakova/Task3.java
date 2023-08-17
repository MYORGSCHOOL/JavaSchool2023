package pozdnyakova;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        for (int i = 0; i != 20; i++) {
            stack.push(i);
        }
        System.out.println(Arrays.toString(stack.getAll()));
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.isEmpty());
        CircularQueue circularQueue = new CircularQueue(5);
        System.out.println(circularQueue.isEmpty());
        for (int i = 0; i != 5; i++) {
            circularQueue.enqueue(i);
            if (i == 3) {
                circularQueue.dequeue();
            }
        }
        System.out.println(circularQueue.isEmpty());
        System.out.println(circularQueue.top());
        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.enqueue(6.25);
        circularQueue.enqueue("a");
        System.out.println(circularQueue.top());
        circularQueue.enqueue("1");
        try {
            circularQueue.enqueue("1");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(circularQueue.getAll()));
    }
}
