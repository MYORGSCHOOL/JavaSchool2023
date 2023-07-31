package skrebkov;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    /**
     * Cчитает длину вектора
     *
     * @return длину вектора
     */
    public double vectorLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Считает скалярное произведение двух векторов
     *
     * @param secondVector Второй вектор
     * @return возвращает скалярное произведение
     */
    public double scalarProduct(Vector secondVector) {
        return x * secondVector.getX() +
                y * secondVector.getY() +
                z * secondVector.getZ();
    }

    /**
     * Считает векторное произведение
     *
     * @param secondVector Второй вектор
     * @return вектор получившийся в результате произведения
     */
    public Vector vectorProduct(Vector secondVector) {

        int nextVectorX = this.y * secondVector.getZ() - this.z * secondVector.getY();
        int nextVectorY = this.z * secondVector.getX() - this.x * secondVector.getZ();
        int nextVectorZ = this.x * secondVector.getY() - this.y * secondVector.getX();

        return new Vector(nextVectorX,
                nextVectorY,
                nextVectorZ);
    }

    /**
     * считает косинус угла между векторами
     *
     * @param secondVector второй вектор
     * @return косинус угла
     */
    public double cosAngle(Vector secondVector) {
        return this.scalarProduct(secondVector) /
                (this.vectorLength() * secondVector.vectorLength());
    }

    /**
     * Считает сумму векторов
     *
     * @param secondVector второй вектор
     * @return вектор представляющий сумму двух векторов
     */
    public Vector sumVectors(Vector secondVector) {
        return new Vector(x + secondVector.getX(),
                y + secondVector.getY(),
                z + secondVector.getZ());
    }

    /**
     * Считает разность векторов
     *
     * @param secondVector второй вектор
     * @return вектор представляющий разность двух векторов
     */
    public Vector differenceVectors(Vector secondVector) {
        return new Vector(x - secondVector.getX(),
                y - secondVector.getY(),
                z - secondVector.getZ());
    }

    /**
     * Статический метод для генерации n векторов
     *
     * @param n количество векторов
     * @return возвращает массив векторов
     */
    public static Vector[] generateVectors(int n) {

        Vector[] arrVectors = new Vector[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arrVectors[i] = new Vector(random.nextInt(20),
                    random.nextInt(20),
                    random.nextInt(20));
        }
        return arrVectors;
    }

    /**
     * Метод для красивого вывода
     *
     * @return Строку со всеми координатами вектора
     */

    public String vectorInfoForConsole() {
        return "Координаты вектора X=" + x + " Y=" + y + " Z=" + z;
    }
}
