package grossu;


/**
 * Класс для работы с классом Vector
 */
public class Task2 {

    public static void main(String[] args) {
        Vector vector1 = new Vector(1.0d, 2.0d, -5.0d);
        Vector vector2 = new Vector(4.0d, 8.0d, 1.0d);
        System.out.println("Координаты вектора vector1: " + vector1);
        System.out.println("Длина вектора vector1: " + vector1.getVectorLength());
        System.out.println("Скалярное произведение веторов (vector1,vector2): " + vector1.getScalarProduct(vector2));
        System.out.println("Векторное произведение веторов [vector1,vector2]: " + vector1.getVectorProduct(vector2));
        System.out.println("Косинус угла между векторами vector1 и vector2: " + vector1.getCosAngle(vector2));
        System.out.println("Сумма векторов vector1 и vector2: " + vector1.getSumVector(vector2));
        System.out.println("Разность векторов vector1 и vector2: " + vector1.getDiffVector(vector2));
        System.out.println("Генерация массива векторов размером 5:");
        Vector[] vectors = Vector.getRandomVectorArray(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(vectors[i]);
        }

    }
}
