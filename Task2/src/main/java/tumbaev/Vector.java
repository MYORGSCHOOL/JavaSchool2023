package tumbaev;

import static java.lang.Math.*;

public class Vector {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Генерирует массив векторов заданного размера со случайными координатами.
     * Координаты векторов находятся в диапазоне [0.0, 1.0).
     *
     * @param arraySize размер возвращаемого массива
     * @return массив векторов заданного размера со случайными координатами
     * @throws IllegalArgumentException переданный размер массива отрицательный
     */
    public static Vector[] getRandomVectors(int arraySize) {
        if (arraySize < 0) {
            throw new IllegalArgumentException("Размер массива не может быть отрицательным");
        }

        Vector[] vectors = new Vector[arraySize];
        for (int i = 0; i < arraySize; i++) {
            vectors[i] = new Vector(random(), random(), random());
        }
        return vectors;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Вычисляет длину вектора
     *
     * @return длина данного вектора
     */
    public double length() {
        return sqrt(x * x + y * y + z * z);
    }

    /**
     * Вычисляет скалярное произведение векторов
     *
     * @param vector вектор на который умножается данный
     * @return результат скалярного произведения
     */
    public double scalarProduct(Vector vector) {
        return x * vector.getX() + y * vector.getY() + z * vector.getZ();
    }

    /**
     * Вычисляет векторное произведение векторов
     *
     * @param vector вектор на который умножается данный
     * @return результат векторного произведения
     */
    public Vector vectorProduct(Vector vector) {
        return new Vector(
                y * vector.getZ() - z * vector.getY(),
                z * vector.getX() - x * vector.getZ(),
                x * vector.getY() - y * vector.getX()
        );
    }

    /**
     * Вычисляет косинус угла между данным и переданным векторами
     *
     * @param vector другой вектор
     * @return косинус угла между данным и переданным векторами
     */
    public double cos(Vector vector) {
        return scalarProduct(vector) / abs(length()) * abs(vector.length());
    }

    /**
     * Вычисляет сумму данного и переданного векторов
     *
     * @param vector другой вектор
     * @return Вектор суммы данного и переданного векторов
     */
    public Vector plus(Vector vector) {
        return new Vector(
                x + vector.getX(),
                y + vector.getY(),
                z + vector.getZ()
        );
    }

    /**
     * Вычисляет разность данного и переданного векторов
     *
     * @param vector другой вектор
     * @return Вектор разности данного и переданного векторов
     */
    public Vector minus(Vector vector) {
        return new Vector(
                x - vector.getX(),
                y - vector.getY(),
                z - vector.getZ()
        );
    }


    /**
     * Возвращает строковое представление вектора.
     * Количество цифр после запятой в координатах ограничено до 4
     * @return строковое представление вектора
     */
    @Override
    public String toString() {
        return "Vector{" +
                "x=" + String.format("%.4f", x) +
                ", y=" + String.format("%.4f", y) +
                ", z=" + String.format("%.4f", z) +
                '}';
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
}
