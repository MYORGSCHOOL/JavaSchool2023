package grossu;

/**
 * Класс для работы с классами Queue и Stack
 */
public class Task3 {
    public static void main(String[] args) {
        Queue q = new Queue(4);
        q.enqueue(new Object());
        q.enqueue(new Object());
        q.enqueue(new Object());
        q.enqueue(new Object());
        System.out.println(q);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println(q);
        q.enqueue(new Object());
        System.out.println(q);

        Stack stack = new Stack(4);
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
        stack.push(new Object());
        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.push(new Object());
        System.out.println(stack);
    }

}
