import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException {
        double close = 0;
        double[] inter = new double[2];
        CountTask task = new CountTask();
        task.interval();
//        IOService service = new IOService();
//            System.out.println("""
//                Выберите одно из уравнений:
//                1. x^3 - 0.2x^2 + 0.5x + 1.5
//                2. sqrt(x) - 0.5x
//                3. x - cos(x)
//                4. sqrt(x+3) + (3x-2)^(1/3) - 3
//                """);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            Function<Double, Double> function;
//            int fun = Integer.parseInt(reader.readLine());
//            switch (fun) {
//                case (0) -> {
//                    function = arg -> -2.7 * Math.pow(arg, 3) - 1.48*Math.pow(arg, 2) + 19.23*arg + 6.35;
//                }
//                case (1) -> {
//                    function = arg -> Math.pow(arg, 3) - 0.2*Math.pow(arg, 2) + 0.5*arg + 1.5;
//                }
//                case (2) -> {
//                    function = arg -> Math.sqrt(arg) -0.5*arg;
//                }
//                case (3) -> {
//                    function = arg -> arg-Math.cos(arg);
//                }
//                case (4) -> {
//                    function = arg -> Math.sqrt(arg+3) + Math.pow(3*arg-2, 1/3) - 3;
//                }
//                default -> {
//                    System.out.println("Введено некорректное значение");
//                    return;
//                }
//            }
//
////            Thread plot = new Thread(() -> {
////                List<Double> x = NumpyUtils.linspace(-10, 10, 100);
////                List<Double> y = x.stream().map(function).toList();
////                Plot plt = Plot.create();
////                plt.ylim(-10, 10);
////                plt.plot().add(x, y);
////                try {
////                    plt.show();
////                } catch (IOException | PythonExecutionException e) {
////                    throw new RuntimeException(e);
////                }
////            });
////            plot.start();
//            System.out.println("Каким методом будем решать?");
//        System.out.println("""
//                1. Половинное деление
//                2. Хорды
//                3. Ньютон
//                4. Секущие
//                5. Простая итерация""");
//        int method = Integer.parseInt(reader.readLine());
//        System.out.println("Введите точность");
//        double epsilon = Double.parseDouble(reader.readLine());
//        if(method==2||method==1||method==4) {
//            System.out.println("Введите интервал (сначала одно число, потом второе)");
//            inter[0] = Double.parseDouble(reader.readLine());
//            inter[1] = Double.parseDouble(reader.readLine());
//        } else {
//            System.out.println("Введите приближенное значение корня");
//            close = Double.parseDouble(reader.readLine());
//        }
//        service.readFromConsole(fun, method, close, inter, epsilon);

    }
}