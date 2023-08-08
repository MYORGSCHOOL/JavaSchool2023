package volovnik;

import java.util.Arrays;

public class Task3 {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        System.out.println(Arrays.toString(queue.getAll()));
        queue.dequeue();
        queue.dequeue();
        System.out.println(Arrays.toString(queue.getAll()));
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        System.out.println(Arrays.toString(queue.getAll()));

        System.out.println("=====");

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(Arrays.toString(stack.getAll()));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(Arrays.toString(stack.getAll()));
    }
}
