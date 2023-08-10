package tumbaev;

/**
 * Allows to perform operations that modify a string without creating a new string object.
 * <p>
 * Also allows rollback of those operations.
 */
public class RollbackStringBuilder {

    /**
     * Stack where {@link RollbackAction}s are stored.
     */
    private final Stack stack = new Stack();

    /**
     * Original StringBuilder to which all string-modifying operations are delegated.
     */
    private final StringBuilder sb = new StringBuilder();

    /**
     * Inserts the string into this character sequence.
     * <p>
     * The characters of the {@code String} argument are inserted, in
     * order, into this sequence at the indicated offset, moving up any
     * characters originally above that position and increasing the length
     * of this sequence by the length of the argument. If
     * {@code str} is {@code null}, then the four characters
     * {@code "null"} are inserted into this sequence.
     * <p>
     * The character at index <i>k</i> in the new character sequence is
     * equal to:
     * <ul>
     * <li>the character at index <i>k</i> in the old character sequence, if
     * <i>k</i> is less than {@code offset}
     * <li>the character at index <i>k</i>{@code -offset} in the
     * argument {@code str}, if <i>k</i> is not less than
     * {@code offset} but is less than {@code offset+str.length()}
     * <li>the character at index <i>k</i>{@code -str.length()} in the
     * old character sequence, if <i>k</i> is not less than
     * {@code offset+str.length()}
     * </ul><p>
     * The {@code offset} argument must be greater than or equal to
     * {@code 0}, and less than or equal to the length of this sequence.
     *
     * @param offset the offset.
     * @param str    a string.
     * @return a reference to this object.
     * @throws StringIndexOutOfBoundsException if the offset is invalid.
     */
    public RollbackStringBuilder insert(int offset, String str) {
        sb.insert(offset, str);
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                sb.delete(offset, offset + str.length());
            }
        };
        pushRollbackAction(action);
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of
     * the sequence. If there are any surrogate pairs included in the
     * sequence, these are treated as single characters for the
     * reverse operation. Thus, the order of the high-low surrogates
     * is never reversed.
     * <p>
     * Let <i>n</i> be the character length of this character sequence
     * (not the length in {@code char} values) just prior to
     * execution of the {@code reverse} method. Then the
     * character at index <i>k</i> in the new character sequence is
     * equal to the character at index <i>n-k-1</i> in the old
     * character sequence.
     *
     * <p>Note that the reverse operation may result in producing
     * surrogate pairs that were unpaired low-surrogates and
     * high-surrogates before the operation. For example, reversing
     * "\u005CuDC00\u005CuD800" produces "\u005CuD800\u005CuDC00" which is
     * a valid surrogate pair.
     *
     * @return a reference to this object.
     */
    public RollbackStringBuilder reverse() {
        sb.reverse();
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                sb.reverse();
            }
        };
        pushRollbackAction(action);
        return this;
    }

    /**
     * Appends the specified string to this character sequence.
     * <p>
     * The characters of the {@code String} argument are appended, in
     * order, increasing the length of this sequence by the length of the
     * argument. If {@code str} is {@code null}, then the four
     * characters {@code "null"} are appended.
     * <p>
     * Let <i>n</i> be the length of this character sequence just prior to
     * execution of the {@code append} method. Then the character at
     * index <i>k</i> in the new character sequence is equal to the character
     * at index <i>k</i> in the old character sequence, if <i>k</i> is less
     * than <i>n</i>; otherwise, it is equal to the character at index
     * <i>k-n</i> in the argument {@code str}.
     *
     * @param str a string.
     * @return a reference to this object.
     */
    public RollbackStringBuilder append(String str) {
        sb.append(str);
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                sb.delete(sb.length() - str.length(), sb.length());
            }
        };
        pushRollbackAction(action);
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence
     * with characters in the specified {@code String}. The substring
     * begins at the specified {@code start} and extends to the character
     * at index {@code end - 1} or to the end of the
     * sequence if no such character exists. First the
     * characters in the substring are removed and then the specified
     * {@code String} is inserted at {@code start}. (This
     * sequence will be lengthened to accommodate the
     * specified String if necessary.)
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @param str   String that will replace previous contents.
     * @return This object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     *                                         is negative, greater than {@code length()}, or
     *                                         greater than {@code end}.
     */
    public RollbackStringBuilder replace(int start, int end, String str) {
        final String prev = sb.substring(start, end);
        sb.replace(start, end, str);
        RollbackAction action = new RollbackAction() {
            @Override
            public void rollback() {
                sb.replace(start, end, prev);
            }
        };
        pushRollbackAction(action);
        return this;
    }

    /**
     * Undo the latest operation (except rollback itself).
     *
     * @return This object.
     * @throws RollbackException if there is no operations to rollback.
     */
    public RollbackStringBuilder rollback() {
        popRollbackAction().rollback();
        return this;
    }

    /**
     * Returns a string representing the data in this sequence.
     * A new {@code String} object is allocated and initialized to
     * contain the character sequence currently represented by this
     * object. This {@code String} is then returned. Subsequent
     * changes to this sequence do not affect the contents of the
     * {@code String}.
     *
     * @return a string representation of this sequence of characters.
     */
    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * Pushes {@link RollbackAction} to the stack. Should be called after every string-modifying operation.
     *
     * @param action action to save in {@link #stack}.
     * @throws OutOfMemoryError cannot add more actions
     */
    private void pushRollbackAction(RollbackAction action) {
        stack.push(action);
    }

    /**
     * Returns the latest rollback action.
     *
     * @return the latest rollback action.
     * @throws RollbackException if there is no actions to rollback
     *                           or retrieved object is not of type {@link RollbackAction}.
     */
    private RollbackAction popRollbackAction() {
        if (stack.isEmpty()) {
            throw new RollbackException("There is no operations to rollback");
        }
        Object obj = stack.pop();
        if (obj instanceof RollbackAction) {
            return (RollbackAction) obj;
        } else {
            throw new RollbackException("Only rollback actions are allowed in stack");
        }
    }

}
