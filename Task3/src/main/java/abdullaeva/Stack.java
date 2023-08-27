package abdullaeva;

import java.util.Arrays;
import static java.lang.Integer.MAX_VALUE;

/**
 * Класс Stack, реализующий Стэк на массиве Object
 */
public class Stack {
    /**
     * Максимально допустимый размер для стэка
     */
    private static final int MAX_STACK_SIZE = MAX_VALUE - 1;
    /**
     * Размер стэка
     */
    private int maxSize;
    /**
     * Массив для хранения объектов стэка
     */
    private Object[] stackArray;
    /**
     * Указатель на верхний объект в стэке
     */
    private int top;

    /**
     * Код для обозначения успешного завершения работы метода
     */
    public static final int SUCCESS = 1;
    /**
     * Код для обозначения ошибки в работе метода
     */
    public static final int ERROR = -1;

    public Stack(int m) {
        this.maxSize = m;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }

    /**
     * Метод проверяет стек на пустоту.
     * Происходит проверка равенства индекса верхушки стека со значенем,
     * выходящим из диапазона индексации массива, равным -1.
     * Если верхушка стека равна -1, значит в массиве нет элементов.
     *
     * @return true, если стек пуст.
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Метод вставляет объект в стек сверху, если стек не заполнен.
     * Для проверки заполненности метода проверяется равенство индекса верхушки стека
     * и максимальной размерности стека минус 1 (так как индексы в массивах начинаются с 0).
     * Если стек заполнен, то выводит сообщение об ошибке вставки и возвращает -1.
     * При добавлении объекта element в стек также инкрементируется индекс массива,
     * указывающий на верхушку стека. Объект, который вставили, становится верхним в стеке.
     *
     * @param element - объект, который нужно добавить в стек.
     * @return - при успешной отработке метода возвращает 1, при ошибке возвращает -1.
     */
    public int push(Object element) {
        if (top >= MAX_STACK_SIZE) {
            System.out.println("Ошибка вставки");
            return ERROR;
        }
        if (top >= maxSize - 1) resizeArray();
        stackArray[++top] = element;
        System.out.println("В стек добавлен объект " + element);
        return SUCCESS;
    }

    /**
     * Метод осуществляет увеличение размера стэка.
     * Объявляется новая размерность стэка, в данном случае размер увеличивается в двое.
     * Если новый размер массива выходит больше, чем максимальная размерность для стэка, то
     * в качестве размера массива стэка используем максимальную размерность стэка
     * Затем текущий массив стэка копируется в новый массив с новой размерностью.
     * Размерность стэка переопределяется.
     */
    public void resizeArray() {
        long arraySize = maxSize * 2L;
        int newSize = (arraySize < MAX_STACK_SIZE) ? (int) arraySize : MAX_STACK_SIZE;
        stackArray = Arrays.copyOf(stackArray, newSize);
        maxSize = newSize;
        System.out.println("Размер стэка расширен, новый размер: " + stackArray.length);
    }

    /**
     * Метод возвращает верхний объект стека без его удаления, если стек не пуст.
     * Если стек пуст, выводится сообщение об этом и возвращается null.
     * Для проверки пустоты стека вызывается метод isEmpty.
     *
     * @return верхний объект стека, при ошибке возвращает null
     */
    public Object top() {
        if (isEmpty()) {
            System.out.println("Стек пуст");
            return null;
        }
        System.out.println("Верхний объект стека: " + stackArray[top]);
        return stackArray[top];
    }

    /**
     * Метод возвращает верхний объект стека после его удаления.
     * Если стек пуст, то выводится сообщение об этом и возвращается null.
     * Для проверки заполненности стека вызывается метод isEmpty.
     * Печатается сообщение о том, какой объект извлечен из верхушки стека.
     * Затем верхний объект сохраняется в отдельную переменную для того,
     * чтобы вернуть его при корректной отработке метода.
     * В конце происходит удаление верхнего объекта стека - декрементируется
     * индекс верхушки стека.
     *
     * @return верхний объект стека после его удаления, при ощибке возвращает null.
     */
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Стек пуст: из стека невозможно удалить и извлечь объект");
            return null;
        }
        System.out.println("Из стека извлечен объект " + stackArray[top]);
        Object head = stackArray[top];
        stackArray[top] = null;
        top--;
        return head;
    }

    /**
     * Метод позволяет получить объекты стэка, не изменяя сам стэк.
     *
     * @return созданную с помощью метода clone копию массива стэка.
     */
    public Object[] getAll() {
        return stackArray.clone();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Стэк пуст";
        }
        StringBuilder builder = new StringBuilder("Стэк: ");
        for (int i = 0; i <= top; i++) {
            builder.append(stackArray[i]);
            if (i != top) {
                builder.append(", ");
            } else {
                builder.append(".");
            }
        }
        return builder.toString();
    }
}
