package koroliov;

import java.util.LinkedList;

public class Queue {
    private LinkedList<Object> queue = new LinkedList<>();

    /**
     * Returns -1 if the insertion failed and 1 if the insertion was successful.
     * The argument must specify the object that you want to add to the queue.
     * 
     * This method inserts an element at the end of the queue, the argument sent will be
     * converted to {Object} and added. 
     * @param x - Any object.
     * @return -1/1 (int) - The result of adding an object to the queue.
     */
    int Enqueue(Object x) {
        try {
            this.queue.addLast(x);
        } catch (Exception e) {
            return -1;
        }

        return 1;
    }

    /**
     * Returns -1 if the deletion failed and 1 if the deletion was successful.
     * 
     * The method deletes the last element of the queue, otherwise it returns -1.
     * @return  -1/1 (int) - Returns the result of deleting an element.
     */
    int Dequeue() {
        try {
            this.queue.removeFirst();
        } catch (Exception e) {
            return -1;
        }

        return 1;
    }

    /**
     * Returns true, if the outline is empty, and set to false
     * The method determines the current queue size.
     * @return Boolean
     */
    boolean isEmpty() {
        if (this.queue.size() == 0) {
            return true;
        } else return false;
    }

    // возвращает первый элемент очереди
    /**
     * Returns the first item in the queue.
     * The method returns the first element of the current queue without deleting it.
     * @return Object - The first element.
     */
    Object Top() {
        return this.queue.peekFirst();
    }

    /**
     * Returns all queue items as an array.
     * The method copies the current queue and returns it as an array.
     * @return Object[] - Queue array.
     */
    Object[] getAll() {
        return this.queue.toArray();
    }
}
