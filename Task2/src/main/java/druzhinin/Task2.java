package druzhinin;

public class Task2 {
    public static void main(String[] args) {
        Vector3D vector1 = new Vector3D(1.0d, 2.0d, 3.0d);
        Vector3D vector2 = new Vector3D(4.0d, 5.0d, 6.0d);

        System.out.println("Длина вектора 1: " + vector1.getLength());
        System.out.println("Длина вектора 2: " + vector2.getLength());
        System.out.println("Скалярное произведение vector1 и vector2: "
                + vector1.getScalarProduct(vector2));
        System.out.println("Векторное произведение vector1 и vector2: "
                + vector1.getVectorProduct(vector2));
        System.out.println("Косинус угла между vector1 и vector2: "
                + vector1.getCosOfAngleBetweenVectors(vector2));
        System.out.println("Результат сложения vector1 и vector2: "
                + vector1.addVector(vector2));
        System.out.println("Результат вычитания из vector1 вектора vector2: "
                + vector1.subtractVector(vector2));

        Vector3D[] randomVectors = Vector3D.getNRandomVectors(5);
        for (Vector3D vector: randomVectors) {
            System.out.println(vector);
        }
    }
}
