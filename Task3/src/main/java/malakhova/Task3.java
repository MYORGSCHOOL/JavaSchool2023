package malakhova;

public class Task3 {
    public static void main(String[] args) {
        int size = 5;
        System.out.println("Работа с очередью");
        Queue que = new Queue(size);
        try {
            que.enqueue(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Очередь переполнена");
        }
        try {
            System.out.println("Верхний элемент очереди: " + que.top());
        } catch (IllegalArgumentException e) {
            System.out.println("Очередь пустая");
        }
        System.out.println("Очередь пустая: " + que.isEmpty());
        Object[] array1;
        try {
            System.out.println("Очередь:");
            array1 = que.getQueue();
            for (int j = 0; j < size; j++) {
                if (array1[j] != null) {
                    System.out.println(array1[j]);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Очередь пустая");
        }
        try {
            que.dequeue();
        } catch (IllegalArgumentException e) {
            System.out.println("Очередь пустая");
        }
        System.out.println("Работа со стеком");
        Stack stack = new Stack();
        stack.push(2);
        Object[] array2;
        try {
            System.out.println("Стек:");
            array2 = stack.getStack();
            int lenghtArray2 = array2.length;
            for (int j = 0; j < lenghtArray2; j++) {
                if (array2[j] != null) {
                    System.out.println(array2[j]);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Стек пустой");
        }
        try {
            System.out.println("Верхний элемент стека: " + stack.top());
        } catch (IllegalArgumentException e) {
            System.out.println("Стек пустой");
        }
        try {
            System.out.println("Удаление элемента:" + stack.pop());
        } catch (IllegalArgumentException e) {
            System.out.println("Стек пустой");
        }
        System.out.println("Стек пустой: " + stack.isEmpty());
    }
}
