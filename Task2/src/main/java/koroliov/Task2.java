package koroliov;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static Method met = new Method();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector vector1 = new Vector();
        Vector vector2 = new Vector();
        List<Vector> vectors = new ArrayList<>();
        vectors.add(vector1);
        vectors.add(vector2);
        String result;
        int choice = 0;
        int n = 0;

        while(true) {
            System.out.println("Выберете операцию: ");
            System.out.println("1. Длинна вектора");
            System.out.println("2. Скалярное произвежение векторов");
            System.out.println("3. Вычислить векторное произведение");
            System.out.println("4. Вычислить угол межде векторами");
            System.out.println("5. Вычислить сумму векторов");
            System.out.println("6. Вычислить разность векторов");
            System.out.println("7. Сгенерировать векторы");
            System.out.println("8. Выход");
            System.out.print(">> ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                // TODO: Длинна вектора
                    vector1.input(vectors.get(0), in);
                    
                    result = String.format("%.3f", met.lengthVector(vector1));
                    System.out.println("Длинна вектора = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 2:
                // TODO: Скалярное произведение веткторов
                    vector1.input(vectors.get(0), vectors.get(1), in);

                    result = String.format("%.3f", met.scalarProduct(vector1, vector2));
                    System.out.println("Скалярное произведение векторов = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 3:
                // TODO: Векторное произведение
                    vector1.input(vectors.get(0), vectors.get(1), in);

                    System.out.println("Векторное произведение = " + met.vectorProduct(vector1, vector2));
                    break;
                
                case 4:
                // TODO: Угол между векторами
                    vector1.input(vectors.get(0), vectors.get(1), in);

                    result = String.format("%.3f", met.angleVectors(vector1, vector2));
                    System.out.println("Угол между векторами = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 5:
                // TODO: Сумма векторов
                    vector1.input(vectors.get(0), vectors.get(1), in);

                    System.out.println("Сумма векторов = " + met.amount(vector1, vector2));
                    System.out.printf("\n\n");
                    break;
                
                case 6:
                // TODO: Разность
                    vector1.input(vectors.get(0), vectors.get(1), in);

                    System.out.println("Разность векторов = " + met.difference(vector1, vector2));
                    System.out.printf("\n\n");
                    break;
                
                case 7:
                    System.out.print("Введите кол-во векторов: ");
                    n = in.nextInt();
                    List<Vector> generVercList = vector1.setVectors(n);

                    System.out.println("Полученные вектора: ");
                    for (Vector i : generVercList) System.out.println(i.setString(i));
                    System.out.printf("\n\n");
                    break;
                case 8:
                    System.out.println("Закрытие....");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный ввод!!!");
                    break;
            }
        }
    }
}
