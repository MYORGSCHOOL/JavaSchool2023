package koroliov;

import java.util.LinkedList;

public class Stack {
    private LinkedList<Object> stack = new LinkedList<>();

    /**
     * The method adds an element to the stack.
     * 
     * The method adds the passed element to the beginning of the stack.
     * @param obj An add-to-stack element.
     */
    public void Push(Object obj) {
        this.stack.addFirst(obj);
    }

    /**
     * Returns the deleted stack start object.
     * 
     * The method returns the removed element of the beginning of the stack.
     * @return Object Deleted Item.
     */
    public Object Pop() {
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
    public Object Top() {
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
