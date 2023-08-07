package tsimmer;

public class Task3 {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(33);
        System.out.println("Стек");
        System.out.println(stack.isEmpty());
        System.out.println("Первый элемент: " + stack.top());
        System.out.println("Удаленный элемент: " + stack.pop());
        System.out.println("Верхний элемент после удаления: " + stack.top());
        System.out.println(" ");

        Queue queue = new Queue(5);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println("Очередь");
        System.out.println(queue.isEmpty());
        System.out.println("Первый элемент: " + queue.top());
        System.out.println("Удаленный элемент:" + queue.dequeue());
        System.out.println("Первый элемент после удаления: " + queue.top());

    }
}
