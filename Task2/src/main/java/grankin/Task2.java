package grankin;

public class Task2 {

    public static void main(String[] args) {

        VectorXYZ vector1 = new VectorXYZ(1, 2, 3);
        VectorXYZ vector2 = new VectorXYZ(4, 5, 6);

        System.out.println("Вектор 1: (" + vector1.getX() + ", " + vector1.getY() + ", " + vector1.getZ() + ")");
        System.out.println("Вектор 2: (" + vector2.getX() + ", " + vector2.getY() + ", " + vector2.getZ() + ")");

        System.out.println("Длина вектора 1: " + vector1.length());
        System.out.println("Длина вектора 2: " + vector2.length());

        System.out.println("Скалярное произведение векторов: " + vector1.scalarMult(vector2));

        VectorXYZ vectorMult = vector1.vectorMult(vector2);
        System.out.println("Векторное произведение векторов: (" + vectorMult.getX() + ", " +
                                                                  vectorMult.getY() + ", " +
                                                                  vectorMult.getZ() + ")");

        System.out.println("Косинус между векторами: " + vector1.cos(vector2));

        VectorXYZ summaVectorov = vector1.summa(vector2);
        System.out.println("Сумма векторов: (" + summaVectorov.getX() + ", " +
                                                 summaVectorov.getY() + ", " +
                                                 summaVectorov.getZ() + ")");

        VectorXYZ raznostVectorov = vector1.raznost(vector2);
        System.out.println("Разность векторов: (" + raznostVectorov.getX() + ", " +
                                                 raznostVectorov.getY() + ", " +
                                                 raznostVectorov.getZ() + ")");

        VectorXYZ[] arrayVectors = VectorXYZ.generateRandomArr(10);
        System.out.println("\nСоздание массива случайных векторв из 10 элементов:");
        for (VectorXYZ oneVector: arrayVectors)
        {
            System.out.println("Вектор : (" + oneVector.getX() + ", " + oneVector.getY() + ", " + oneVector.getZ() + ")");
        }
    }
}
