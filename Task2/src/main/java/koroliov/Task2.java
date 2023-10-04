package koroliov;

import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String result = "";
        int choice = 0;
        int n = 0;
        double arr[] = new double[6];
        System.out.println("Введите координаты 1-го вектора:");
        for (int i = 0; i < 3; i++) {
            System.out.print(":>> ");
            arr[i] = in.nextDouble();
        }
        System.out.println("Введите координаты 2-го вектора:");
        for (int i = 3; i < 6; i++) {
            System.out.print(":>> ");
            arr[i] = in.nextDouble();
        }
        Vector v1 = new Vector(arr[0], arr[1], arr[2]);
        Vector v2 = new Vector(arr[3], arr[4], arr[5]);

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
                    System.out.print("Выберите вектор:>> ");
                    int vChoice = in.nextInt();

                    if (vChoice == 1) result = String.format("%.3f", v1.lengthVector(v1));
                    if (vChoice == 2) result = String.format("%.3f", v1.lengthVector(v2));

                    System.out.println("Длинна вектора = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 2:
                    result = String.format("%.3f", v1.scalarProduct(v1, v2));
                    System.out.println("Скалярное произведение векторов = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 3:
                    System.out.println("Векторное произведение = " + v1.getString(v1.vectorProduct(v1, v2)));
                    System.out.printf("\n\n");
                    break;
                
                case 4:
                    result = String.format("%.3f", v1.angleVectors(v1, v2));
                    System.out.println("Угол между векторами = " + result);
                    System.out.printf("\n\n");
                    break;
                
                case 5:
                    System.out.println("Сумма векторов = " + v1.amount(v1, v2));
                    System.out.printf("\n\n");
                    break;
                
                case 6:
                    System.out.println("Разность векторов = " + v1.getString(v1.difference(v1, v2)));
                    System.out.printf("\n\n");
                    break;
                
                case 7:
                    System.out.print("Введите кол-во векторов: ");
                    n = in.nextInt();
                    List<Vector> generVercList = v1.getVectors(n);

                    System.out.println("Полученные вектора: ");
                    for (Vector i : generVercList) System.out.println(i.getString(i));
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
