package karmanchikova;

import java.util.Arrays;

import static java.lang.Math.*;

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
     * Метод высчитывающий длину вектора
     * Указываются параметры вектора прикотором был вызван метод
     *
     * @return Длина вектора расчитанная по формуле
     */
    double getVectorLength() {
        return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
    }

    /**
     * Метод вычисляющий скалярное произведение
     *
     * @param a перадача значений первого вектора
     * @param b перадача значений второго вектора
     * @return скалярное произведение двух векторов по формуле
     */
    int getScalar(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    /**
     * Метод вычисляющий векторное произведение с другим вектором
     *
     * @param a перадача значений первого вектора
     * @param b перадача значений второго вектора
     * @return векторное произведение расчитанное по формуле
     */
    int[] getVectorProduct(Vector a, Vector b) {
        int dx = a.y * b.z - a.z * b.y;
        int dy = a.z * b.x - a.x * b.z;
        int dz = a.x * b.y - a.y * b.x;
        return new int[]{dx, dy, dz};
    }

    /**
     * Метод вычисляющий угол между векторами
     *
     * @param a перадача значений первого вектора
     * @param b перадача значений второго вектора
     * @return угол между векторами вычисляющий по формуле
     */
    double getCorner(Vector a, Vector b) {
        return getScalar(a, b) / (a.getVectorLength() * b.getVectorLength());
    }

    /**
     * Метод вычисляющий сумму векторов
     *
     * @param a перадача значений первого вектора
     * @param b перадача значений второго вектора
     * @return сумма векторов
     */
    static int[] getSumm(Vector a, Vector b) {
        return new int[]{a.x + b.x, a.y + b.y, a.z + b.z};
    }

    /**
     * Метод вычисляющий разность векторов
     *
     * @param a перадача значений первого вектора
     * @param b перадача значений второго вектора
     * @return разность векторов
     */
    static int[] getDifference(Vector a, Vector b) {
        return new int[]{a.x - b.x, a.y - b.y, a.z - b.z};
    }


    /**
     * Метод возвращающий массив случайных векторов
     *
     * @param n колличество векторов
     * @return массив случйных векторов
     */
    static Vector[] generate(int n) {
        Vector[] vectors = new Vector[n];
        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector((int) (random() * 200) - 100, (int) (random() * 200) - 100, (int) (random() * 200) - 100);
        }
        return vectors;
    }

    public static void main(String[] args) {
        Vector a = new Vector(1, 2, 5);
        Vector b = new Vector(2, 4, 7);
        System.out.println("Длина вектора: " + a.getVectorLength());
        System.out.println("Скалярное произведение: " + a.getScalar(a, b));
        System.out.println("Векторное произведение: " + Arrays.toString(a.getVectorProduct(a, b)));
        System.out.println("Угол между векторами: " + a.getCorner(a, b));
        System.out.println("Сумма векторов: " + Arrays.toString(getSumm(a, b)));
        System.out.println("Разность векторов: " + Arrays.toString(getDifference(a, b)));
        System.out.println("Массив случайных векторов:");
        Vector[] vectors = generate(3);
        for (int i = 0; i < vectors.length; i++) {
            System.out.println(vectors[i].x + " " + vectors[i].y + " " + vectors[i].z);
        }
    }
}
