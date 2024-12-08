package mini;

import java.util.ArrayList;

public class NumericalDifferentiation {
    public static void main(String[] args) {
        // Задаем сетку значений x
        double[] x = {0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2}; // Пример значений x
        double h = x[1] - x[0]; // Шаг сетки

        // Вычисляем значения y = sin(x)
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = Math.sin(x[i]);
        }

        // Вычисление производных по различным формулам
        System.out.println("Правая разностная производная:");
        for (int i = 0; i < x.length - 1; i++) {
            double derivative = forwardDifference(y, i, h);
            System.out.printf("f'(%f) ≈ %f%n", x[i], derivative);
        }

        System.out.println("\nЛевая разностная производная:");
        for (int i = 1; i < x.length; i++) {
            double derivative = backwardDifference(y, i, h);
            System.out.printf("f'(%f) ≈ %f%n", x[i], derivative);
        }

        System.out.println("\nЦентральная разностная производная:");
        for (int i = 1; i < x.length - 1; i++) {
            double derivative = centralDifference(y, i, h);
            System.out.printf("f'(%f) ≈ %f%n", x[i], derivative);
        }

        System.out.println("\nВторая разностная производная:");
        for (int i = 1; i < x.length - 1; i++) {
            double secondDerivative = secondDifference(y, i, h);
            System.out.printf("f''(%f) ≈ %f%n", x[i], secondDerivative);
        }
    }

    // Правая разностная производная
    public static double forwardDifference(double[] y, int i, double h) {
        return (y[i + 1] - y[i]) / h;
    }

    // Левая разностная производная
    public static double backwardDifference(double[] y, int i, double h) {
        return (y[i] - y[i - 1]) / h;
    }

    // Центральная разностная производная
    public static double centralDifference(double[] y, int i, double h) {
        return (y[i + 1] - y[i - 1]) / (2 * h);
    }

    // Вторая разностная производная
    public static double secondDifference(double[] y, int i, double h) {
        return (y[i + 1] - 2 * y[i] + y[i - 1]) / (h * h);
    }
}