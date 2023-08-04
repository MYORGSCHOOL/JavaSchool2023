package piryazev;

import java.util.Arrays;
import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        final Random random = new Random();
        int n = random.nextInt(10);

        Vector vector1 = new Vector(3, 2, 3);
        Vector vector2 = new Vector(1, 5, 8);

        System.out.println("Длина вектора:          " + vector1.vectorLength());
        System.out.println("Скалярное произведение: " + vector1.scalarProduct(vector2));
        System.out.println("Векторное произвдение:  " + vector1.crossProduct(vector2));
        System.out.println("Угол между векторами:   " + vector1.vectorAngle(vector2));
        System.out.println("Сумма векторoв:         " + vector1.vectorSum(vector2));
        System.out.println("Разница векторoв:       " + vector1.vectorDifference(vector2));
        System.out.println("Массив вектoров:        " + Arrays.toString(Vector.vectorsArray(n)));
    }
}
