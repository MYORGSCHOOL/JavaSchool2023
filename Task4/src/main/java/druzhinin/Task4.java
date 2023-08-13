package druzhinin;

/**
 * Класс для использования RollbackStringBuilder. *
 */
public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder("Hello");
        System.out.println(rollbackStringBuilder);
        rollbackStringBuilder.append("MyFriend");
        System.out.println("\nДобавление в конец строки через append:\n" + rollbackStringBuilder);
        rollbackStringBuilder.insert(7,"Dear");
        System.out.println("\nДобавление в середину строки через insert:\n" + rollbackStringBuilder);
        rollbackStringBuilder.replace(0, 100, "HelloWorld");
        System.out.println("\nПолная замена строки через replace:\n" + rollbackStringBuilder);
        rollbackStringBuilder.reverse();
        System.out.println("\nИнвертирование строки через replace:\n" + rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println("\nОткат инвертирования строки через rollback:\n" + rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println("\nОткат замены строки через rollback:\n" + rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println("\nОткат добавления в середину строки через rollback:\n" + rollbackStringBuilder);
        rollbackStringBuilder.rollback();
        System.out.println("\nОткат добавления в конец строки через rollback:\n" + rollbackStringBuilder);
    }
}
