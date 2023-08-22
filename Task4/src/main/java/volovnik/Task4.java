package volovnik;

public class Task4 {

    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder
                .append("str")
                .append("text")
                .insert(3, "555")
                .replace(0, 2, "11")
                .reverse();
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        rollbackStringBuilder.rollback();
        rollbackStringBuilder.rollback();
        rollbackStringBuilder.rollback();
        System.out.println(rollbackStringBuilder);
    }
}
