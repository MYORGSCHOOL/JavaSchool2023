package proskurina;

public class Task2 {
    public static void main(String[] args) {
        Vector vector1 = new Vector(1.0d,1.0d,2.0d);
        Vector vector2 = new Vector(2.0d,1.0d,2.0d);
        
        System.out.println("Длина вектора " + vector1.length());
        System.out.println("Скалярное произведение " + vector1.scalarProduct(vector2));
        System.out.println("Векторное произведение " + vector1.vectorProduct(vector2));
        System.out.println("Косинус угла " + vector1.angle(vector2));
        System.out.println("Разность " + vector1.minus(vector2));
        System.out.println("Сумма " + vector1.plus(vector2));
        System.out.println("Случайный вектор " + Vector.getRandomVectors(1)[0]);
    }
}