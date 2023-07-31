package skrebkov;

public class Task2 {
    public static void main(String[] args) {

        Vector[] vectors = Vector.generateVectors(5);
        for (int i = 1; i < vectors.length; i++) {
            System.out.println(vectors[i].vectorInfoForConsole());
            System.out.println("Произведение " + (vectors[i].vectorProduct(vectors[i-1])).vectorInfoForConsole());
            System.out.println("Сумма " + (vectors[i].sumVectors(vectors[i-1])).vectorInfoForConsole());
            System.out.println("Разность " + (vectors[i].differenceVectors(vectors[i-1])).vectorInfoForConsole());
        }
    }
}
