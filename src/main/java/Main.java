import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] inter = new double[2];
        IOService service = new IOService();
            System.out.println("""
                Выберите одно из уравнений:
                1. x^3 - 0.2x^2 + 0.5x + 1.5
                2. sqrt(x) - 0.5x
                3. x - cos(x)
                4. sqrt(x+3) + (3x-2)^(1/3) - 3
                """);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int function = Integer.parseInt(reader.readLine());
            System.out.println("Каким методом будем решать?");
        System.out.println("""
                1. Прямоугольники (Левый)
                2. Прямоугольники (Правый)
                3. Прямоугольники (Центр)
                4. Трапеции
                5. Симпсон""");
        int method = Integer.parseInt(reader.readLine());
        System.out.println("Сколько знаков после запятой оставлять?");
        int signs = Integer.parseInt(reader.readLine());
        double epsilon = Math.pow(0.1, signs);
        System.out.println("Введите интервал (сначала одно число, потом второе)");
        inter[0] = Double.parseDouble(reader.readLine());
        inter[1] = Double.parseDouble(reader.readLine());
        service.readFromConsole(function, method, inter, epsilon, signs);
        System.out.println("Продолжить выполнение программы? (Y/N)");
        if(reader.readLine().equals("Y")) {
            main(null);
        }
    }
}