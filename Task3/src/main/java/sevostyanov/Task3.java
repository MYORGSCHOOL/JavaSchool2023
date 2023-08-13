package sevostyanov;

public class Task3 {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        System.out.println("Очередь:");
        queue.enqueue("Элемент 1");
        queue.enqueue("Элемент 2");
        queue.enqueue("Элемент 3");
        System.out.println("Первый элемент: " + queue.top());
        System.out.println("Удаленный элемент: " + queue.dequeue());
        System.out.println("Первый элемент в очереди после удаления: " + queue.top());
        System.out.println("Стек:");
        Stack stack = new Stack(5);
        stack.push("Элемент 1");
        stack.push("Элемент 2");
        stack.push("Элемент 3");
        System.out.println("Верхний элемент стека: " + stack.top());
        System.out.println("Удаление верхневого элемента из стека: " + stack.pop());
        System.out.println("Верхний элемент стека после удаления: " + stack.top());
    }
}


