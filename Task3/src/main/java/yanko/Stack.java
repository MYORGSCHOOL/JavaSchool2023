package yanko;

/**
 * класс, реализующий стек
 */
public class Stack {
    /**
     * элементы, хранящиеся в стеке
     */
    private final Object [] elements;
    /**
     * количество элементов в стеке
     */
    private int elementsNum;
    /**
     * максимально допустимое количество элементов в стеке
     */
    private static final int MAX_SIZE = 100;

    public Stack() {
        this.elements = new Object[MAX_SIZE];
        this.elementsNum = 0;
    }

    /**
     * возвращает копию массива стека
     * @return копия массива стека
     */
    public Object[] toArray() {
        Object [] objects = elements;
        return objects;
    }

    public int size() {
        return elementsNum;
    }

    /**
     * вставляет элемент в стек сверху
     *
     * @param element новый элемент
     * @throws ArrayIndexOutOfBoundsException если элемент вставляется в заполненный стек
     */
    public void push(Object element) {
        try {
            for (int i = elementsNum; i > 0; i--) {
                this.elements[i] = this.elements[i-1];
            }
            this.elements[0] = element;
            this.elementsNum++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("the stack is already full!");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * возвращает верхний элемент и удаляет его из стека
     *
     * @return верхний элемент перед удалением его из стека
     * @throws ArrayIndexOutOfBoundsException если элемент удаляется из пустого стека
     */
    public Object pop() {
        try {
            Object object = this.top();
            for (int i = 0; i < elementsNum - 1; i++) {
                this.elements[i] = this.elements[i+1];
            }
            this.elements[elementsNum - 1] = null;
            this.elementsNum--;
            return object;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * проверяет, пуст ли стек
     *
     * @return true - стек пуст, false - стек не пуст
     */
    public boolean isEmpty() {
        if(elementsNum > 0) {
            return false;
        }
        return true;
    }

    /**
     * возвращает верхний элемент стека
     *
     * @return верхний элемент
     */
    public Object top() {
        if(elementsNum > 0) {
            return this.elements[0];
        }
        System.out.println("the stack is empty! nothing to return");
        return null;
    }

    /**
     * выводит элементы стека и их количество
     *
     * @return строка с информацией о стеке
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Object object : this.elements) {
            if(object != null) {
                result.append(object);
                result.append(" ");
            }
        }
        return "stack\nelements = " + result.toString().trim() +
                ",\nelementsNum = " + elementsNum +
                "\n";
    }
}
