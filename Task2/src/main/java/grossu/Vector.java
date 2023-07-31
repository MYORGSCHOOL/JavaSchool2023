package grossu;


import java.util.Random;


/**
 * Класс для работы с векторами
 */
public class Vector {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Метод для создания массива случайных векторов размером N
     *
     * @param n размер массива
     * @return массив случайных векторов
     */
    public static Vector[] getRandomVectorArray(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
        }
        return vectors;
    }

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
     * Метод для вычисления длины вектора
     *
     * @return длина вектора
     */
    public double getVectorLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Метод вычисления скалярного произведения двух векторов: вызывающего и вектора-аргумента
     *
     * @param vector2 второй вектор
     * @return скалярное произведение двух веторов
     */
    public double getScalarProduct(Vector vector2) {
        return this.x * vector2.getX() + this.y * vector2.getY() + this.z * vector2.getZ();
    }

    /**
     * Метод вычисляющий векторное произведение двух векторов: вызывающего и вектора-аргумента
     *
     * @param vector2 второй вектор
     * @return новый вектор - вектороное произведение двух векторов
     */
    public Vector getVectorProduct(Vector vector2) {
        double xNew = this.y * vector2.getZ() - this.z * vector2.getY();
        double yNew = this.z * vector2.getX() - this.x * vector2.getZ();
        double zNew = this.x * vector2.getY() - this.y * vector2.getX();
        return new Vector(xNew, yNew, zNew);
    }

    /**
     * Метод вычисляющий угол между двумя векторами (косинус угла): вызывающего и вектора-аргумента
     *
     * @param vector2 второй вектор
     * @return косинус угла между векторами
     */
    public double getCosAngle(Vector vector2) {
        return getScalarProduct(vector2)
                / getVectorLength() / vector2.getVectorLength();
    }

    /**
     * Метод вычисляющий сумму двух векторов: вызывающего и вектора-аргумента
     *
     * @param vector2 второй вектор
     * @return вектор - сумма двух векторов
     */
    public Vector getSumVector(Vector vector2) {
        return new Vector(this.x + vector2.getX(), this.y + vector2.getY(), this.z + vector2.getZ());
    }

    /**
     * Метод вычисляющий разность двух векторов: вызывающего и вектора-аргумента
     *
     * @param vector2 второй вектор
     * @return вектор - разность двух векторов
     */
    public Vector getDiffVector(Vector vector2) {
        return new Vector(this.x - vector2.getX(), this.y - vector2.getY(), this.z - vector2.getZ());
    }

    /**
     * Получение справочной информации о векторе
     */
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", z=" + z;
    }
}
