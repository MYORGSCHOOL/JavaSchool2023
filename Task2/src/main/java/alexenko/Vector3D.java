package alexenko;

import java.util.concurrent.ThreadLocalRandom;

public class Vector3D {

    private final Integer x;
    private final Integer y;
    private final Integer z;

    public Vector3D(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод вычисляет скалярное произведение двух векторов по формуле:
     * x1*x2 + y1*y2 + z1*z2
     *
     * @param v1 первый вектор
     * @param v2 второй вектор
     * @return скалярное произведение первого и второго вектора
     */
    public static Integer getScalar(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    /***
     * Метод вычисляет векторное произведение двух векторов по формуле:
     * (y1*z2 - z1y2, z1*x2 - x1*z2, x1*y2 - y1*x2)
     *
     * @param v1 первый вектор
     * @param v2 второй вектор
     * @return вектор, перпендикулярный плоскости, образованной первым и вторым вектором
     */
    public static Vector3D getVector(Vector3D v1, Vector3D v2) {
        Integer x = v1.y * v2.z - v1.z * v2.y;
        Integer y = v1.z * v2.x - v1.x * v2.z;
        Integer z = v1.x * v2.y - v1.y * v2.x;

        return new Vector3D(x, y, z);
    }

    /***
     * Метод вычисляет угол между двумя векторами (косинус угла) по формуле:
     * (a,b) / (|a|*|b|)
     * Скаялрное произведение векторов делится на произведение модулей (длин) векторов.
     *
     * @param v1 первый вектор
     * @param v2 второй вектор
     * @return угол (косинус угла) между первым и вторым вектором
     */
    public static Double getAngle(Vector3D v1, Vector3D v2) {
        Integer scalar = getScalar(v1, v2);
        Double len1 = v1.getLength();
        Double len2 = v2.getLength();

        return scalar / (len1 * len2);
    }

    /***
     * Метод вычисляет сумму двух векторов по формуле:
     * (x1+x2, y1+y2, z1+z2)
     *
     * @param v1 первый вектор
     * @param v2 второй вектор
     * @return вектор, являющийся суммой первого и второго вектора
     */
    public static Vector3D getSum(Vector3D v1, Vector3D v2) {
        Integer x = v1.x + v2.x;
        Integer y = v1.y + v2.y;
        Integer z = v1.z + v2.z;

        return new Vector3D(x, y, z);
    }

    /***
     * Метод вычисляет разность двух векторов по формуле:
     * (x1-x2, y1-y2, z1-z2)
     *
     * @param v1 первый вектор
     * @param v2 второй вектор
     * @return вектор, полученный в результате вычитания второго вектора из первого вектора
     */
    public static Vector3D getDifference(Vector3D v1, Vector3D v2) {
        Integer x = v1.x - v2.x;
        Integer y = v1.y - v2.y;
        Integer z = v1.z - v2.z;

        return new Vector3D(x, y, z);
    }

    /***
     * Метод создаёт массив векторов заданного размера.
     * Координаты векторов являются случайно сгенерированными числами в диапозоне от 0 до 100.
     *
     * @param n длина массива
     * @return массив заданной длины, заполненный случайными векторами
     */
    public static Vector3D[] getArray(Integer n) {

        Vector3D[] vectors = new Vector3D[n];

        for (int i = 0; i < n; i++) {

            Integer x = ThreadLocalRandom.current().nextInt(101);
            Integer y = ThreadLocalRandom.current().nextInt(101);
            Integer z = ThreadLocalRandom.current().nextInt(101);

            vectors[i] = new Vector3D(x, y, z);
        }

        return vectors;
    }

    /**
     * Метод вычисляет длину вектра по формуле:
     * x^2 + y^2 + z^2
     *
     * @return длина вектора
     */
    public Double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
