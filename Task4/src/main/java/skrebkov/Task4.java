package skrebkov;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("Ёжик ").append("в тумане!").reverse();
        System.out.println(rollbackStringBuilder);
    }
}
