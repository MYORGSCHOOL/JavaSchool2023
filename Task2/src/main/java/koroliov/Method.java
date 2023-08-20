package koroliov;

public class Method {
    // [x]: Метод, вычисляющий длину вектора. Корень можно посчитать с помощью Math.sqrt()
    // NOTE: SQRT(X^2 + Y^2 + Z^2)
    double lengthVector(Vector vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

    // [x]: Метод, вычисляющий скалярное произведение
    // NOTE: X1 * X2 + Y1 * Y2 + Z1 * Z2;
    double scalarProduct(Vector v1, Vector v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    // [x]: Метод, вычисляющий векторное произведение с другим вектором
    // NOTE: Y1 * Z2 - Z1 * Y2; Z1 * X2 - X1 * Z2; X1 * Y2 - Y1 * X2;
    String vectorProduct(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getY() * v2.getZ() - v1.getZ() * v2.getY());
        String two = String.valueOf(v1.getZ() * v2.getX() - v1.getX() * v2.getZ());
        String three = String.valueOf(v1.getX() * v2.getY() - v1.getY() * v2.getX());
        String result = v1.setString(one, two, three);
        return result;
    }
    
    // [x]: Метод, вычисляющий угол между векторами (или косинус угла)
    // NOTE: Scalar / |length(a)| * |length(b)|
    double angleVectors(Vector v1, Vector v2) {
        return scalarProduct(v1, v2) / (Math.abs(lengthVector(v1)) * Math.abs(lengthVector(v2)));
    }

    // [x]: Методы для суммы и разности
    // TODO: Сумма
    String amount(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getX() + v2.getX());
        String two = String.valueOf(v1.getY() + v2.getY());
        String three = String.valueOf(v1.getZ() + v2.getZ());
        return v1.setString(one, two, three);
    }
    
    // TODO: Разность
    String difference(Vector v1, Vector v2) {
        String one = String.valueOf(v1.getX() - v2.getX());
        String two = String.valueOf(v1.getY() - v2.getY());
        String three = String.valueOf(v1.getZ() - v2.getZ());
        return v1.setString(one, two, three);
    }
}
