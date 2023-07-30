package tumbaev;

public class Task2 {
    public static void main(String[] args) {
        Vector v = new Vector(1, 2, 3);
        System.out.println("Создан новый вектор v: " + v);
        System.out.printf("Длина вектора: %.4f%n", v.length());

        Vector v1 = new Vector(1.5, 2.6, 3.7);
        System.out.println("\nСоздан новый вектор v1: " + v1);
        System.out.printf("Скалярное произведение векторов v и v1: %.4f%n", v.scalarProduct(v1));
        System.out.println("Векторное произведение векторов v и v1: " + v.vectorProduct(v1));
        System.out.printf("Косинус угла между v и v1: %.4f%n", v.cos(v1));
        System.out.println("Сумма v и v1: " + v.plus(v1));
        System.out.println("Разность v и v1: " + v.minus(v1));

        System.out.println("\nГенерируем вектора с отрицательным размером массива:");
        try {
            Vector.getRandomVectors(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Полученное исключение: " + e.getMessage());
        }

        System.out.println("\nГенерируем 5 векторов со случайными координатами в диапазоне [0, 1):");
        Vector[] vectors = Vector.getRandomVectors(5);
        for (Vector vector : vectors) {
            System.out.println(vector);
        }
    }
}
