package abdullaeva;

import java.util.Random;

public class Vector {
    final double x;
    final double y;
    final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     * Метод генерирует массив случайных векторов с размерностью N
     *
     * @param n - заданная размерность для случайных векторов
     * @return массив случайных векторов с размерностью N
     */
    public static Vector[] getRandArrOfVectors(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
        }
        return vectors;
    }

    /**
     * Метод вычисляет длину вектора
     *
     * @return длину вектора
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Метод вычисляет скалярное произведение двух векторов
     *
     * @param vector2 - второй вектор, с которым скалярно перемножается изначальный вектор
     * @return скалярное произведение двух векторов
     */
    public double getScalar(Vector vector2) {
        return this.x * vector2.getX() + this.y * vector2.getY() + this.z * vector2.getZ();
    }

    /**
     * Метод вычисляет векторное произведение двух векторов
     *
     * @param vector2 - второй вектор, с которым векторно перемножается изначальный вектор
     * @return новый вектор - результат векторного умножения двух векторов
     */
    public Vector getProduct(Vector vector2) {
        double newX = this.y * vector2.getZ() - this.z * vector2.getY();
        double newY = this.z * vector2.getX() - this.x * vector2.getZ();
        double newZ = this.x * vector2.getY() - this.y * vector2.getX();
        return new Vector(newX, newY, newZ);
    }

    /**
     * Метод вычисляет угол между векторами (или косинус угла)
     *
     * @param vector2 - вектор, угол между которым с исходным нужно вычислить
     * @return угол между векторами (или косинус угла)
     */
    public double getAngleBetween(Vector vector2) {
        return getScalar(vector2) / (getLength() * vector2.getLength());
    }

    /**
     * Метод вычисляет сумму двух векторов
     *
     * @param vector2 - второй вектор, с которым складывается исходный вектор
     * @return сумму двух вектров
     */
    public Vector getSum(Vector vector2) {
        return new Vector(this.x + vector2.getX(), this.y + vector2.getY(), this.z + vector2.getZ());
    }

    /**
     * Метод вычисляет разность двух векторов
     *
     * @param vector2 - второй вектор, который вычитается из исходного вектора
     * @return разность двух векторов
     */
    public Vector getDiff(Vector vector2) {
        return new Vector(this.x - vector2.getX(), this.y - vector2.getY(), this.z - vector2.getZ());
    }
}
