package dushkina;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        Queue queuePeople = new Queue(5);
        queuePeople.enqueue("1");
        queuePeople.enqueue("2");
        queuePeople.enqueue("3");
        queuePeople.enqueue("4");
        queuePeople.enqueue("5");
        System.out.println(queuePeople);
        System.out.println("Первый элемент очереди queuePeople: " + queuePeople.top() + "\nВыведем его из очереди и получаем:");
        queuePeople.dequeue();
        System.out.println(Arrays.toString(queuePeople.getAll()));

        Stack stackBook = new Stack(0);
        for (int i = 0; i < 12; i++) {
            stackBook.push("book" + (i + 1));
        }
        System.out.println(stackBook);
        System.out.println("Первый элемент стека stackBook: " + stackBook.top() + "\nЗабираем книгу из стека:");
        stackBook.pop();
        System.out.println(Arrays.toString(stackBook.getAll()));
    }
}
