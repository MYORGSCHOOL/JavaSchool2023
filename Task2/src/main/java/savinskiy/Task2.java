package savinskiy;

public class Task2 {
    public static void main(String[] args) {
        Vector vector1 = new Vector(18.0, 3.0, 93.0);
        Vector vector2 = new Vector(13.0, 4.0, 88.0);

        System.out.println("\n*** Length of vectors ***");
        double lengthVector1 = vector1.length();
        double lengthVector2 = vector2.length();
        System.out.println(String.format("Vector1 length = %.2f", lengthVector1));
        System.out.println(String.format("Vector1 length = %.2f", lengthVector2));

        System.out.println("\n*** Scalar product ***");

        double scalarProduct = vector1.scalarProduct(vector2);
        System.out.println(String.format("Scalar product of vector1 and vector 2 = %.2f", scalarProduct));

        System.out.println("\n*** Vector Product ***");

        Vector vectorProduct = vector1.vectorProduct(vector2);
        System.out.println(vectorProduct.toString());

        System.out.println("\n*** Cosines between two vectors ***");

        double cosVectors = vector1.cos(vector2);
        System.out.println(String.format("Cosines between vector1 and vector2 = %.2f", cosVectors));

        System.out.println("\n*** Vectors addition ***");

        Vector addVectors = vector1.add(vector2);
        System.out.println(addVectors.toString());

        System.out.println("\n*** Subtraction of vectors ***");

        Vector subtractVectors = vector1.subtract(vector2);
        System.out.println(subtractVectors.toString());

        System.out.println("\n*** Random Vectors ***");
        int num = 7;
        Vector vectors[] = Vector.randomVectorsArray(num);

        for (Vector vector : vectors) {
            System.out.println(vector);
        }
    }
}
