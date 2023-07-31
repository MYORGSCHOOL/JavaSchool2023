package babadzhanov;

public class Vector {

    final double x;
    final double y;
    final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод вычисляет сумму или разность двух векторов в зависимости от переданного параметра
     *
     * @param first  Первый вектор
     * @param second Второй вектор
     * @param action Операция над векторами
     * @return Сумму или разность векторов
     */
    public static Vector printVectorCalculations(Vector first, Vector second, String action) {
        if (action.equals("sum")) {
            return new Vector(first.x + second.x, first.y + second.y, first.z + second.z);
        }
        if (action.equals("dif")) {
            return new Vector(first.x - second.x, first.y - second.y, first.z - second.z);
        }
        return null;
    }

    /**
     * Метод создает массив рандомных ветокров, размера N
     *
     * @param n Размер массива
     * @return Массив рандомных векторов
     */
    public static Vector[] getRandomVectors(int n) {
        Vector[] vectors = new Vector[n];
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(Math.random(), Math.random(), Math.random());
        }
        return vectors;
    }

    /**
     * Метод вычисляет длину вектора
     *
     * @return Длину вектора
     */
    public Double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Метод вычисляет скалярное произведение 2-х векторов
     *
     * @param second Второй вектор
     * @return Скалярное произведение
     */
    public Double getScalarMultiply(Vector second) {
        return x * second.x + y * second.y + z * second.z;
    }

    /**
     * Метод вычисляет векторное произведение 2-х векторов
     *
     * @param second Второй вектор
     * @return Векторное произведение
     */
    public Vector getVectorMultiply(Vector second) {
        return new Vector(y * second.z - z * second.y, z * second.x - x * second.z, x * second.y - y * second.x);
    }

    /**
     * Метод вычисляет угол между векторами
     *
     * @param second Второй вектор
     * @return Угол между векторами
     */
    public Double getAngle(Vector second) {
        return getScalarMultiply(second)
                / getLength() / second.getLength();
    }
}
