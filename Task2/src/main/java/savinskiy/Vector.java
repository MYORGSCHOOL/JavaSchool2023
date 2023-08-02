package savinskiy;

import java.util.Random;


/**
 * Класс Vector представляет вектор в трехмерном пространстве
 */
public class Vector {
    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Вычисляет длину вектора
     *
     * @return длина вектора
     */
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Вычисляет скалярное произведение двух векторов
     *
     * @param otherVector другой вектор для вычисления метода
     * @return скалярное произведение векторов
     */
    public double scalarProduct(Vector otherVector) {
        return this.x * otherVector.x +
                this.y * otherVector.y +
                this.x * otherVector.z;
    }


    /**
     * Вычисляет векторное произведение двух векторов
     *
     * @param otherVector другой вектор для вычисления метода
     * @return векторное произведение векторов
     */
    public Vector vectorProduct(Vector otherVector) {
        double newX = this.y * otherVector.z - this.z * otherVector.y;
        double newY = this.z * otherVector.x - this.x * otherVector.z;
        double newZ = this.x * otherVector.y - this.y * otherVector.x;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Вычисляет косинус угла между двуми векторами
     *
     * @param otherVector другой вектор для вычисления метода
     * @return косинус угла между векторами
     */
    public double cos(Vector otherVector) {
        double scalar = otherVector.scalarProduct(otherVector);
        double length = otherVector.length() * this.length();
        return scalar / length;
    }


    /**
     * Вычисляет операцию сложения двух векторов
     *
     * @param otherVector другой вектор для вычисления метода
     * @return новый вектор полученный в результате вычисления
     */
    public Vector add(Vector otherVector) {
        double newX = this.x + otherVector.x;
        double newY = this.y + otherVector.y;
        double newZ = this.z + otherVector.z;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Вычисляет операцию вычитания двух векторов
     *
     * @param otherVector другой вектор для вычисления метода
     * @return новый вектор полученный в результате вычитания
     */
    public Vector subtract(Vector otherVector) {
        double newX = this.x - otherVector.x;
        double newY = this.y - otherVector.y;
        double newZ = this.z - otherVector.z;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Метод создает новый массив векторов со случаныйми занчениями с заданым размером
     *
     * @param n размер массива векторов
     * @return массив векторов размеров N со случайными значениями
     */
    public static Vector[] randomVectorsArray(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            double newX = random.nextDouble();
            double newY = random.nextDouble();
            double newZ = random.nextDouble();
            vectors[i] = new Vector(newX, newY, newZ);
        }

        return vectors;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
