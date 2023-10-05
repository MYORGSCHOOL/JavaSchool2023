package koroliov;

import java.util.LinkedList;


/**
 * A class that implements the stack.
 * @author Nikita Koroliov
 * @version 1.1
 */
public class Stack {
    private final LinkedList<Object> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    public Stack(String str) {
        this.stack = new LinkedList<>();
        this.stack.addFirst(str);
    }

    /**
     * The method adds an element to the stack.
     * 
     * The method adds the passed element to the beginning of the stack.
     * @param obj An add-to-stack element.
     */

    public void push(Object obj) {
        this.stack.addFirst(obj);
    }

    /**
     * Returns the deleted stack start object.
     * 
     * The method returns the removed element of the beginning of the stack.
     * @return Object Deleted Item.
     */

    public Object pop() {
        return this.stack.removeFirst();
    }

    /**
     * Returns true if the stack is empty, 
     * otherwise, the method will return false.
     * 
     * The method checks the size of the stack.
     * @return boolean The result of the stack check.
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Returns the top item without removing it from Stack.
     * 
     * The method returns the initial element from the stack without deleting it.
     * @return Object Item.
     */

    public Object top() {
        return this.stack.getFirst();
    }

    /**
     * Returns all elements of the stack in
     * an identical order in an array.
     * 
     * The method generates an array of stack
     * objects in a similar order and returns it.
     * @return Object[] An array of stack objects.
     */
    public Object[] getAll() {
        return this.stack.toArray();
    }
}
