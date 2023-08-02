package grankin;

/**
 * Класс, описывающий вектор (в трёхмерном пространстве).
 */
public class VectorXYZ {

    /**
     * Координата X
     */
    private double x;

    /**
     * Координата Y
     */
    private double y;

    /**
     * Координата Z
     */
    private double z;

    /**
     * Создание массива векторов с количеством N щтук и диапазоном значений от -100 до 100 включая концы.
     * @param n     - Количество элементов в создаваемом массиве
     * @return      - Массив типа VectorXYZ
     */
    public static VectorXYZ[] generateRandomArr(int n) {
        VectorXYZ[] array = null;

        if (n > 0) {
            array = new VectorXYZ[n];

            for (int i = 0; i < n; i++) {
                array[i] = new VectorXYZ((int) (Math.random()*(200+1)) - 100,
                        (int) (Math.random()*(200+1)) - 100,
                        (int) (Math.random()*(200+1)) - 100);
            }
        }

        return array;
    }

    /**
     * Конструктор по умолчанию
     */
    public VectorXYZ() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    /**
     * Конструктор с параметрами
     * @param x     - координата x
     * @param y     - координата y
     * @param z     - координата z
     */
    public VectorXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Вычисление длины вектора
     * @return      - возвращается длина вектора
     */
    public double length() {
        double result = 0;

        double tmp = x*x + y*y + z*z;

        if (tmp != 0) {
            result = Math.sqrt(tmp);
        }

        return result;
    }

    /**
     * Скалярное произведение векторов
     * @param vector2       - второй вектор, на который умножают
     * @return              - скалярное произведение векторов
     */
    public double scalarMult(VectorXYZ vector2) {
        return this.x * vector2.getX() + this.y * vector2.getY() + this.z * vector2.getZ();
    }

    /**
     * Векторное произведение векторов
     * @param vector2       - Второй вектор
     * @return              - Результирующий вектор
     */
    public VectorXYZ vectorMult(VectorXYZ vector2) {
        return new VectorXYZ(this.y * vector2.getZ() - this.z * vector2.getY(),
                             this.z * vector2.getX() - this.x * vector2.getZ(),
                             this.x * vector2.getY() - this.y * vector2.getX());
    }

    /**
     * Косинус между векторами
     * @param vector2       - второй вектор
     * @return              - значение косинуса
     */
    public double cos(VectorXYZ vector2) {
        double result = 0;

        double znamenatel = this.length() * vector2.length();

        if (znamenatel != 0) {
            result = this.scalarMult(vector2) / znamenatel;
        }

        return result;
    }

    /**
     * Сумма векторов
     * @param vector2       - Второй вектор
     * @return              - Результирующий вектор
     */
    public VectorXYZ summa(VectorXYZ vector2) {
        return new VectorXYZ(this.x + vector2.getX(),
                             this.y + vector2.getY(),
                             this.z + vector2.getZ());
    }

    /**
     * Разность векторов
     * @param vector2       - Второй вектор
     * @return              - Результирующий вектор
     */
    public VectorXYZ raznost(VectorXYZ vector2) {
        return new VectorXYZ(this.x - vector2.getX(),
                             this.y - vector2.getY(),
                             this.z - vector2.getZ());
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
