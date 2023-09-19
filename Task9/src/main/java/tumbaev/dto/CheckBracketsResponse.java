package tumbaev.dto;

public class CheckBracketsResponse {
    private boolean isCorrect;

    public CheckBracketsResponse() {
    }

    public CheckBracketsResponse(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
