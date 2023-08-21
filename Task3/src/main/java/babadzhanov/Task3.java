package babadzhanov;

public class Task3 {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.top());
        System.out.println(queue.isEmpty());

        System.out.println("---------------------------");

        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        System.out.println("Верхний элемент без удаления: " + stack.top());
        System.out.println("Элемент удаленный сверху: " + stack.pop());
        System.out.println(stack);
        System.out.println(stack.isEmpty());
    }
}
