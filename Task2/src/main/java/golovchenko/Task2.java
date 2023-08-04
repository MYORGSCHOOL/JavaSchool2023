package golovchenko;


import java.util.List;

import static java.lang.String.format;

public class Task2 {

    public static void main(String[] args) {
        Vector vector1 = new Vector(1.5, 2, 3);
        Vector vector2 = new Vector(2, 2.5, 4);
        int n = 2;
        System.out.println("Вектор №1: " + vector1);
        System.out.println("Вектор №2: " + vector2);
        System.out.println("Длинна вектора №1: " + format("%.2f", vector1.length()));
        System.out.println("Скалярное произведение векторов №1 и №2: " + format("%.2f", vector1.scalarProduct(vector2)));
        System.out.println("Векторное произведение векторов №1 и №2: " + vector1.vectorProduct(vector2));
        System.out.println("Угол (или косинус угла) между векторами №1 и №2: " + format("%.2f", vector1.cosAngle(vector2)));
        System.out.println("Сумма векторов №1 и №2: " + vector1.sum(vector2));
        System.out.println("Разность векторов №1 и №2: " + vector1.difference(vector2));
        System.out.println("Массив N = " + n + " случайных векторов: ");
        List<Vector> array = Vector.arrayVectors(n);
        for(int i = 0; i < n; i++){
            System.out.println("Vector[" + i + "]: " + array.get(i));
        }
    }
}
