package volovnik;

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
     * Метод вычисляет скалярное произведение векторов
     *
     * @param vector второй вектор
     * @return скалярное произведение векторов
     */
    public double calcScalar(Vector vector) {
        return ((x * vector.x) + (y * vector.y) + (z * vector.z));
    }

    /**
     * Метод вычисляет векторное произведение векторов
     *
     * @param vector второй вектор
     * @return векторное произведение векторов
     */
    public Vector calcVectorMultiply(Vector vector) {
        double newX = (y * vector.z) - (z * vector.y);
        double newY = (z * vector.x) - (x * vector.z);
        double newZ = (x * vector.y) - (y * vector.x);
        return new Vector(newX, newY, newZ);
    }

    /**
     * Метод вычисляет угол между векторами
     *
     * @param vector второй вектор
     * @return угол между векторами
     */
    public double calcAngle(Vector vector) {
        double scalar = calcScalar(vector);
        double v1Length = calcLength();
        double v2Length = vector.calcLength();
        return (scalar / (v1Length * v2Length));
    }

    /**
     * Метод вычисляет сумму векторов
     *
     * @param vector второй вектор
     * @return сумма векторов
     */
    public Vector calcSum(Vector vector) {
        double newX = x + vector.x;
        double newY = y + vector.y;
        double newZ = z + vector.z;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Метод вычисляет разность векторов
     *
     * @param vector второй вектор
     * @return разность векторов
     */
    public Vector calcSubtract(Vector vector) {
        double newX = x - vector.x;
        double newY = y - vector.y;
        double newZ = z - vector.z;
        return new Vector(newX, newY, newZ);
    }

    /**
     * Метод создаёт заданное число векторов
     *
     * @param n количество векторов
     * @return массив векторов
     */
    public static Vector[] getRandomVectors(int n, int upperBound) {
        Vector[] vectors = new Vector[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(random.nextDouble() * upperBound, random.nextDouble() * upperBound,
                    random.nextDouble() * upperBound);
        }
        return vectors;
    }

    /**
     * Метод вычисляет длину вектора
     *
     * @return длина вектора
     */
    public double calcLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
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