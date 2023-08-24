package grankin;

public class Task4 {

    public static void main(String[] args) {
        RollbackStringBuilder builder = new RollbackStringBuilder();

        builder.append("START ");

        builder.insert(5, " INSERT").append("New string")
                .reverse().replace(0, 6, ")ELIMS")
                .deleteCharAt(3);
        System.out.println(builder);

        builder.rollback().rollback();
        System.out.println(builder);

        builder.rollback().rollback().rollback();
        System.out.println("\n" + builder);
    }
}
