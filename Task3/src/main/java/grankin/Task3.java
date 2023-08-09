package grankin;

public class Task3 {

    public static void main(String[] args) {

        MyStack stack1 = new MyStack(10);

        System.out.println("Стек только что создан");
        System.out.println("Текущий размер: " + stack1.currentSize());
        System.out.println("Максимальный размер: " + stack1.maxSize());
        System.out.println("Пустой ли стек?: " + stack1.isEmpty());
        System.out.println("Переполнен ли стек?: " + stack1.isFull());
        System.out.println();

        for (int i = 0; i < 5; i++){
            stack1.push(i);
        }

        System.out.println("Стек наполовину заполнен");
        System.out.println("Текущий размер: " + stack1.currentSize());
        System.out.println("Максимальный размер: " + stack1.maxSize());
        System.out.println("Пустой ли стек?: " + stack1.isEmpty());
        System.out.println("Переполнен ли стек?: " + stack1.isFull());
        System.out.println();

        for (int i = 5; i < 10; i++){
            stack1.push(i);
        }

        System.out.println("Стек полностью заполнен");
        System.out.println("Текущий размер: " + stack1.currentSize());
        System.out.println("Максимальный размер: " + stack1.maxSize());
        System.out.println("Пустой ли стек?: " + stack1.isEmpty());
        System.out.println("Переполнен ли стек?: " + stack1.isFull());
        System.out.println();


        stack1.displayMyStack();

        System.out.println("\nДостаем все элементы\n");
        int s = stack1.maxSize();
        for (int i  = 0; i < s; i++){
            Object tmp = stack1.pop();
        }

        stack1.displayMyStack();

        System.out.println("Стек опустошон");
        System.out.println("Текущий размер: " + stack1.currentSize());
        System.out.println("Максимальный размер: " + stack1.maxSize());
        System.out.println("Пустой ли стек?: " + stack1.isEmpty());
        System.out.println("Переполнен ли стек?: " + stack1.isFull());
        System.out.println();

        System.out.println("==============================================================");

        MyQueue queue1 = new MyQueue(10);

        System.out.println("Очередь только что создана");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        for (int i = 0; i < 7; i++){
            queue1.enqueue(i);
        }

        System.out.println("Очередь частично заполнена - 7");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();

        System.out.println("Удалено 5 элементов");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        for (int i = 0; i < 3; i++){
            queue1.enqueue(i);
        }

        System.out.println("Добавлено 3 элемента");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        for (int i = 0; i < 5; i++){
            queue1.enqueue(i);
        }

        System.out.println("Добавлено 5 элементов");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        System.out.println(queue1.enqueue(4));

        System.out.println("Попытка добавть 1 элемент");
        System.out.println("Текущий размер: " + queue1.getCurrentSize());
        System.out.println("Максимальный размер: " + queue1.getMaxSize());
        System.out.println("Пустая ли очередь?: " + queue1.isEmpty());
        System.out.println("Переполнена ли очередь?: " + queue1.isFull());
        System.out.println();

        queue1.displayMyQueue();

        System.out.println("Удаляем все, параллельно выводя на экран:");
        int size = queue1.getCurrentSize();
        for (int i = 0; i < size; i++){
            System.out.println(queue1.top());
            queue1.dequeue();
        }
        System.out.println();

        queue1.displayMyQueue();
    }
}
