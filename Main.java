import java.util.Scanner;

public class Main {
    static String[] operands = {"+", "-", "*", "/"};
    static int x1 = 0;
    static int x2 = 0;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = console.nextLine();
        solve(expression);
    }

    public static void solve(String expr) {
        String str = expr.replaceAll(" ", "");
        int index = -1;
        for (int i = 0; i < operands.length; i++) {
            if (str.contains(operands[i])) {
                index = i;
                break;
            }
        }

        if (index == -1) throw new RuntimeException("Исключение: нет операнда в строке");

        String a, b;
        String[] array = str.split("[+-/*]");
        if (array.length > 2) {
            throw new RuntimeException("Исключение: недопустимое выражение");
        }
        a = array[0];
        b = array[1];


        if (a.matches("\\d*") && b.matches("\\d*")) {
            x1 = Integer.parseInt(a);
            x2 = Integer.parseInt(b);
            if ((x1 > 0 && x1 < 11) && (x2 > 0 && x2 < 11)) {
                int solution = calculate(x1, x2, operands[index]);
                System.out.println(solution);
            } else
                throw new RuntimeException("Исключение: числа не входят в диапазон от 1 до 10");
        } else if (a.matches("\\D*") && b.matches("\\D*")) {
            x1 = toArabic(a);
            x2 = toArabic(b);
            if ((index ==1) && (x1 <= x2))
                throw new RuntimeException("Исключение: римские числа не могут быть меньше единицы");
            if (x1 > 0 && x1 < 11 && x2 > 0) {
                int solution = calculate(x1, x2, operands[index]);
                System.out.println(toRoman(solution));
            } else
                throw new RuntimeException("Исключение: числа не входят в диапазон от 1 до 10");
        } else
            throw new

                    RuntimeException("Исключение: в выражении числа разного типа");

    }
    public static int calculate(int a, int b, String operand) {
        return switch (operand) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new RuntimeException("Исключение: недопустимый оператор");
        };
    }
    public static int toArabic(String roman) {
        String[] letters = {"XC", "C", "XL", "L", "IX", "X", "IV", "V", "I"};
        int[] numbers = {90, 100, 40, 50, 9, 10, 4, 5, 1};
        int result = 0;
        int index;
        String str = roman.toUpperCase();
        for (int i = 0; i < letters.length; i++) {
            index = str.indexOf(letters[i]);
            if (index >= 0) {
                result += numbers[i];
                str = str.replaceFirst(letters[i], "");
                i--;
            }
        }
        return result;
    }

    public static String toRoman(int arabic) {
        String[] letters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int number = arabic;
        int quotient;
        for (int i = 0; i < letters.length; i++) {
            quotient = number / numbers[i];
            if (quotient > 0) {
                result.append(letters[i]);
                number -= numbers[i];
                i--;
            }
        }
        return result.toString();
    }
}