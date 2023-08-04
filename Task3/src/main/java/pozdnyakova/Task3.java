package pozdnyakova;

public class Task3 {
    public static void main(String[] args) {
        Stack stack = new Stack(10);
        System.out.println(stack.isEmpty());
        for (int i = 0; i != 5; i++) {
            stack.push(i);
        }
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
    }
}
