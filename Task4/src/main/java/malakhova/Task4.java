package malakhova;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Hi.")
                .append("How are you?");
        try {
            rollbackStringBuilder.replace(3, 3, "Hello.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Неверно введены параметры");
        }
        rollbackStringBuilder.append("And you?");
        try {
            rollbackStringBuilder.insert(21, "Im fine.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно введены параметры");
        }
        try {
            rollbackStringBuilder.replace(21, 29, "Fine.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Неверно введены параметры");
        }
        rollbackStringBuilder.reverse();
        System.out.println(rollbackStringBuilder);
        try {
            rollbackStringBuilder.rollback();
        } catch (IllegalArgumentException e) {
            System.out.println("Стек пустой, вы ушли в первоначальное состояние строки");
        }
        System.out.println(rollbackStringBuilder);
    }
}
