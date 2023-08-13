package druzhinin;

/**
 * Класс для использования очереди Queue и стека Stack. *
 */
public class Task3 {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        System.out.println(queue);
        queue.enqueue(1);
        queue.enqueue(2.0d);
        queue.enqueue(3.0f);
        queue.enqueue(4L);
        queue.enqueue(true);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        queue.enqueue(false);
        System.out.println(queue);
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue);
        System.out.println("Последний элемент очереди: " + queue.top());
        queue.dequeue();
        System.out.println(queue + "\n");

        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2.0d);
        stack.push(3.0f);
        stack.push(4L);
        stack.push(true);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack);
        stack.push(100);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println("Последний элемент стека через top(): " + stack.top());
        Object o = stack.pop();
        System.out.println(stack);
        System.out.println("Последний элемент стека через pop(): " + o);
    }
}
