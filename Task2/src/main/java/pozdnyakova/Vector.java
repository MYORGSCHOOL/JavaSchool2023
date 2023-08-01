package pozdnyakova;

public class Vector {
    private final int x;
    private final int y;
    private final int z;

    Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод, вычисляющий длину вектора
     *
     * @return длина вектора
     */
    public double length() {
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        return length;
    }

    /**
     * Метод, вычисляющий скалярное произведение двух векторов
     *
     * @param vector второй вектор
     * @return скалярное произведение векторов
     */
    public int scalarProduct(Vector vector) {
        int scalarProduct = this.x * vector.x + this.y * vector.y + this.z * vector.z;
        return scalarProduct;
    }

    /**
     * Метод, вычисляющий векторное произведение двух векторов
     *
     * @param vector второй вектор
     * @return векторное произведение
     */
    public Vector vectorProduct(Vector vector) {
        int x = this.y * vector.z - this.z * vector.y;
        int y = this.z * vector.x - this.x * vector.z;
        int z = this.x * vector.y - this.y * vector.x;
        Vector newVector = new Vector(x, y, z);
        return newVector;
    }

    /**
     * Метод, вычисляющий косинус угла между двумя векторами
     *
     * @param vector второй вектор
     * @return косинус угла между векторами
     */
    public double cos(Vector vector) {
        double cos = scalarProduct(vector) / (Math.abs(this.length()) * Math.abs(vector.length()));
        return cos;
    }

    /**
     * Метод, вычисляющий сумму двух векторов
     *
     * @param vector второй вектор
     * @return сумма векторов
     */
    public Vector sum(Vector vector) {
        int x = this.x + vector.x;
        int y = this.y + vector.y;
        int z = this.z + vector.z;
        Vector newVector = new Vector(x, y, z);
        return newVector;
    }

    /**
     * Метод, вычисляющий разность двух векторов
     *
     * @param vector второй вектор
     * @return разность векторов
     */
    public Vector sub(Vector vector) {
        int x = this.x - vector.x;
        int y = this.y - vector.y;
        int z = this.z - vector.z;
        Vector newVector = new Vector(x, y, z);
        return newVector;
    }

    /**
     * Метод, возвращающий массив случайных векторов
     *
     * @param n количество векторов в массиве
     * @return массив векторов
     */
    public static Vector[] array(int n) {
        Vector[] vector = new Vector[n];
        for (int i = 0; i != n; i++)
            vector[i] = new Vector((int) (Math.random() * 10), (int) (Math.random() * 10), (int) (Math.random() * 10));
        return vector;
    }

    /**
     * Метод, выводящий координаты вектора
     */
    public void output() {
        System.out.println("x = " + x + ", y = " + y + " z = " + z);
    }
}
