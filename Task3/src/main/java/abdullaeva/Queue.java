package abdullaeva;

import java.util.Arrays;

/**
 * Класс Queue, реализующий кольцевую Очередь на массиве Object
 */
public class Queue {
    /**
     * Размер очереди
     */
    private final int maxSize;
    /**
     * Массив для хранения объектов очереди
     */
    private final Object[] queueArray;
    /**
     * Указатель на начальный объект очереди
     */
    private int head;
    /**
     * Указатель на конец очереди
     */
    private int tail;

    /**
     * Код для обозначения успешного завершения работы метода
     */
    public static final int SUCCESS = 1;
    /**
     * Код для обозначения ошибки в работе метода
     */
    public static final int ERROR = -1;

    public Queue(int m) {
        this.maxSize = m;
        this.queueArray = new Object[maxSize];
        this.head = -1;
        this.tail = -1;
    }

    /**
     * Метод проверяет очередь на пустоту.
     * Происходит проверка равенства индекса начала очереди со значением,
     * выходящим из диапазона индексации массива, равным -1.
     *
     * @return true, если очередь пуста.
     */
    public boolean isEmpty() {
        return (head == -1);
    }

    /**
     * Метод вставляет объект в конец очереди (реализую кольцевую очередь).
     * Происходит проверка на заполненность очереди: Если начало очереди - первый элемент массива
     * и конец очереди - последний элемент массива, то очередь заполнена, вставка невзможна;
     * если конец очереди перемещается в начало массива и отличается от
     * начала очереди на 1, значит очередь также заполнена и вставка невозможна.
     * Если добавляется первый объект в очередь, то указатель на начало очереди обнуляем.
     * Затем вычисляется, куда вставить новый объект в очередь - конец очереди.
     * После вставляем объект в очередь и выводим об этом сообщение.
     *
     * @param element - объект, который необходимо вставить в конец очереди.
     * @return - 1, если объект вставлен, -1 - если ошибка вставки.
     */
    public int enQueue(Object element) {
        if ((head == 0 && tail == maxSize - 1) || (head == tail + 1)) {
            System.out.println("Ошибка вставки");
            return ERROR;
        } else {
            if (head == -1) {
                head = 0;
            }
            tail = (tail + 1) % maxSize;
            queueArray[tail] = element;
            System.out.println("В очередь добавлен объект " + queueArray[tail]);
        }
        return SUCCESS;
    }

    /**
     * Метод удаляет объект из начала очереди.
     * Происходит проверка очереди на пустоту с помощью метода isEmpty, если очередь пуста,
     * выводится сообщение об ошибке удаления.
     * Если указатели начала и конца совпадают, значит в очереди только один объект, поэтому
     * указатели начала и конца очереди сбрасываются и удаляется последний объект очереди,
     * очередь становится пустой.
     * Если очередь не пуста и в ней больше одного объекта, объект удаляется из начала очереди -
     * указатель на начало очереди сдвигается вперед по массиву и удаляется объект.
     *
     * @return - 1, если удаление объекта из начала очереди произошло успешно, -1, если ошибка удаления.
     */
    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Удаление из очереди невозможно");
            return ERROR;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            queueArray[head] = null;
            head = (head + 1) % maxSize;
            System.out.println("Объект удалён из начала очереди");
        }
        return SUCCESS;
    }

    /**
     * Метод возвращает верхний объект очереди.
     * Происходит проверка очереди на пустоту с помощью метода isEmpty, если очередь пуста,
     * выводится сообщение об этом.
     * Затем выводится значение верхнего объекта очереди.
     *
     * @return - верхний объект очереди
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Очередь пуста");
            return null;
        }
        System.out.println("Верхний объект очереди: " + queueArray[head]);
        return queueArray[head];
    }

    /**
     * Метод позволяет получить объекты очереди, не изменяя саму очередь.
     *
     * @return созданную с помощью метода clone копию массива очереди.
     */
    public Object[] getAll() {
        return queueArray.clone();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Очередь пуста";
        }
        StringBuilder builder = new StringBuilder("Очередь: ");
        if (head <= tail) {
            for (int i = head; i <= tail; i++) {
                builder.append(queueArray[i]);
                if (i != tail) {
                    builder.append(", ");
                } else {
                    builder.append(".");
                }
            }
        } else {
            for (int i = head; i < queueArray.length; i++) {
                builder.append(queueArray[i]).append(", ");
            }
            for (int i = 0; i <= tail; i++) {
                builder.append(queueArray[i]);
                if (i != tail) {
                    builder.append(", ");
                } else {
                    builder.append(".");
                }
            }
        }
        return builder.toString();
    }
}