package koroliov;

public class Method {
    /**
     * Метод, вычисляющий длину вектора.  SQRT(X^2 + Y^2 + Z^2)
     * @param vector Конструктор Vector
     * @return double Длинна вектора
     */
    double lengthVector(Vector vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

    /**
     * Метод, вычисляющий скалярное произведение.  X1 * X2 + Y1 * Y2 + Z1 * Z2
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return double Скалярная величина
     */
    double scalarProduct(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    /**
     *  Метод, вычисляющий векторное произведение с другим вектором. Y1 * Z2 - Z1 * Y2; Z1 * X2 - X1 * Z2; X1 * Y2 - Y1 * X2
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String Координаты вектора
     */
    String vectorProduct(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getY() * v2.getZ() - v1.getZ() * v2.getY());
        String two = String.valueOf(v1.getZ() * v2.getX() - v1.getX() * v2.getZ());
        String three = String.valueOf(v1.getX() * v2.getY() - v1.getY() * v2.getX());
        String result = v1.setString(one, two, three);
        return result;
    }
    
    /**
     * Метод, вычисляющий угол между векторами (или косинус угла) Scalar / |length(a)| * |length(b)|
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return double Угол вектора
     */
    double angleVectors(Vector v1, Vector v2) {
        return scalarProduct(v1, v2) / (Math.abs(lengthVector(v1)) * Math.abs(lengthVector(v2)));
    }

    /**
     * Метод, вычисляющий сумму векторов
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String координаты ветора
     */
    String amount(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getX() + v2.getX());
        String two = String.valueOf(v1.getY() + v2.getY());
        String three = String.valueOf(v1.getZ() + v2.getZ());
        return v1.setString(one, two, three);
    }
    
    /**
     * Метод, вычисляющий разность векторов
     * @param v1 Конструктор Vector
     * @param v2 Конструктор Vector
     * @return String координаты ветора
     */
    String difference(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getX() - v2.getX());
        String two = String.valueOf(v1.getY() - v2.getY());
        String three = String.valueOf(v1.getZ() - v2.getZ());
        return v1.setString(one, two, three);
    }
}
