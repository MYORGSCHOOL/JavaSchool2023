package sevostyanov;

import java.util.Random;

public class Vector {
    int x;
    int y;
    int z;

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
     * ¬озвращает длину вектора
     *
     * @return
     */
    public double vectorLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * ¬озвращает скал€рное произведение
     *
     * @param vector вектор
     * @return
     */
    public double vectorScalar(Vector vector) {
        return this.x * getX() + this.y * getY() + this.z * getZ();
    }

    /**
     * ¬озвражает векторное произведение с другим вектором
     * get
     *
     * @param vector
     */
    public Vector vectorMultiplication(Vector vector) {
        return new Vector(this.y * vector.getZ() - this.z * vector.getY(), this.z * vector.getX() - this.x * vector.getZ(), this.x * vector.getY() - this.y - vector.getX());
    }

    /**
     * ¬озвращает угол между векторами (косинус угла)
     *
     * @param vector
     * @return
     */
    public double vectorCosineAngle(Vector vector) {
        return vectorScalar(vector) / (vectorLength() * vector.vectorLength());
    }

    /**
     * ¬озвращает сумму вектора который мы вызываем и вектора  аргумента
     *
     * @param vector
     * @return
     */
    public Vector vectorPlus(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    /**
     * ¬озвращает разность вектора который мы вызываем и вектора  аргумента
     *
     * @param vector
     * @return
     */
    public Vector vectorMinus(Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }

    /**
     * ћетод дл€ генерации указаного количества векторов
     *
     * @param n количество векторов
     * @return возвращает массив векторов
     */
    public static Vector[] getRandomVector(int n) {
        Vector[] arrVector = new Vector[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arrVector[i] = new Vector(random.nextInt(312), random.nextInt(312), random.nextInt(312));
        }
        return arrVector;
    }

    @Override
    public String toString() {
        return "Vector{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}

