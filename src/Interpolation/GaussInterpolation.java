package Interpolation;

import java.lang.reflect.Array;
import java.util.Scanner;

public class GaussInterpolation {

    public static void main(String[] args) {
        // Узлы из примера
        double[] argsX = {2, 4, 6, 8, 10};

        // Первая функция: y = 1 / x
        double[] argsY1 = new double[argsX.length];
        for (int i = 0; i < argsX.length; i++) {
            argsY1[i] = 1 / argsX[i];
        }

        // Вторая функция: y = x^4
        double[] argsY2 = new double[argsX.length];
        for (int i = 0; i < argsX.length; i++) {
            argsY2[i] = Math.pow(argsX[i], 4);
        }

        // Точка интерполяции
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение x для интерполяции: ");
        double ourX = scanner.nextDouble();

        // Интерполяция для первой функции
        double res1 = interpolation(argsX, argsY1, ourX);
        System.out.println("Результат интерполяции для y = 1 / x: " + res1);

        // Интерполяция для второй функции
        double res2 = interpolation(argsX, argsY2, ourX);
        System.out.println("Результат интерполяции для y = x^4: " + res2);
    }

    // Метод интерполяции
    public static double interpolation(double[] argsX, double[] argsY, double ourX) {
        int num = Array.getLength(argsX); // Количество узлов
        double h = argsX[1] - argsX[0]; // Шаг узлов
        int midIndex = num / 2; // Индекс центрального узла
        double qConst = (ourX - argsX[midIndex]) / h; // q для центрального узла
        double q = qConst; // Текущее значение q
        double[][] delY = new double[num][num]; // Таблица конечных разностей

        // Вычисление разностей первого порядка
        for (int j = 0; j < num - 1; j++) {
            delY[0][j] = argsY[j + 1] - argsY[j];
        }

        // Вычисление разностей более высокого порядка
        for (int i = 1; i < num - 1; i++) {
            for (int j = 0; j < num - i - 1; j++) {
                delY[i][j] = delY[i - 1][j + 1] - delY[i - 1][j];
            }
        }

        // Начальное значение интерполированной функции
        double ourY = argsY[midIndex];
        int iter = -1;
        int iter1 = -iter;
        ourY += qConst * delY[0][midIndex]; // Учет первой разности
        int g = 1;
        int factorial = 2;

        // Цикл для добавления более высоких порядков
        for (int cur = 0; cur < num - 2; cur++) {
            int nowIter = Math.abs(Math.min(iter1, Math.abs(iter)));
            if (iter1 < Math.abs(iter)) {
                q *= (qConst + Math.abs(Math.min(iter1, Math.abs(iter))));
                iter1++;
            } else {
                q *= (qConst - Math.abs(Math.min(iter1, Math.abs(iter))));
                iter--;
            }
            ourY += (q / factorial) * delY[g][midIndex - nowIter];
            g++;
            factorial *= cur + 3;
        }
        return ourY;
    }
}
