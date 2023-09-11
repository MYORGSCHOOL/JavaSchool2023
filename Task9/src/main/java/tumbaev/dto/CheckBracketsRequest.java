package tumbaev.dto;

public class CheckBracketsRequest {
    private String text;

    public CheckBracketsRequest() {
    }

    public CheckBracketsRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
