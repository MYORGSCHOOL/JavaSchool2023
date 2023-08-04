package golovchenko;

import java.util.ArrayList;
import java.util.List;

public class Vector {
    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x=" + String.format("%.3f", x) +
                ", y=" + String.format("%.3f", y) +
                ", z=" + String.format("%.3f", z);
    }

    /**
     * Нахождение длины вектора
     * @return численное значение длины
     */
    public double length(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Скалярное произведение векторов
     * @param vector вектор на который необходимо умножить
     * @return численное значение скалярного произведение двух векторов
     */
    public double scalarProduct(Vector vector){
        return x * vector.x + y * vector.y + z * vector.z;
    }

    /**
     * Векторное произведение векторов
     * @param vector вектор на который необходимо умножить
     * @return новый вектор, равный векторному произведению двух других
     */
    public Vector vectorProduct(Vector vector){
        return new Vector(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    /**
     * Угол между векторами (или косинус угла)
     * @param vector вектор, угол между которым необходимо найти
     * @return численное значение угла (или косинуса угла)
     */
    public double cosAngle(Vector vector){
        return scalarProduct(vector) / ( length() * vector.length());
    }

    /**
     * Сумма векторов
     * @param vector вектор с которым производится суммирование
     * @return новый вектор, равный сумме двух других
     */
    public Vector sum(Vector vector) {
        return new Vector(x + vector.x, y + vector.y, z + vector.z);
    }

    /**
     * Разность векторов
     * @param vector вектор с которым производится разность
     * @return новый вектор равный разности двух других
     */
    public Vector difference(Vector vector) {
        return new Vector(x - vector.x, y - vector.y, z - vector.z);
    }

    /**
     * Создание массива случайных векторов
     * @param n количество векторов
     * @return array - список векторов
     */
    public static List<Vector> arrayVectors(int n){
        List<Vector> array = new ArrayList<>();
        for(int i = 0; i < n; i ++){
            array.add(new Vector(Math.random() * 11, Math.random() * 11, Math.random() * 11));
        }
        return array;
    }
}
