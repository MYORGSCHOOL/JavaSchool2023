package koroliov;

public class RollBackStringBuilder {
    private Steck builder = new Steck();
    
    RollBackStringBuilder() {
        this.builder.Push("");
    }

    RollBackStringBuilder(String str) {
        this.builder.Push(str);
    }

    /**
     * The method adds a new row to an existing row.
     * 
     * The method takes a string as input and if there is already a string in the builder, 
     * adds it to the existing string, otherwise it simply creates it.
     * @param str - String.
     */
    public void append(String str) {
        this.builder.Push(this.builder.Top() + str);
    }

    /**
     * The method adds a substring to the builder by index.
     * 
     * The method receives a string and an insertion index into an existing row. 
     * The builder adds a substring to the main string and builds it.
     * @param offset - Integer, index.
     * @param str - String substring.
     */
    public void insert(int offset, String str) {
        String[] temp = ((String) this.builder.Top()).split("");
        String result = "";
        if (offset > 0) {
            temp[offset - 1] += str;
        } else result += str;

        for (String i : temp) result += i;
        System.out.println(result);
        this.builder.Push(result);
    }
    
    /**
     * The method reverses the line in the builder.
     * 
     * The previously added row to the builder is flipped and added as the current builder row.
     */
    public void reverse() {
        char[] temp =  ((String) this.builder.Top()).toCharArray();
        char[] str = new char[temp.length];
        for (int i = temp.length - 1; i >= 0; i--) str[temp.length - 1 - i] = temp[i];
        String result = String.valueOf(str);
        System.out.println(result);
        this.builder.Push(result);
    }

    /**
     * The method adds a substring within a certain range of an existing string.
     * 
     * The method takes as input the indices of the range of an existing string and, 
     * after removing the characters in this gap, adds a substring.
     * @param start - Integer, index start.
     * @param end - Integer, end.
     * @param str - String, substring.
     */
    public void replace(int start, int end, String str) {
        String[] temp = ((String) this.builder.Top()).split("");
        for (int i = start; i < end; i++) temp[i] = "";
        temp[end - 1] += str;
        String result = "";
        for (String i : temp) result += i;
        System.out.println(result);
        this.builder.Push(result);
    }

    /**
     * Roll back to the previous change.
     * 
     * The method reverts the current changes to the string and reverts it to the previous version.
     */
    public void rollback() {
        this.builder.Pop();
    }

    /**
     * The method constructs the resulting string and returns it.
     * @return Returns the constructed String.
     */
    public String toString() {
        return (String) this.builder.Top();
    }
}
