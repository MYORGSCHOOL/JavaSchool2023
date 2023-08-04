package malakhova;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        Vector a = new Vector(2, 3, 4);
        Vector b = new Vector(5, 6, 7);
        System.out.println("Длина вектора a: " + a.lenghtVector());
        System.out.println("Длина вектора b: " + b.lenghtVector());
        System.out.println("Скалярное произведение векторов a и b: " + a.scalarProduct(b));
        Vector c = a.vectorProduct(b);
        System.out.println("Векторное произведение векторов a и b: (" + c.x + ", " + c.y + ", " + c.z + ")");
        System.out.println("Угол между векторами a и b: " + a.corner(b));
        Vector d = a.sum(b);
        System.out.println("Сумма векторов: (" + d.x + ", " + d.y + ", " + d.z + ")");
        Vector e = a.difference(b);
        System.out.println("Разность векторов: (" + e.x + ", " + e.y + ", " + e.z + ")");
        Random r = new Random(System.currentTimeMillis());
        int N = 1 + r.nextInt(10);
        System.out.println("Массив векторов: ");
        Vector[] vectors = Vector.returnArray(N);
        for (int i = 0; i < N; i++)
            System.out.println("(" + vectors[i].x + ", " + vectors[i].y + ", " + vectors[i].z + ")");
    }
}
