package dushkina;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder builder = new RollbackStringBuilder();
        builder.append("Held").append("1");//"Held1"
        System.out.println(builder);
        builder.rollback();//"Held"
        //"Hello, World" -> "Hello,!!! World"
        builder.insert(2, "llo, Wor").insert(6, "!!!");
        System.out.println(builder);
        builder.rollback().rollback();//"Held"
        System.out.println(builder);
        builder.reverse();
        System.out.println(builder);
        builder.rollback();
        System.out.println(builder);
        builder.replace(2, 4, "llo!");
        System.out.println(builder);
        builder.replace(5, 6, ", World.");
        System.out.println(builder);
        builder.rollback().rollback();
        System.out.println(builder);
        builder.rollback().rollback();
    }

}
