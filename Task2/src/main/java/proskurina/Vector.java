package proskurina;

public class Vector {
    private final double x;
    private final double y;
    private final double z;
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Возвращает длину вектора.
     *
     * @return длина вектора
     */
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    /**
     * Возвращает скалярное произведение на вектор-аргумент.
     *
     * @param v вектор
     * @return результат произведения
     */
    public double scalarProduct(Vector v) {
        return this.x + v.getX() + this.y + v.getY() + this.z + v.getZ();
    }
    
    /**
     * Возвращает векторное произведение на вектор-аргумент.
     *
     * @param v вектор
     * @return результирующий вектор
     */
    public Vector vectorProduct(Vector v) {
        double tmpX = this.y * v.getZ() - this.z * v.getY();
        double tmpY = this.z * v.getX() - this.x * v.getZ();
        double tmpZ = this.x * v.getY() - this.y * v.getX();
        return new Vector(tmpX, tmpY, tmpZ);
    }
    
    /**
     * Возвращает величину угла между вызывающим вектором и вектором-аргументом.
     *
     * @param v вектор-аргумент
     * @return угол между векторами
     */
    public double angle(Vector v) {
        return scalarProduct(v) / (length() * v.length());
    }
    
    /**
     * Возвращает вектор разности вызывающего вектора и вектора-аргумента.
     *
     * @param v вычитаемый вектор
     * @return вектор разности
     */
    public Vector minus(Vector v) {
        return new Vector(this.x - v.getX(), this.y - v.getY(), this.z - v.getZ());
    }
    
    /**
     * Возвращает вектор суммы вызывающего вектора и вектора-аргумента.
     *
     * @param v слагаемый вектор
     * @return вектор суммы
     */
    public Vector plus(Vector v) {
        return new Vector(this.x + v.getX(), this.y + v.getY(), this.z + v.getZ());
    }
    
    /**
     * Возвращает массив указанного размера со случайными векторами
     * с координатами в диапазоне [0.0 ; 1.0).
     *
     * @param count размер массива
     * @return массив с векторами
     * @throws IllegalArgumentException если размер массива меньше нуля
     */
    public static Vector[] getRandomVectors(int count) throws IllegalArgumentException {
        if (count < 0) {
            throw new IllegalArgumentException("Размер массива меньше нуля.");
        }
        Vector[] vectors = new Vector[count];
        for (int i = 0; i < count; i++) {
            vectors[i] = new Vector(Math.random(), Math.random(), Math.random());
        }
        return vectors;
    }
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + ")";
    }
}