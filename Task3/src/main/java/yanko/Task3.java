package yanko;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
       /* Queue queue = new Queue();
        System.out.println(queue.isEmpty());
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        queue.dequeue();
        System.out.println(queue.top());*/

        /*Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        stack.push(12);
        System.out.println(stack);
        stack.push(13);
        stack.push(14);
        System.out.println(stack);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.isEmpty());*/

        Queue queue = new Queue();
        queue.enqueue(2);
        queue.enqueue(5);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);

    }
}
