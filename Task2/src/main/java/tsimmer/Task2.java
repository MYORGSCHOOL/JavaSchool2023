package tsimmer;


public class Task2 {
    public static void main(String[] args) {

        Vector[] vectors = Vector.arrayVectors(5);
        System.out.println("Длина вектора: " + vectors[0].length());
        System.out.println("Скалярное произведение: " + vectors[0].scalarProduct(vectors[1]));
        System.out.println("Векторное произведение: " + vectors[0].vectorProduct(vectors[1]).consoleOutput());
        System.out.println("Косинус угла: " + vectors[0].cosAngle(vectors[1]));
        System.out.println("Сумма: " + vectors[0].sumVectors(vectors[1]).consoleOutput());
        System.out.println("Разность: " + vectors[0].diffVectors(vectors[1]).consoleOutput());
        System.out.println("Массив векторов");
        for (int i = 0; i < 5; i++) {
            System.out.println(vectors[i].consoleOutput());
        }
    }
}
