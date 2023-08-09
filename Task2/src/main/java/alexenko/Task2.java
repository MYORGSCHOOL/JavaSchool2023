package alexenko;


import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {

        Vector3D v1 = new Vector3D(1,1,1);
        Vector3D v2 = new Vector3D(2,2,2);

        System.out.println("Длина вектора v1: " + v1.getLength());
        System.out.println("Длина вектора v2: " + v2.getLength());

        System.out.println("Скалярное произведение (v1,v2): " + Vector3D.getScalar(v1,v2));

        System.out.println("Векторное произведение v1 x v2: " + Vector3D.getVector(v1,v2));

        System.out.println("Угол между векторами v1 и v2: " + Vector3D.getAngle(v1,v2));

        System.out.println("Сумма векторов v1+v2: " + Vector3D.getSum(v1,v2));

        System.out.println("Разность векторов v1-v2: " + Vector3D.getDifference(v1,v2));

        Scanner scanner = new Scanner(System.in);

        Integer n = scanner.nextInt();

        Vector3D[] vectors = Vector3D.getArray(n);

        System.out.println("Массив случайных векторов: \n");

        for (int i = 0; i < n; i++) {
            System.out.println(vectors[i]);
        }

    }
}
