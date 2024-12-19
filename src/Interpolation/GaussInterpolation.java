package Interpolation;

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

        // Выбор центрального узла
        int midIndex = findClosestNode(argsX, ourX);
        System.out.println("Ближайший узел (x0): x[" + midIndex + "] = " + argsX[midIndex]);

        // Интерполяция для первой функции
        double res1 = interpolation(argsX, argsY1, ourX, midIndex);
        System.out.println("Результат интерполяции для y = 1 / x: " + res1);

        // Интерполяция для второй функции
        double res2 = interpolation(argsX, argsY2, ourX, midIndex);
        System.out.println("Результат интерполяции для y = x^4: " + res2);
    }

    // Метод нахождения ближайшего узла
    public static int findClosestNode(double[] argsX, double ourX) {
        int closestIndex = 0;
        double minDistance = Math.abs(ourX - argsX[0]);
        for (int i = 1; i < argsX.length; i++) {
            double distance = Math.abs(ourX - argsX[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    // Метод интерполяции
    public static double interpolation(double[] argsX, double[] argsY, double ourX, int midIndex) {
        int num = argsX.length; // Количество узлов
        double h = argsX[1] - argsX[0]; // Шаг узлов
        double qConst = (ourX - argsX[midIndex]) / h; // q
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

        // Вывод таблицы конечных разностей
        System.out.println("\nТаблица конечных разностей:");
        for (int i = 0; i < num; i++) {
            System.out.print("y[" + i + "]");
            for (int j = 0; j < num - i - 1; j++) {
                System.out.printf("\t%.5f", delY[j][i]);
            }
            System.out.println();
        }

        // Начальное значение интерполированной функции
        double ourY = argsY[midIndex];
        System.out.println("\nШаги интерполяции:");
        System.out.printf("Начальное значение: y[%d] = %.5f\n", midIndex, ourY);

        int iter = -1;
        int iter1 = -iter;
        ourY += qConst * delY[0][midIndex]; // Учет первой разности
        System.out.printf("Добавляем первую разность: %.5f * %.5f = %.5f\n", qConst, delY[0][midIndex], qConst * delY[0][midIndex]);

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

            // Проверка выхода индекса за пределы массива
            if (midIndex - nowIter < 0 || midIndex - nowIter >= num) {
                System.out.println("Индекс вышел за пределы таблицы. Пропускаем этот шаг.");
                continue;
            }

            double term = (q / factorial) * delY[g][midIndex - nowIter];
            System.out.printf("Добавляем %d-ю разность: %.5f * %.5f / %d! = %.5f\n", g + 1, q, delY[g][midIndex - nowIter], factorial, term);
            ourY += term;

            g++;
            factorial *= cur + 3;
        }
        return ourY;
    }

}
