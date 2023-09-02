package koroliov;

import java.util.LinkedList;

public class Steck {
    private LinkedList<Object> steck = new LinkedList<>();

    /**
     * Returns -1/1 int, the result of adding an element to the stack.
     * As a parameter, the method takes an object and adds it to the end of the queue.
     * 
     * The method accepts the object, then adds it to the end of the stack, otherwise it sends -1.
     * @param x - Object
     * @return int - The result of executing the method (-1/1).
     */
    int Push(Object x) {
        try {
            this.steck.addFirst(x);
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    /**
     * Returns the top element after removing the element from the stack.
     * 
     * The method selects the first element of the stack, 
     * deletes it, and returns the element received after deletion.
     * @return Object - The first element of the stack, deleted.
     */
    Object Pop() {
        Object temp = null;
        try {
            temp = this.steck.removeFirst();
        } catch (Exception e) {
            return null;
        }
        return temp;
    }

    /**
     * Returns true, if the outline is empty and set to false.
     * The method determines the current stack size.
     * @return Boolean
     */
    Boolean isEmpty() {
        if (this.steck.size() == 0) {
            return true;
        } else return false;
    }

    /**
     * Returns the top element of the stack without deleting it.
     * The method selects the last element of the stack and returns it without removing it from the stack,
     * If the stack is empty, null will be returned.
     * @return Object - The top element of the stack.
     */
    Object Top() {
        return this.steck.getFirst();
    }

    // возвращает обьекты в стеке (без изменения самого стека)
    /**
     * Returns all stack items as an array.
     * The method copies the current stack and returns it as an array.
     * @return Object[] - Stack array.
     */
    Object[] getAll() {
        return this.steck.toArray();
    }
}
