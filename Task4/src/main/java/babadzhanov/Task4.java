package babadzhanov;

public class Task4 {

    public static void main(String[] args) {

        RollbackStringBuilder builder = new RollbackStringBuilder();

        builder.append("evorppa em evig");
        System.out.println(builder);

        builder.insert(0, "): esaelp ");
        System.out.println(builder);

        builder.reverse();
        System.out.println(builder);

        builder.replace(16, 16, "for task 4, ");
        System.out.println(builder);

    }

}
