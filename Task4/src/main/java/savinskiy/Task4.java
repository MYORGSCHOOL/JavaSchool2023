package savinskiy;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder str = new RollbackStringBuilder();

        str.append("My ");
        System.out.println(str);
        str.append("name ");
        System.out.println(str);
        str.append("is ");
        System.out.println(str);
        str.append("Khan!");
        System.out.println(str);

        str.insert(11, "Kanye West ");
        System.out.println(str);

        str.rollback();
        System.out.println(str);

        str.rollback();
        System.out.println(str);

        str.rollback();
        System.out.println(str);

        str.rollback();
        System.out.println(str);

        str.rollback();
        System.out.println(str);
    }
}
