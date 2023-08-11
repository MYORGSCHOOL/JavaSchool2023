package skrebkov;

public class Task3 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Queue queue = new Queue();
        String[] stringsArr = {"first", "second", "third", "fourth"};
        for (String s: stringsArr) {
            stack.push(s);
            queue.enqueue(s);
        }

        System.out.println("Стэк " + stack);
        System.out.println("Очередь " + queue);
        stack.pop();
        queue.dequeue();
        System.out.println("Стэк " + stack);
        System.out.println("Очередь " + queue);
    }
}
