package sevostyanov;

import java.util.Random;

public class Vector {
    private final int x;
    private final int y;
    private final int z;
    static final int GENERATION_RANDOM_NUMBER = 1000;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Возвращает длину вектора
     *
     * @return
     */
    public double vectorLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Возвращает скалярное произведение
     *
     * @param vector вектор
     * @return
     */
    public double vectorScalar(Vector vector) {
        return this.x * getX() + this.y * getY() + this.z * getZ();
    }

    /**
     * Возвражает векторное произведение с другим вектором
     * get
     *
     * @param vector
     */
    public Vector vectorMultiplication(Vector vector) {
        return new Vector(this.y * vector.getZ() - this.z * vector.getY(), this.z * vector.getX() - this.x * vector.getZ(), this.x * vector.getY() - this.y - vector.getX());
    }

    /**
     * Возвращает угол между векторами (косинус угла)
     *
     * @param vector
     * @return
     */
    public double vectorCosineAngle(Vector vector) {
        return vectorScalar(vector) / (vectorLength() * vector.vectorLength());
    }

    /**
     * Возвращает сумму вектора который мы вызываем и вектора  аргумента
     *
     * @param vector
     * @return
     */
    public Vector vectorPlus(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    /**
     * Возвращает разность вектора который мы вызываем и вектора  аргумента
     *
     * @param vector
     * @return
     */
    public Vector vectorMinus(Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }

    /**
     * Метод для генерации указаного количества векторов
     *
     * @param n количество векторов
     * @return возвращает массив векторов
     */
    public static Vector[] getRandomVector(int n) {
        Vector[] arrVector = new Vector[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arrVector[i] = new Vector(random.nextInt(GENERATION_RANDOM_NUMBER), random.nextInt(GENERATION_RANDOM_NUMBER), random.nextInt(GENERATION_RANDOM_NUMBER));
        }
        return arrVector;
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

    @Override
    public String toString() {
        return "Vector{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}

