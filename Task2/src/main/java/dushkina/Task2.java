package dushkina;

public class Task2 {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3.0d, -1.0d, 2.0d);
        System.out.println("Координаты vector1: " + vector1.coordinateOutput());
        System.out.println("Длина vector1 = " + vector1.calculatingTheLength());

        Vector vector2 = new Vector(1.5d, 3.0d, 6.0d);
        System.out.println("Координаты vector2: " + vector2.coordinateOutput());
        System.out.println("Длина vector2 = " + vector2.calculatingTheLength());

        System.out.println("Скалярное произведение vector1 и vector2 = " + vector1.calculatingTheScalarProduct(vector2));
        Vector vector3 = vector1.calculatingTheVectorProduct(vector2);
        System.out.println("Векторное произведение vector1 и vector2 = " + vector3.coordinateOutput());

        System.out.println("Угол между vector1 и vector2 = " + vector1.calculatingTheAngleBetweenVectors(vector2));

        vector3 = vector1.calculatingTheSumOfVectors(vector2);
        System.out.println("Сумма vector1 и vector2 :  " + vector3.coordinateOutput());
        vector3 = vector1.calculatingTheDifferenceOfVectors(vector2);
        System.out.println("Разность vector1 и vector2 : " + vector3.coordinateOutput());

        int size = 10;
        System.out.println("Массив случайных векторов размером = " + size + ":");
        Vector[] vectorArray = Vector.getVectorArray(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Координаты вектора[" + i + "]: " + vectorArray[i].coordinateOutput());
        }
    }
}
