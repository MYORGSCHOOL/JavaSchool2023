package dushkina;

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
     * Метод, вычисляющий длину вектора.
     *
     * @return длина вектора
     */
    public double calculatingTheLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Метод, вычисляющий скалярное произведение:
     *
     * @param vector2 вектор, с которым текущий экземпляр класса производит скалярное произведение
     * @return скалярное произведение
     */
    public double calculatingTheScalarProduct(Vector vector2) {
        return this.x * vector2.getX() + this.y * vector2.getY() + this.z * vector2.getZ();
    }

    /**
     * Метод, вычисляющий векторное произведение с другим вектором
     *
     * @param vector2 вектор, с которым текущий экземпляр класса производит векторное произведение
     * @return новый вектор - векторное произведение текущего экземпляра класса и переданного экземпляра
     */
    public Vector calculatingTheVectorProduct(Vector vector2) {
        double x, y, z;
        x = this.y * vector2.getZ() - this.z * vector2.getY();
        y = this.z * vector2.getX() - this.x * vector2.getZ();
        z = this.x * vector2.getY() - this.y * vector2.getX();
        return new Vector(x, y, z);
    }

    /**
     * Метод, выводщий координаты вектора
     *
     * @return строка координат вектора
     */
    public String coordinateOutput() {
        return ("(" + this.x + ", " + this.y + ", "
                + this.z + ")");
    }

    /**
     * Метод, вычисляющий угол между векторами
     *
     * @param vector2 вектор, с которым текущий экземпляр класса вычисляет угол
     * @return угол между векторами
     */
    public double calculatingTheAngleBetweenVectors(Vector vector2) {
        return (this.calculatingTheScalarProduct(vector2)) /
                (this.calculatingTheLength() * vector2.calculatingTheLength());
    }

    /**
     * Метод, вычисляющий сумму двух векторов
     *
     * @param vector2 переданный экземпляр класса
     * @return новый вектор - сумма вектора-текущего экземпляра класса и вектора-переданног экземпляра
     */
    public Vector calculatingTheSumOfVectors(Vector vector2) {
        return new Vector(
                this.x + vector2.getX(),
                this.y + vector2.getY(),
                this.z + vector2.getZ()
        );
    }

    /**
     * Метод, вычисляющий разность двух векторов
     *
     * @param vector2 переданный экземпляр класса
     * @return новый вектор - разность между вектором-текущем экземпляром класса и вектором-переданног экземпляра
     */
    public Vector calculatingTheDifferenceOfVectors(Vector vector2) {
        return new Vector(
                this.x - vector2.getX(),
                this.y - vector2.getY(),
                this.z - vector2.getZ()
        );
    }

    /**
     * Статический метод для создания массива случайных векторов размером size.
     *
     * @param size размер массива
     * @return массив случайных векторов
     */
    public static Vector[] getVectorArray(int size) {
        Vector[] vector = new Vector[size];
        for (int i = 0; i < size; i++) {
            vector[i] = new Vector(Math.random(), Math.random(), Math.random());
        }
        return vector;
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
