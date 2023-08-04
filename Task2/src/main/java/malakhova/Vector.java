package malakhova;

public class Vector {
    final int x;
    final int y;
    final int z;

    Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод, вычисляющий длину вектора по формуле
     *
     * @return длина вектора
     */
    double lenghtVector() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Метод, вычисляющий скалярное произведение по формуле
     *
     * @param b второй вектор
     * @return скалярное произведение
     */
    int scalarProduct(Vector b) {
        return (x * b.x + y * b.y + z * b.z);
    }

    /**
     * Метод, вычисляющий векторное произведение по формуле
     *
     * @param b второй вектор
     * @return вектор - результат произведения
     */
    Vector vectorProduct(Vector b) {
        int x2 = y * b.z - z * b.y;
        int y2 = z * b.x - x * b.z;
        int z2 = x * b.y - y * b.x;
        Vector vector2 = new Vector(x2, y2, z2);
        return vector2;
    }

    /**
     * Метод, вычисляющий угол между векторами по формуле
     *
     * @param b второй вектор
     * @return косинус угла
     */
    double corner(Vector b) {
        double n = scalarProduct(b);
        double l1 = lenghtVector();
        double l2 = b.lenghtVector();
        return (n / (l1 * l2));
    }

    /**
     * Метод, вычисляющий сумму векторов по формуле
     *
     * @param b второй вектор
     * @return вектор - результат сложения
     */
    Vector sum(Vector b) {
        int x2 = x + b.x;
        int y2 = y + b.y;
        int z2 = z + b.z;
        Vector vector2 = new Vector(x2, y2, z2);
        return vector2;
    }

    /**
     * Метод, вычисляющий разность векторов по формуле
     *
     * @param b второй вектор
     * @return вектор - результат вычитания
     */
    Vector difference(Vector b) {
        int x2 = x - b.x;
        int y2 = y - b.y;
        int z2 = z - b.z;
        Vector vector2 = new Vector(x2, y2, z2);
        return vector2;
    }

    /**
     * Метод, который создает массив случайных векторов
     *
     * @param n размер массива
     * @return массив векторов
     */
    static Vector[] returnArray(int n) {
        Vector[] arrayVector = new Vector[n];
        for (int i = 0; i < n; i++) {
            int x2 = (int) (Math.random() * 10 + 1);
            int y2 = (int) (Math.random() * 10 + 1);
            int z2 = (int) (Math.random() * 10 + 1);
            arrayVector[i] = new Vector(x2, y2, z2);
        }
        return arrayVector;
    }
}
