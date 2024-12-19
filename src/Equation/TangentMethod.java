package Equation;

public class TangentMethod {
    // Функция f(x) = x^4 + x - 4
    public static double f(double x) {
        return Math.pow(x, 4) + x - 4;
    }

    // Производная f'(x) = 4x^3 + 1
    public static double fDerivative(double x) {
        return 4 * Math.pow(x, 3) + 1;
    }

    public static void main(String[] args) {
        double x0 = 1.0; // Начальная точка как середина отрезка [0, 2]
        double x = x0; // Начальное приближение
        double tolerance = 0.001; // Точность
        int maxIterations = 100; // Максимальное количество итераций

        double a = 0;
        double b = 2;
        double m = Math.min(fDerivative(a), fDerivative(b));
        // Флаг, который определяет использование модифицированного метода или обычного
        boolean useModifiedMethod = true;

        int iteration = 0;


        System.out.println("Итерация\tЗначение x\tЗначение f(x)");

        do {

            System.out.printf("%d\t\t%.6f\t%.6f\n", iteration, x, f(x)); // Выводим текущую информацию

            // Обычный метод касательных (с xi в знаменателе)
            if (!useModifiedMethod) {
                x = x - f(x) / fDerivative(x);
            } else {
                // Модифицированный метод (с x0 в знаменателе)
                x = x - f(x) / fDerivative(x0);
            }

            iteration++;
        } while (Math.abs(f(x)) > m*tolerance && iteration < maxIterations);

        // Результат
        if (Math.abs(f(x)) <= m*tolerance) {
            System.out.printf("Корень найден: %.6f\n", x); // Если разница меньше точности, выводим результат
            System.out.printf("Подставляем найденное значение в уравнение f(x): f(%.6f) = %.10f\n", x, f(x));
        } else {
            System.out.println("Метод не сошёлся за " + maxIterations + " итераций.");
        }
    }
}
