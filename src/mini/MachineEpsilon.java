package mini;

import java.math.BigDecimal;
import java.math.MathContext;

public class MachineEpsilon {
    public static void main(String[] args) {
        // Машинный эпсилон для float
        float floatEpsilon = calculateEpsilonFloat();
        System.out.println("Машинное эпсилон для float: " + floatEpsilon);

        // Машинный эпсилон для double
        double doubleEpsilon = calculateEpsilonDouble();
        System.out.println("Машинное эпсилон для double: " + doubleEpsilon);

        // "Машинное эпсилон" с использованием BigDecimal (произвольная точность)
        BigDecimal bigDecimalEpsilon = calculateEpsilonBigDecimal();
        System.out.println("Эпсилон для BigDecimal: " + bigDecimalEpsilon);
    }

    // Метод для вычисления машинного эпсилон для float
    public static float calculateEpsilonFloat() {
        float epsilon = 1.0f;
        while ((1.0f + (epsilon / 2.0f)) != 1.0f) {
            epsilon /= 2.0f;
        }
        return epsilon;
    }

    // Метод для вычисления машинного эпсилон для double
    public static double calculateEpsilonDouble() {
        double epsilon = 1.0;
        while ((1.0 + (epsilon / 2.0)) != 1.0) {
            epsilon /= 2.0;
        }
        return epsilon;
    }

    // Метод для вычисления "эпсилон" с использованием BigDecimal
    public static BigDecimal calculateEpsilonBigDecimal() {
        BigDecimal one = BigDecimal.ONE;
        BigDecimal epsilon = BigDecimal.ONE;
        MathContext mc = new MathContext(50); // Устанавливаем 50 знаков точности
        BigDecimal minThreshold = BigDecimal.valueOf(1E-128); // Минимальный порог, чтобы остановиться

        while (one.add(epsilon.divide(BigDecimal.valueOf(2), mc)).compareTo(one) != 0) {
            epsilon = epsilon.divide(BigDecimal.valueOf(2), mc);
            //Проверяем, если epsilon стал меньше порога, то выходим
            if (epsilon.compareTo(minThreshold) < 0) {
                break;
            }
        }
        return epsilon;
    }
}
