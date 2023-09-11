package tumbaev;

import tumbaev.dto.CheckBracketsRequest;
import tumbaev.dto.CheckBracketsResponse;

import java.util.Map;
import java.util.Stack;

public class CheckBracketsService {

    private final Map<Character, Character> bracketsMap = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );
    private final Stack<Character> brackets = new Stack<>();

    /**
     * Checks if provided text have balanced brackets and no brackets pair has empty content (e.g., '()' - not allowed).
     *
     * @param request - request object with text to check
     * @return response object with result of checking
     */
    public CheckBracketsResponse checkBrackets(CheckBracketsRequest request) {
        return new CheckBracketsResponse(bracketsBalancedAndNotEmpty(request.getText()));
    }

    /**
     * Checks if provided text have balanced brackets and no brackets pair has empty content (e.g., '()' - not allowed).
     *
     * @param text text to check
     * @return true - if all brackets are balanced and none of them have empty content, false otherwise.
     */
    private boolean bracketsBalancedAndNotEmpty(String text) {
        char[] charArray = text.toCharArray();

        int prevBracketInd = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (bracketsMap.containsValue(c)) {
                brackets.push(c);
                prevBracketInd = i;
            } else if (bracketsMap.containsKey(c)) {
                if (brackets.isEmpty() ||
                        !brackets.pop().equals(bracketsMap.get(c)) ||
                        prevBracketInd == i - 1) {
                    brackets.clear();
                    return false;
                }
            }
        }

        boolean isBalanced = brackets.isEmpty();
        brackets.clear();
        return isBalanced;
    }
}
