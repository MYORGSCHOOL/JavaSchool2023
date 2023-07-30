package yanko;

import java.util.Random;

public class Task2 {
    final double x;
    final double y;
    final double z;

    public Task2(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * вычисляет длину вектора
     * @return длину вектора
     */
    public Double getVectorLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * вычисляет скалярное произведение двух векторов
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return скалярное произведение
     */
    public static Double getVectorScalarMult(Task2 vector1, Task2 vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z;
    }

    /**
     * вычисляет вектороное произведение двух векторов
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return новый вектор (векторное произведение)
     */
    public static Task2 getVectorVectorMult(Task2 vector1, Task2 vector2) {
        return new Task2(vector1.y * vector2.z - vector1.z * vector2.y,
                vector1.z * vector2.x - vector1.x * vector2.z,
                vector1.x * vector2.y - vector1.y * vector2.x);
    }

    /**
     * вычисляет угол между двумя векторами
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return угол между векторами (косинус)
     */
    public static Double getVectorAngle(Task2 vector1, Task2 vector2) {
        return getVectorScalarMult(vector1, vector2) / (vector1.getVectorLength() * vector2.getVectorLength());
    }

    /**
     * вычисляет сумму двух векторов
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return новый вектор (сумма векторов)
     */
    public static Task2 getVectorSum(Task2 vector1, Task2 vector2) {
        return new Task2(vector1.x + vector2.x, vector1.y + vector2.y, vector1.z + vector2.z);
    }

    /**
     * вычисляет разность двух векторов
     * @param vector1 первый вектор
     * @param vector2 второй вектор
     * @return новый вектор (разность векторов)
     */
    public static Task2 getVectorDiff(Task2 vector1, Task2 vector2) {
        return new Task2(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
    }

    /**
     * возвращает массив случайных n векторов
     * @param n количество векторов в массиве
     * @return массив случайных векторов
     */
    public static Task2[] getVectorRandMass(Integer n) {
        Task2 vector[] = new Task2[n];
        Double start = 0.0;
        Double end = 100.0;
        for (int i = 0; i < n; i++) {
            Task2 vector1 = new Task2(start + (new Random().nextDouble() * (end - start)),
                    start + (new Random().nextDouble() * (end - start)),
                    start + (new Random().nextDouble() * (end - start)));
            vector[i] = vector1;
        }
        return vector;
    }

    /**
     * выводит координаты вектора в консоль
     */
    public void printVector() {
        System.out.println(this.x + " " + this.y + " " + this.z);
    }

    public static void main(String[] args) {
        Task2 vector1 = new Task2(8.0, 4.0, 1.0);
        Task2 vector2 = new Task2(2.0, 3.0, 7.0);

        //длина вектора vector1
        System.out.println("\nvector1 length " + vector1.getVectorLength());

        //скалярное произведение vector1 и vector2
        System.out.println("\nvector1 * vector2 scalar " + Task2.getVectorScalarMult(vector1, vector2));

        //вектороное произведение vector1 и vector2 (vector3)
        Task2 vector3 = Task2.getVectorVectorMult(vector1, vector2);
        System.out.println("\nvector1 * vector2 vector ");
        vector3.printVector();

        //угол между vector1 и vector2
        System.out.println("\nvector1 and vector2 angle " + Task2.getVectorAngle(vector1, vector2));

        //сумма векторов vector1 и vector2 (vector4)
        Task2 vector4 = Task2.getVectorSum(vector1, vector2);
        System.out.println("\nvector1 + vector2 ");
        vector4.printVector();

        //разность векторов vector1 и vector2
        Task2 vector5 = Task2.getVectorDiff(vector1, vector2);
        System.out.println("\nvector1 - vector2 ");
        vector5.printVector();

        //массив случайных векторов vector[]
        Task2 vector[] = Task2.getVectorRandMass(3);
        for (int i = 0; i < 3; i++) {
            vector[i].printVector();
        }
    }
}
