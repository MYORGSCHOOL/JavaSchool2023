package tsimmer;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Hello world").insert(11, "!").replace(6, 12, "123");
        System.out.println(builder);
        builder.reverse();
        System.out.println(builder);
        builder.rollback().rollback();
        System.out.println(builder);

    }
}
