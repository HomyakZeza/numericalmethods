package SLAE;

import java.util.Scanner;

public class MatrixDeterminant {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите размерность матрицы (например, 3 для 3x3):");
        int n = scanner.nextInt();
        double[][] matrix = new double[n][n];

        System.out.println("Введите элементы матрицы построчно:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        double determinant = calculateDeterminant(matrix);
        System.out.printf("Определитель матрицы: %.5f%n", determinant);
    }

    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        int swapCount = 0; // Счётчик перестановок строк

        for (int i = 0; i < n; i++) {
            // Поиск главного элемента по модулю в текущем столбце
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Если главный элемент не в текущей строке, меняем строки местами
            if (maxRow != i) {
                double[] temp = matrix[i];
                matrix[i] = matrix[maxRow];
                matrix[maxRow] = temp;
                swapCount++; // Увеличиваем счётчик перестановок
            }

            // Если диагональный элемент равен 0, определитель равен 0
            if (Math.abs(matrix[i][i]) < 1e-10) {
                return 0;
            }

            // Вывод текущего состояния матрицы после перестановки
            System.out.println("Матрица после перестановки строк (шаг " + (i + 1) + "):");
            printMatrix(matrix);

            // Приведение к верхнетреугольной форме
            for (int k = i + 1; k < n; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                for (int j = i; j < n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }

            // Вывод матрицы после обнуления столбца
            System.out.println("Матрица после обнуления столбца " + (i + 1) + ":");
            printMatrix(matrix);
        }

        // Вычисление определителя как произведения диагональных элементов
        double determinant = 1.0;
        for (int i = 0; i < n; i++) {
            determinant *= matrix[i][i];
        }

        // Учёт знака определителя в зависимости от количества перестановок
        if (swapCount % 2 != 0) {
            determinant *= -1;
        }

        return determinant;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%10.5f ", value);
            }
            System.out.println();
        }
        System.out.println();
    }

}
