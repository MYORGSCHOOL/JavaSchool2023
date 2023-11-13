package korolev.Exceptions;

/**
 * The error of incorrect prioritization in {@code TestMethod}
 */
public class PriorityAllocationError extends RuntimeException {
    public PriorityAllocationError(String massage) {
        super(massage);
    }
    
}
