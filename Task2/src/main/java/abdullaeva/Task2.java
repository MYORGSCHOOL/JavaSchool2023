package abdullaeva;

public class Task2 {
    public static void main(String[] args) {
        Vector vector = new Vector(0.88, 1.13, 1.02);
        Vector vector1 = new Vector(0.12, 1, 0.15);
        System.out.println("Длина исходного вектора vector равна " + vector.getLength());
        System.out.println("Скалярное произведение векторов vector и vector1 равно " + vector.getScalar(vector1));
        System.out.println("Векторное произведение векторов vector и vector1 равно вектору " + vector.getProduct(vector1));
        System.out.println("Угол между векторами vector и vector1 равен " + vector.getAngleBetween(vector1));
        System.out.println("Сумма векторов vector и vector1 равна вектору " + vector.getSum(vector1));
        System.out.println("Разность векторов vector и vector1 равна вектору " + vector.getDiff(vector1));

        Vector[] vectors = Vector.getRandArrOfVectors(4);
        System.out.println("Массив случайных векторов с заданной размерностью создан.");
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + " Случайный вектор : " + vectors[i] + " с координатами x: " + vectors[i].getX() + " y: " + vectors[i].getY() + " z: " + vectors[i].getZ());
        }
    }
}
