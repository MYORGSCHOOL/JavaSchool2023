package abdullaeva;

public class Task3 {
    public static void main(String[] args) {
        Stack stack = new Stack(3);
        stack.isEmpty();
        stack.push(1);
        stack.push("a");
        stack.push(3);
        stack.push(4);

        String arrayStack = stack.toString();
        System.out.println(arrayStack);

        stack.pop();
        stack.top();

        arrayStack = stack.toString();
        System.out.println(arrayStack);

        Queue queue = new Queue(5);
        queue.isEmpty();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);

        String arrayQueue = queue.toString();
        System.out.println(arrayQueue);

        queue.top();
        queue.deQueue();
        queue.top();
        queue.enQueue(6);
        queue.top();
        queue.enQueue(7);
        queue.deQueue();
        queue.top();

        arrayQueue = queue.toString();
        System.out.println(arrayQueue);

        queue.deQueue();
        queue.enQueue(8);
        queue.enQueue(9);

        arrayQueue = queue.toString();
        System.out.println(arrayQueue);

        queue.top();
    }
}
