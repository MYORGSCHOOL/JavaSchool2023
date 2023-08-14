package sevostyanov;

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        Vector vectorOne = new Vector(1, 5, 8);
        Vector vectorTwo = new Vector(7, 5, 3);
        System.out.println("Длина вектора = " + vectorOne.vectorLength());
        System.out.println("Скалярное произведение = " + vectorOne.vectorScalar(vectorTwo));
        System.out.println("Cкалярное произведение между двумя векторами = " + vectorOne.vectorMultiplication(vectorTwo));
        System.out.println("Угол между векторами = " + vectorOne.vectorCosineAngle(vectorTwo));
        System.out.println("Cумма векторов = " + vectorOne.vectorPlus(vectorTwo));
        System.out.println("Разность векторов = " + vectorOne.vectorMinus(vectorTwo));
        System.out.println("Сгенерировано случайных векторов = " + Arrays.toString(Vector.getRandomVector(3)));
    }
}
