package koroliov;

import java.util.LinkedList;

public class Queue {
    private LinkedList<Object> queue = new LinkedList<>();

    /**
     * The queue method inserts 
     * the submitted item at the end of the queue.
     * @param obj The object to be added to the queue will be added as an object
     */
    public void Enqueue(Object obj) {
        this.queue.addLast(obj);;
    }

    /**
     * After the call, the first element
     *  of the queue will be deleted.
     */
    public void Dequeue() {
        this.queue.removeFirst();
    }

    /**
     * Returns true if the queue is empty, 
     * otherwise, the method will return false.
     * 
     * The method checks the size of the queue.
     * @return boolean The result of the queue check.
     */
    public boolean isEmpty() {
        return this.queue.size() == 0 ? true : false;
    }

    /**
     * Returns the first item in the queue, 
     * and then removes it from the queue.
     * 
     * The method returns the first deleted item of the essay.
     * @return object The first deleted item in the queue
     */
    public Object Top() {
        return this.queue.getFirst();
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
