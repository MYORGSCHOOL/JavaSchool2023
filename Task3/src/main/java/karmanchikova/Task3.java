package karmanchikova;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        System.out.println(queue);
        System.out.println("Первый элемент в очереди: " + queue.top());
        queue.dequeue();
        System.out.println("Текущее состояние очереди: " + Arrays.toString(queue.getAll()));

        Stack stack = new Stack(2);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        System.out.println(stack);
        stack.pop();
        System.out.println("Верхний элемент в стеке: " + stack.top());
        System.out.println("Текущее состояние стека: " + Arrays.toString(stack.getAll()));
    }
}