package babadzhanov;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {

        Vector first = new Vector(1.0, 3.0, 5.0);
        Vector second = new Vector(2.0, 4.0, 6.0);

        System.out.println("Длина вектора: " + second.getLength());

        System.out.println("Скалярное произведение 2-ух векторов: " + first.getScalarMultiply(second));

        System.out.println("Векторное произведение 2-ух векторов: " + first.getVectorMultiply(second));

        System.out.println("Угол между векторами равен: " + first.getAngle(second));

        System.out.println("Сумма векторов: " + Vector.printVectorCalculations(first, second, "sum"));

        System.out.println("Разность векторов: " + Vector.printVectorCalculations(first, second, "dif"));

        System.out.println("Массив случайных векторов: " + Arrays.toString(Vector.getRandomVectors(5)));


    }
}
