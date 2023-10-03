package koroliov;

/**
 * The interface implements working with data.
 */
public interface Dictionary<G> {

    /**
     * Adding data to a specific data storage method.
     * @param s String A string to add.
     */
    void insert(String s);

    /**
     * The method returns a random element from the data storage structure.
     * @return String A random structure element.
     */
    String getRandom();

    /**
     * Deletes the transferred item from the specified data storage structure.
     * @param s String The outline element that you want to remove.
     */
    void delete(String s);

    /**
     * The method checks the data structure for the presence of a given element in it.
     * 
     * Returns boolean, the result of checking the structure for the presence of an element.
     * @param s String String to check.
     * @return Bollean Result of the check.
     */
    boolean consist(String s);
}
