package koroliov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Vector {
    private Map<Character, Double> coordinates = new TreeMap<>();

    /**
     * Метод ввода координат вектора в 3-х мерном пространстве с клавиатуры
     * @param in Scannet System.in
     */
    private void setVector(Scanner in) {
        System.out.print("Enter x: ");
        coordinates.put('x', in.nextDouble());
        System.out.print("Enter y: ");
        coordinates.put('y', in.nextDouble());
        System.out.print("Enter z: ");
        coordinates.put('z', in.nextDouble());
    }

    /**
     * Метод ввода 1 вектора
     * @param v Конструктор Vector
     * @param in Scaner System.in
     */
    void input(Vector v, Scanner in) {
        System.out.println("Введите значения вектора: ");
        v.setVector(in);
    }

    /**
     * Метод ввода 2-х векторов
     * @param v1 Конструктор Vector1
     * @param v2 Конструктор Vector2
     * @param in Scaner System.in
     */
    void input(Vector v1, Vector v2, Scanner in) {
        System.out.println("Ввекдите значение 1 вектора: ");
        v1.setVector(in);
        System.out.println("Ввекдите значение 2 вектора: ");
        v2.setVector(in);
    }

    // [x]: Статический метод, который принимает целое число N, и возвращает массив случайных векторов размером N
    /**
     * Метод, генерирующий список с заданным кол-вом 3-мерных векторов (Случайные координаты)
     * @param count Колличество векторов
     * @return Список векторов
     */
    List<Vector> setVectors(int count) {
        List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < count; i++) vectors.add(vectorGeneration());

        return vectors;
    }
    
    /**
     * Метод, генерирующий 3-мерный вектор с случайными координатми
     * @return Конструктор Vector с случайными координатами
     */
    private static Vector vectorGeneration() {
        Vector temp = new Vector();
        double X = Math.random() * 10;
        double Y = Math.random() * 10;
        double Z = Math.random() * 10;
        temp.coordinates.put('x', X);
        temp.coordinates.put('y', Y);
        temp.coordinates.put('z', Z);

        return temp;
    }

    /**
     * Метод преобразования координат вектора в строку
     * @param v Конструктор Vector
     * @return Строка, координаты вектора, [x; y; z]
     */
    public String setString(Vector v) {
        String one = String.format("%.3f", v.getX());
        String two = String.format("%.3f", v.getY());
        String three = String.format("%.3f", v.getZ());
        return setString(one, two, three);
    }

    /**
     * Метод преобразования координат вектора по имеющимся координатам(String)
     * @param X координата X (String)
     * @param Y координата Y (String)
     * @param Z координата Z (String)
     * @return Строка, координаты вектора, [x; y; z]
     */
    public String setString(String X, String Y, String Z)  {
        return "[ " + X + "; " + Y + "; " + Z + " ]";
    }

    /**
     * Метод получения координаты вектора "X"
     * @return Координата вектора "X" double
     */
    double getX() {return coordinates.get('x');}

    /**
     * Метод получения координаты вектора "Y"
     * @return Координата вектора "Y" double
     */
    double getY() {return coordinates.get('y');}

    /**
     * Метод получения координаты вектора "Z"
     * @return Координата вектора "Z" double
     */
    double getZ() {return coordinates.get('z');}
}
