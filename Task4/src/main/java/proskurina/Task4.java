package proskurina;

public class Task4 {
    public static void main(String[] args) {
        var rb = new RollbackStringBuilder();
        System.out.println(rb.append("123")
                             .reverse()
                             .insert(0,"4")
                             .replace(4,4,"0"));
    }
}
