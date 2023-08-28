package sevostyanov;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
            rollbackStringBuilder.append("lllllllllllllll");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.insert(7,"qwerty");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.reverse();
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.replace(8,14,"123456");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println(rollbackStringBuilder);
    }
}
