package pozdnyakova;

public class Task2 {
    public static void main(String[] args) {
        Vector v1 = new Vector(10, 6, 4);
        Vector v2 = new Vector(5, 1, 12);
        System.out.printf("Вектор v1: ");
        v1.output();
        System.out.printf("Вектор v2: ");
        v2.output();
        System.out.println("Длина v1: " + v1.length());
        System.out.println("Длина v2: " + v2.length());
        System.out.println("Скалярное произведение v1 и v2: " + v1.scalarProduct(v2));
        System.out.printf("Векторное произведение v1 и v2: ");
        v1.vectorProduct(v2).output();
        System.out.println("Косинус угла между v1 и v2: " + v1.cos(v2));
        System.out.printf("Сумма v1 и v2: ");
        v1.sum(v2).output();
        System.out.printf("Разность v1 и v2: ");
        v1.sub(v2).output();
        int N = 10;
        Vector[] vectors = Vector.array(N);
        System.out.println("Случайные 10 векторов");
        for (int i = 0; i != N; i++) vectors[i].output();
    }
}
