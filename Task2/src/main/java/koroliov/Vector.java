package koroliov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Vector {
    private Map<Character, Double> coordinates = new TreeMap<>();

    public Vector(Double x, Double y, Double z) {
        coordinates.put('x', x);
        coordinates.put('y', y);
        coordinates.put('z', z);
    }

    /**
     * Метод, генерирующий список с заданным кол-вом 3-мерных векторов (Случайные координаты)
     * @param count Колличество векторов
     * @return Список векторов
     */
    List<Vector> getVectors(int count) {
        List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < count; i++) vectors.add(vectorGeneration());

        return vectors;
    }

    /**
     * Метод преобразования координат вектора в строку
     * @param v Конструктор Vector
     * @return Строка, координаты вектора, [x; y; z]
     */
    public String getString(Vector v) {
        String one = String.format("%.3f", v.getX());
        String two = String.format("%.3f", v.getY());
        String three = String.format("%.3f", v.getZ());
        return "[" + one + "; " + two + "; " + three + "]";
    }

    /**
     * Метод, вычисляющий длину вектора.
     * @param vector Конструктор Vector
     * @return double Длинна вектора
     */
    public double lengthVector(Vector vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

    /**
     * Метод, вычисляющий скалярное произведение.
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return double Скалярная величина
     */
    public double scalarProduct(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    /**
     *  Метод, вычисляющий векторное произведение с другим вектором. Y1 * Z2 - Z1 * Y2; Z1 * X2 - X1 * Z2; X1 * Y2 - Y1 * X2
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String Координаты вектора
     */
    public Vector vectorProduct(Vector v1, Vector v2) {
        double one = (v1.getY() * v2.getZ() - v1.getZ() * v2.getY());
        double two = (v1.getZ() * v2.getX() - v1.getX() * v2.getZ());
        double three = (v1.getX() * v2.getY() - v1.getY() * v2.getX());
        return new Vector(one, two, three);
    }
    
    /**
     * Метод, вычисляющий угол между векторами (или косинус угла) Scalar / |length(a)| * |length(b)|
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return double Угол вектора
     */
    public double angleVectors(Vector v1, Vector v2) {
        return scalarProduct(v1, v2) / (Math.abs(lengthVector(v1)) * Math.abs(lengthVector(v2)));
    }

    /**
     * Метод, вычисляющий сумму векторов
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String координаты ветора
     */
    Vector amount(Vector v1, Vector v2) {
        Double one = v1.getX() + v2.getX();
        Double two = v1.getY() + v2.getY();
        Double three = v1.getZ() + v2.getZ();
        return new Vector(one, two, three);
    }
    
    /**
     * Метод, вычисляющий разность векторов
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String координаты ветора
     */
    public Vector difference(Vector v1, Vector v2) {
        double one = (v1.getX() - v2.getX());
        double two = (v1.getY() - v2.getY());
        double three = (v1.getZ() - v2.getZ());
        return new Vector(one, two, three);
    }

    /**
     * Метод получения координаты вектора "X"
     * @return Координата вектора "X" double
     */
    public double getX() {return coordinates.get('x');}

    /**
     * Метод получения координаты вектора "Y"
     * @return Координата вектора "Y" double
     */
    public double getY() {return coordinates.get('y');}

    /**
     * Метод получения координаты вектора "Z"
     * @return Координата вектора "Z" double
     */
    public double getZ() {return coordinates.get('z');}

    /**
     * Метод, генерирующий 3-мерный вектор с случайными координатми
     * @return Конструктор Vector с случайными координатами
     */
    private static Vector vectorGeneration() {
        double X = Math.random() * 10;
        double Y = Math.random() * 10;
        double Z = Math.random() * 10;
        Vector temp = new Vector(X, Y, Z);
        temp.coordinates.put('x', X);
        temp.coordinates.put('y', Y);
        temp.coordinates.put('z', Z);

        return temp;
    }
}
