package druzhinin;

import java.util.Random;

/**
 * Класс для описания вектора с координатами (x, y, z) в трехмерном пространстве.
 *
 * @author Дружинин Артем
 */
public class Vector3D {
    private final double x;

    private final double y;

    private final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод для получения массива случайных векторов в количестве N.
     *
     * @param n Количество случайных векторов.
     * @return Возвращает массив, заполненный векторами со случайными значениями координат.
     */
    public static Vector3D[] getNRandomVectors(int n) {
        Random random = new Random();
        Vector3D[] randomVectors = new Vector3D[n];

        for (int i = 0; i < n; i++) {
            randomVectors[i] = new Vector3D(random.nextDouble(),
                    random.nextDouble(), random.nextDouble());
        }

        return randomVectors;
    }

    /**
     * Метод для вычисления скалярного произведения двух векторов.
     *
     * @param vector Второй вектор
     * @return Возвращает скалярное произведение двух векторов.
     */
    public double getScalarProduct(Vector3D vector) {
        return x * vector.getX() +
                y * vector.getY() +
                z * vector.getZ();
    }

    /**
     * Метод для вычисления векторного произведения двух векторов.
     *
     * @param vector Второй вектор
     * @return Возвращает вектор, представляющий векторное произведение двух векторов.
     */
    public Vector3D getVectorProduct(Vector3D vector) {
        return new Vector3D(
                y * vector.getZ() - z * vector.getY(),
                z * vector.getX() - x * vector.getZ(),
                x * vector.getY() - y * vector.getX());
    }

    /**
     * Метод для вычисления косинуса угла между векторами
     * путем деления скалярного произведения этих векторов
     * на произведение модулей (длин) этих векторов.
     *
     * @param vector Второй вектор
     * @return Возвращает косинус угла между векторами.
     */
    public double getCosOfAngleBetweenVectors(Vector3D vector) {
        return this.getScalarProduct(vector) /
                (getLength() * vector.getLength());
    }

    /**
     * Метод для сложения двух векторов.
     *
     * @param vector Второй вектор
     * @return Возвращает вектор - результат сложения двух векторов.
     */
    public Vector3D addVector(Vector3D vector) {
        return new Vector3D(
                x + vector.getX(),
                y + vector.getY(),
                z + vector.getZ());
    }

    /**
     * Метод для вычитания из одного вектора другого.
     *
     * @param subtractedVector Вектор, который вычитается (вектор-вычитаемое)
     * @return Возвращает вектор - результат вычитания из одного вектора другого.
     */
    public Vector3D subtractVector(Vector3D subtractedVector) {
        return new Vector3D(
                x - subtractedVector.getX(),
                y - subtractedVector.getY(),
                z - subtractedVector.getZ());
    }

    /**
     * Метод для вычисления длины вектора.
     *
     * @return Возвращает длину вектора.
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vector3D[x=" + x
                + ",y=" + y
                + ",z=" + z
                + "]";
    }
}
