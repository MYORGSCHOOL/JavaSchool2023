package tumbaev;

/**
 * Stack backed by array of {@link Object}s.
 * <br>
 * <br>
 * Stack is a data structure that follows the Last In First Out (LIFO) principle.
 * This means the most recently added (or "pushed") element is the first one to be removed (or "popped") from the stack.
 */
public class Stack {

    /**
     * Stack size will be multiplied by this value when expanding.
     */
    private static final int EXPAND_RATE = 2;

    /**
     * If initial size is not specified, it will be set to this value.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * An array where elements of the stack will be stored.
     */
    private Object[] data;

    /**
     * Pointer to the top element of the stack.
     */
    private int top;

    /**
     * Creates new stack with size = 10.
     */
    public Stack() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates new stack with size = initialSize.
     *
     * @param initialSize size of the stack to create
     * @throws IllegalArgumentException initialSize < 0
     * @throws OutOfMemoryError         initialSize is too large
     */
    public Stack(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Initial stack size must be >= 0");
        }

        this.data = new Object[initialSize];
        this.top = -1;
    }

    /**
     * Adds element to the top of the stack.
     * <br>
     * If stack is full, it will be expanded.
     *
     * @param element element to add
     * @throws OutOfMemoryError stack cannot be expanded
     */
    public void push(Object element) {
        if (top == data.length - 1) {
            expand();
        }
        data[++top] = element;
    }

    /**
     * Returns and removes top element of the stack.
     *
     * @return top element
     * @throws EmptyStackException stack is empty
     */
    public Object pop() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        Object element = data[top];
        data[top] = null;
        top--;
        return element;
    }

    /**
     * Returns top element of the stack, without removing it.
     *
     * @return top element or null if stack is empty
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    /**
     * Check if stack is empty.
     *
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Makes the stack {@link #EXPAND_RATE} times bigger.
     * <br>
     * If size is zero, it will be set to {@link #DEFAULT_SIZE}.
     *
     * @throws OutOfMemoryError stack cannot be expanded
     */
    private void expand() {
        int newSize = data.length == 0 ? DEFAULT_SIZE : data.length * EXPAND_RATE;
        Object[] newData = new Object[newSize];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
