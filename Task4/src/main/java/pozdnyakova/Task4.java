package pozdnyakova;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder= new RollbackStringBuilder();
        for (int i=0; i!=12; i++){
            rollbackStringBuilder.append("a");
        }
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.insert(5,"qwerty");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.reverse();
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.replace(0,2,"123");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println(rollbackStringBuilder);
    }
}
