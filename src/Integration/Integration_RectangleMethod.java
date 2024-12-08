package Integration;

public class Integration_RectangleMethod {

    public static void main(String[] args) {
        // Интервалы интегрирования
        double a1 = 1, b1 = 3;
        double a2 = 0, b2 = 1;

        // Число разбиений
        int n = 10; // Количество разбиений для первого расчета
        int n2 = 2 * n; // Увеличенное число разбиений для оценки погрешности

        // Вычисление интегралов методом прямоугольников
        double integral1_n = rectangleMethod(a1, b1, n, x -> (6 * x - 4));
        double integral1_n2 = rectangleMethod(a1, b1, n2, x -> (6 * x - 4));

        double integral2_n = rectangleMethod(a2, b2, n, x -> (1 / (x + 2)));
        double integral2_n2 = rectangleMethod(a2, b2, n2, x -> (1 / (x + 2)));

        // Точные значения интегралов
        double exact1 = exactIntegral1();
        double exact2 = exactIntegral2();

        // Оценка погрешности по правилу Рунге
        double error1 = rungeError(integral1_n, integral1_n2);
        double error2 = rungeError(integral2_n, integral2_n2);

        // Вывод результатов
        System.out.printf("Интеграл 1 (метод прямоугольников): %.6f%n", integral1_n);
        System.out.printf("Интеграл 1 (точное значение): %.6f%n", exact1);
        System.out.printf("Погрешность 1 по правилу Рунге: %.6f%n", error1);

        System.out.printf("Интеграл 2 (метод прямоугольников): %.6f%n", integral2_n);
        System.out.printf("Интеграл 2 (точное значение): %.6f%n", exact2);
        System.out.printf("Погрешность 2 по правилу Рунге: %.6f%n", error2);
    }

    // Метод прямоугольников
    public static double rectangleMethod(double a, double b, int n, Function func) {
        double h = (b - a) / n; // Шаг разбиения
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            double x = a + (i + 0.5) * h; // Точка в центре прямоугольника
            sum += func.calculate(x);
        }

        return sum * h;
    }

    // Точные значения интегралов
    public static double exactIntegral1() {
        // ∫ (6x - 4) dx от 1 до 3
        return (3 * 3 * 3 - 4 * 3) - (3 * 1 * 1 - 4 * 1);
    }

    public static double exactIntegral2() {
        // ∫ (1 / (x + 2)) dx от 0 до 1
        return Math.log(3) - Math.log(2);
    }

    // Оценка погрешности по правилу Рунге
    public static double rungeError(double integral_n, double integral_n2) {
        return Math.abs(integral_n2 - integral_n) / 3.0; // k = 2 для метода прямоугольников
    }

    // Функциональный интерфейс для передачи функций
    interface Function {
        double calculate(double x);
    }
}
