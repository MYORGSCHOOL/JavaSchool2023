package proskurina;

public class Task3 {
    public static void main(String[] args) {
        // Стек
        var stack = new Stack();
        Object obj = "a";
        System.out.println("push obj " + stack.push(obj));
        System.out.println("top " + stack.top());
        System.out.println("isEmpty " + stack.isEmpty());
        System.out.println("pop obj " + stack.pop());
        Object[] stackElements = stack.toArray();
        System.out.println(stackElements[0]);
        // Очередь
        var q = new Queue();
        System.out.println("enqueue obj" + q.enqueue(obj));
        System.out.println("top " + q.top());
        System.out.println("isEmpty  " + q.isEmpty());
        System.out.println("dequeue obj" + q.dequeue());
        Object[] queueElements = q.toArray();
        System.out.println(queueElements[0]);
    }
}