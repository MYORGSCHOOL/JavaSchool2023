package karmanchikova;

public class Task4 {
    public static void main(String[] args) {
        var rb = new RollbackStringBuilder();
        System.out.println(rb.append("olleh").reverse()
                .replace(2, 3, " word")
                .insert(9, "l").rollback());
    }
}