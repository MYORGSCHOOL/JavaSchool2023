package tsimmer;


import java.util.Random;

public class Vector {
    final int x;
    final int y;
    final int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод вычисляет длину вектора по формуле
     *
     * @return длина вектора
     */
    public Double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Метод вычисляет скалярное произведение по формуле
     *
     * @param vector2 второй вектор
     * @return скалярное произведение
     */
    public double scalarProduct(Vector vector2) {

        return x * vector2.x + y * vector2.y + z * vector2.z;
    }

    /**
     * Метод вычисляет векторное произведение
     *
     * @param vector2 второй вектор
     * @return вектор
     */
    public Vector vectorProduct(Vector vector2) {
        return new Vector(
                y * vector2.z - z * vector2.y,
                z * vector2.x - x * vector2.z,
                x * vector2.y - y * vector2.x);
    }

    /**
     * Метод вычисляет косинус угла между векторами
     *
     * @param vector2 второй вектор
     * @return косинус угла
     */
    public double cosAngle(Vector vector2) {

        return scalarProduct(vector2) / (length() * vector2.length());
    }

    /**
     * Метод вычисляет сумму векторов
     *
     * @param vector2 второй вектор
     * @return вектор
     */
    public Vector sumVectors(Vector vector2) {
        return new Vector(
                x + vector2.x,
                y + vector2.y,
                z + vector2.z);
    }

    /**
     * Метод вычисляет разность векторов
     *
     * @param vector2 второй вектор
     * @return вектор
     */
    public Vector diffVectors(Vector vector2) {
        return new Vector(
                x - vector2.x,
                y - vector2.y,
                z - vector2.z);
    }

    /**
     * Метод возвращающий массив случайных векторов
     *
     * @param n размер массива
     * @return массив случайных векторов
     */
    public static Vector[] arrayVectors(int n) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(random.nextInt(10),
                    random.nextInt(10),
                    random.nextInt(10));
        }
        return vectors;
    }

    /**
     * Метод для вывода координат векторов
     *
     * @return строка координат
     */
    public String consoleOutput() {

        return "x:" + x + " y:" + y + " z:" + z;
    }
}
