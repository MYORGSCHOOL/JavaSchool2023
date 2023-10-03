package koroliov;

import java.util.LinkedList;

/**
 * The extended StringBuiler class.
 * @author Nikita Koroliov
 * @version 1.1
 */
public class RollbackStringBuilder {
    private Stack stack = new Stack();

    /**
     * Empty constructor, for further work, 
     * you need to add a line to it.
     */
    RollbackStringBuilder() {}

    /**
     * A constructor with a specified string.
     */
    RollbackStringBuilder(String str) {
        this.stack.push(str);
    }

    /**
     * Inserts characters into this buffer string (RollBackStringBuilder). 
     * The substring is added before the specified index.
     * 
     * @param offset Index A place to add a substring.
     * @param str Substring Will be added before the index.
     */
    public void insert(int offset, String str) {
        LinkedList<String> list = new LinkedList<>();

        for (String i : ((String) this.stack.top()).split("")) {
            list.add(i);
        }

        list.add(offset, str);
        String result = "";

        for (String i : list) result += i;

        this.stack.push(result);
    }

    /**
     * The method flips the existing string.
     */
    public void reverse() {
        String result = "";
        char[] arr = ((String) this.stack.top()).toCharArray();

        for (int i = arr.length - 1; i >= 0; i--) {
            result += arr[i];
        }

        this.stack.push(result);
    }

    /**
     * Adds the passed string to the specified string.
     * A folded line will be added to the story.
     * 
     * @param str The string is appended to the existing line.
     */
    public void append(String str) {
        this.stack.push(this.stack.top() + str);
    }

    /**
     * Replaces the substring characters of this buffer string (RollBackStringBuilder) 
     * with the specified characters in the string. 
     * In other words, the method allows Java to replace 
     * characters in the string between the specified start and end indexes.
     * 
     * @param start Starting index.
     * @param end Final index.
     * @param str  The substring to insert.
     */
    public void replace(int start, int end, String str) {
        String[] arr = ((String) this.stack.top()).split("");
        String result = "";

        for (int i = start; i < end; i++) {
            arr[i] = "";
            if (i == start) arr[i] = str;
        }

        for (String i : arr) result += i;

        this.stack.push(result);
    }

    /**
     * The method adds a history of changes from
     * the first given line, allows you to roll back 
     * changes all the way back to the beginning. 
     * Rolls back 1 action.
     */
    public void rollback() {
        if (this.stack.isEmpty()) return;
        Object[] arr = this.stack.getAll();

        if (arr.length == 1) {
            this.stack = new Stack();
        } else {
            this.stack.pop();
        }
    }

    /**
     * Returns the string RollBackStringBuilder.
     * 
     * @return String An in-memory string of the RollBackStringBuilder.
     */
    public String toString() {
        String result = (String) this.stack.top();
        return result;
    }
}
