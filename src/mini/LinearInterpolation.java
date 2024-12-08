package mini;

import java.util.Scanner;

public class LinearInterpolation {
    // Метод для линейной интерполяции
    public static double linearInterpolation(double x0, double y0, double x1, double y1, double x) {
        return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество известных точек: ");
        int size = scanner.nextInt();

        double[] xArr = new double[size];
        double[] yArr = new double[size];

        System.out.println("Введите координаты x и y:");
        for (int i = 0; i < size; i++) {
            System.out.print("x[" + i + "] = ");
            xArr[i] = scanner.nextDouble();
            System.out.print("y[" + i + "] = ");
            yArr[i] = scanner.nextDouble();
        }

        System.out.print("Введите значение x, для которого нужно найти y: ");
        double x = scanner.nextDouble();

        // Проверяем, в каком интервале находится x
        for (int i = 0; i < size - 1; i++) {
            if (x >= xArr[i] && x <= xArr[i + 1]) {
                // Выполняем линейную интерполяцию
                double y = linearInterpolation(xArr[i], yArr[i], xArr[i + 1], yArr[i + 1], x);
                System.out.println("Значение y для заданного x = " + x + " равно: " + y);
                break;
            }
        }

        scanner.close();
    }
}