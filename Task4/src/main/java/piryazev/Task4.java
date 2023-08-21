package piryazev;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.rollback();
        System.out.println(builder);
    }
}
