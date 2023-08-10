package yanko;

public class Task4 {
    public static void main(String[] args) {
        CustomStringBuilder builder = new CustomStringBuilder();
        builder.append("abcd");
        builder.replace(0, 9, "0");
        System.out.println(builder);
        builder.rollback();
        System.out.println(builder);
    }
}
