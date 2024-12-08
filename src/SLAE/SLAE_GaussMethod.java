package SLAE;


import java.util.Scanner;

public class SLAE_GaussMethod {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод матрицы размерности 3x3
        double[][] matrix = new double[3][3];
        System.out.println("Введите элементы матрицы 3x3 построчно:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        // Вычисление определителя методом Гаусса
        double determinant = calculateDeterminant(matrix);

        // Вывод результата с повышенной точностью
        System.out.printf("Определитель матрицы: %.6f\n",  determinant);
    }

    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        double det = 1.0;

        // Прямой ход метода Гаусса
        for (int k = 0; k < n - 1; k++) {
            // Выбор главного элемента по столбцу
            int maxRow = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(matrix[i][k]) > Math.abs(matrix[maxRow][k])) {
                    maxRow = i;
                }
            }

            // Перестановка строк
            if (maxRow != k) {
                double[] temp = matrix[k];
                matrix[k] = matrix[maxRow];
                matrix[maxRow] = temp;
                det *= -1; // Учитываем изменение знака определителя
            }

            // Проверка на нулевой главный элемент
            if (Math.abs(matrix[k][k]) < 1e-9) {
                return 0.0; // Если элемент слишком мал, определитель равен 0
            }

            // Обнуление элементов ниже главного
            for (int i = k + 1; i < n; i++) {
                double factor = matrix[i][k] / matrix[k][k];
                for (int j = k; j < n; j++) {
                    matrix[i][j] -= factor * matrix[k][j];
                }
            }
        }

        // Вычисление определителя
        for (int i = 0; i < n; i++) {
            det *= matrix[i][i];
        }

        return det;
    }
}
