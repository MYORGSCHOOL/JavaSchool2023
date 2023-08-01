package kashuba;

import java.util.Random;

/**
 * Класс с методами для выполнения некоторых операций над вектором
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
     * Метод который вычисляет длину вектора
     *
     * @return длина вектора
     */
    public double getLenght() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Метод который вычисляет скалярное произведение
     *
     * @return скалярное произведение
     */
    public double getScalarProduct(double x, double y, double z) {
        return (this.x * x + this.y * y + this.z * z);
    }

    /**
     * Метод вычисляющий векторное произведение
     *
     * @param x - абсцисса
     * @param y - ордината
     * @param z - аппликата
     * @return объект класса Vector, полученные векторным произведением с другим вектором
     */
    public Vector getCrossProduct(double x, double y, double z) {
        return new Vector(
                this.y * z - this.z * y,
                this.z * x - this.x * z,
                this.x * y - this.y * x
        );
    }

    /**
     * Метод вычисляющий угол между векторами
     *
     * @param x - абсцисса
     * @param y - ордината
     * @param z - аппликата
     * @return косинус угла между векторами
     */
    public double getAngle(double x, double y, double z) {
        return getScalarProduct(x, y, z) / (getLenght() * new Vector(x, y, z).getLenght());
    }

    /**
     * Метод вычисляющий сумму двух векторов
     *
     * @param x - абсцисса
     * @param y - ордината
     * @param z - аппликата
     * @return объект класса Vector - сумму двух векторов
     */
    public Vector getSum(double x, double y, double z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Метод вычисляющий разность двух векторов
     *
     * @param x - абсцисса
     * @param y - ордината
     * @param z - аппликата
     * @return объект класса Vector - разность двух векторов
     */
    public Vector getDifference(double x, double y, double z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Статический метод для генерации векторов размером n
     *
     * @param n - кол-во векторов
     * @return массив векторов
     */
    public static Vector[] getVectorsArray(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(random.nextInt(10), random.nextInt(10), random.nextInt(10));
        }
        return vectors;
    }
}
