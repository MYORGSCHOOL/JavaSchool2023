package koroliov;

import java.util.LinkedList;

/**
 * A class that implements the queue.
 * @author Nikita Koroliov
 * @version 1.1
 */
public class Queue {
    private final LinkedList<Object> queue;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    public Queue(String str) {
        this.queue = new LinkedList<>();
        this.queue.addFirst(str);
    }

    /**
     * The queue method inserts 
     * the submitted item at the end of the queue.
     * @param obj The object to be added to the queue will be added as an object
     */

    public void enqueue(Object obj) {
        this.queue.addFirst(obj);
    }

    /**
     * After the call, the first element
     *  of the queue will be deleted.
     */

    public void dequeue() {
        this.queue.removeLast();
    }

    /**
     * Returns true if the queue is empty, 
     * otherwise, the method will return false.
     * 
     * The method checks the size of the queue.
     * @return boolean The result of the queue check.
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Returns the first item in the queue, 
     * and then removes it from the queue.
     * 
     * The method returns the first deleted item of the essay.
     * @return object The first deleted item in the queue
     */
  
    public Object top() {
        return this.queue.getLast();
    }

    /**
     * Returns all elements of the queue in
     * an identical order in an array.
     * 
     * The method generates an array of queue
     * objects in a similar order and returns it.
     * @return Object[] An array of queue objects.
     */
    public Object[] getAll() {
        return this.queue.toArray();
    }
}
