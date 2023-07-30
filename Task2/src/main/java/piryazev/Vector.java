package piryazev;

import java.util.Random;

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
     * Метод вычисляющий длину вектора
     *
     * @return длина вектора
     */
    public double vectorLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Метод вычисляющий скалярное произведение
     *
     * @param vector вектор, на который умножаем
     * @return скалярное произведение векторов
     */
    public double scalarProduct(Vector vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    /**
     * Метод вычисляющий векторное произведение
     *
     * @param vector умножаемый вектор
     * @return новый вектор векторного произведения
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x
        );
    }

    /**
     * Метод вычисляющий угол между векторами
     *
     * @param vector второй вектор
     * @return угол между двумя векторами
     */
    public double vectorAngle(Vector vector) {
        return this.scalarProduct(vector) /
                (Math.abs(this.vectorLength()) * Math.abs(vector.vectorLength()));
    }

    /**
     * Метод вычисляющий сумму вектoра
     *
     * @param vector прибавляемый вектор
     * @return сумму векторов в виде нового вектора
     */
    public Vector vectorSum(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    /**
     * Метод вычисляющий разность векторов
     *
     * @param vector вектор, который отнимаем
     * @return разность векторов в виде нового вектора
     */
    public Vector vectorDifference(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    /**
     * Статический метод, который принимает целое число N, и возвращает массив
     * случайных векторов размером N
     *
     * @param n случайное число
     * @return массив векторов
     */
    public static Vector[] vectorsArray(int n) {
        Vector[] vector = new Vector[n];
        final Random random = new Random();

        for (int i = 0; i < n; i++) {
            vector[i] = new Vector(random.nextInt(10), random.nextInt(10), random.nextInt(10));
        }
        return vector;
    }

    @Override
    public String toString() {
        return "Vector {" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                "}";
    }
}
