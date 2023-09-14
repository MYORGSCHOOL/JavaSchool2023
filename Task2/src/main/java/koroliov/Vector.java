package koroliov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Vector {
    private Map<Character, Double> coordinates = new TreeMap<>();

    Vector(Double x, Double y, Double z) {
        coordinates.put('x', x);
        coordinates.put('y', y);
        coordinates.put('z', z);
    }

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
        double X = Math.random() * 10;
        double Y = Math.random() * 10;
        double Z = Math.random() * 10;
        Vector temp = new Vector(X, Y, Z);
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
