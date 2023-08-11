package grossu;

/**
 * Класс для работы с классом RollbackStringBuilder
 */
public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();

        rollbackStringBuilder.append("55").replace(1, 2, "4");
        System.out.println(rollbackStringBuilder);
        System.out.println(rollbackStringBuilder.rollback().rollback());
        rollbackStringBuilder.append("555").insert(3, "66");
        System.out.println(rollbackStringBuilder);
        System.out.println(rollbackStringBuilder.rollback());
    }
}
