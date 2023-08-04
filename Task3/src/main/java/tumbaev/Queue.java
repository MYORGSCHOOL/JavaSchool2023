package tumbaev;

/**
 * Queue backed by array of {@link Object}s.
 * <br>
 * <br>
 * Queue is a data structure that follows the First In First Out (FIFO) principle.
 * This means the most lately added (or "enqueued") element is the first one to be removed (or "dequeued") from the queue.
 */
public class Queue {

    /**
     * Queue size will be multiplied by this value when expanding.
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
     * Pointer to the front element of the queue.
     */
    private int front;

    /**
     * Pointer to the rear element of the queue.
     */
    private int rear;

    /**
     * Creates new queue with size = 10.
     */
    public Queue() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates new queue with size = initialSize.
     *
     * @param initialSize size of the queue to create
     * @throws IllegalArgumentException initialSize < 0
     * @throws OutOfMemoryError         initialSize is too large
     */
    public Queue(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Initial queue size must be >= 0");
        }
        reset(new Object[initialSize]);
    }

    /**
     * Adds element to the back of the queue.
     * <br>
     * If queue is full, it will be expanded.
     *
     * @param element element to add
     * @throws OutOfMemoryError queue cannot be expanded
     */
    public void enqueue(Object element) {
        if (isFull()) {
            expand();
        }
        //move front forward if this is a first element
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % data.length;
        data[rear] = element;
    }

    /**
     * Returns and removes element from the front of the queue.
     *
     * @return front element or null if queue is empty
     * @throws EmptyQueueException queue is empty
     */
    public Object dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        Object element = data[front];
        data[front] = null;

        //if this was the last element - reset the queue
        if (front == rear) {
            reset(data);
        } else {
            front = (front + 1) % data.length;
        }
        return element;
    }

    /**
     * Returns top element of the queue, without removing it.
     *
     * @return top element or null if queue is empty
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    /**
     * Check if queue is empty.
     *
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return front == -1;
    }

    /**
     * Checks if {@link #data} is full.
     *
     * @return true if {@link #data} is full, false otherwise
     */
    private boolean isFull() {
        return data.length == 0 ||
                front == rear + 1 ||
                front == 0 && rear == data.length - 1;
    }

    /**
     * Makes the queue {@link #EXPAND_RATE} times bigger.
     * <br>
     * If size is zero, it will be set to {@link #DEFAULT_SIZE}.
     *
     * @throws OutOfMemoryError queue cannot be expanded
     */
    private void expand() {
        int newSize = data.length == 0 ? DEFAULT_SIZE : data.length * EXPAND_RATE;
        Object[] newData = new Object[newSize];
        if (front <= 0) {
            System.arraycopy(data, 0, newData, 0, data.length);
        } else {
            //if front is not at the beginning
            //copy elements from front to end
            System.arraycopy(data, front, newData, 0, data.length - front);
            //copy elements from beginning to rear
            System.arraycopy(data, 0, newData, data.length - front, rear + 1);
        }
        data = newData;
        front = 0;
        rear = data.length - 1;
    }

    /**
     * Sets {@link #front} and {@link #rear} to -1, sets {@link #data} to the given array.
     *
     * @param data array to set
     */
    private void reset(Object[] data) {
        this.data = data;
        this.front = -1;
        this.rear = -1;
    }
}
