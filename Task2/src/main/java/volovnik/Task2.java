package volovnik;

public class Task2 {

    public static void main(String[] args) {
        Vector v1 = new Vector(-2, 0, 5);
        double v1Length = v1.calcLength();
        System.out.println("Длина вектора v1 = " + v1Length);

        Vector v2 = new Vector(4, 2, 1);
        double scalar = v1.calcScalar(v2);
        System.out.println("Скалярное произведение векторов v1 и v2 = " + scalar);

        Vector vectorMultiply = v1.calcVectorMultiply(v2);
        System.out.printf("Векторное произведение v1 и v2 = {%.2f, %.2f, %.2f}\n",
                vectorMultiply.getX(), vectorMultiply.getY(), vectorMultiply.getZ());

        double angle = v1.calcAngle(v2);
        System.out.println("Угол между v1 и v2 = " + angle);

        Vector sum = v1.calcSum(v2);
        System.out.printf("Сумма векторов v1 и v2 = {%.2f, %.2f, %.2f}\n", sum.getX(), sum.getY(), sum.getZ());

        Vector sub = v1.calcSubtract(v2);
        System.out.printf("Разность векторов v1 и v2 = {%.2f, %.2f, %.2f}\n", sub.getX(), sub.getY(), sub.getZ());

        Vector[] vectors = Vector.getRandomVectors(50, 30);
        System.out.println("50 случайных векторов:");
        for (Vector vector : vectors) {
            System.out.printf("{%.2f, %.2f, %.2f}%n", vector.getX(), vector.getY(), vector.getZ());
        }
    }
}
