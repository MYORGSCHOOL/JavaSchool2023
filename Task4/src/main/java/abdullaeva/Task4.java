package abdullaeva;

public class Task4 {
    public static void main(String[] args) {
        RollbackStringBuilder rollbackStringBuilder = new RollbackStringBuilder();
        rollbackStringBuilder.append("osmanthus")
                .insert(0, "delicious ")
                .insert(19, " wine ")
                .append(" is tasty")
                .replace(25, 28, "is not")
                .rollback()
                .rollback();
        System.out.println(rollbackStringBuilder);
    }

}
