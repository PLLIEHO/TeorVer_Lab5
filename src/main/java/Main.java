import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException {
        double close = 0;
        double[] inter = new double[2];
        CountTask task = new CountTask();
        task.interval();
        IOService service = new IOService();
            System.out.println("""
                Выберите одно из уравнений:
                1. x^3 - 0.2x^2 + 0.5x + 1.5
                2. sqrt(x) - 0.5x
                3. x - cos(x)
                4. sqrt(x+3) + (3x-2)^(1/3) - 3
                или одну из систем уравнений:
                5. -0.3 + 0.1x^2 + 0.2y^2 +x
                   -0.7 + 0.2x^2 + 0.1xy + y
                6. x^3 + sin(y)
                   2x + y^2 - 4
                хотите вводить из файла? Введите его имя (расширение должно быть .txt)
                """);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Function<Double, Double> function;
            String read = reader.readLine();
            int fun;
            try {
                fun = Integer.parseInt(read);
            } catch (NumberFormatException e) {
                FileInput input = new FileInput();
                input.input(read);
                return;
            }
            switch (fun) {
                case (0) -> {
                    function = arg -> -2.7 * Math.pow(arg, 3) - 1.48*Math.pow(arg, 2) + 19.23*arg + 6.35;
                }
                case (1) -> {
                    function = arg -> Math.pow(arg, 3) - 0.2*Math.pow(arg, 2) + 0.5*arg + 1.5;
                }
                case (2) -> {
                    function = arg -> Math.sqrt(arg) -0.5*arg;
                }
                case (3) -> {
                    function = arg -> arg-Math.cos(arg);
                }
                case (4) -> {
                    function = arg -> Math.sqrt(arg+3) + Math.pow(3*arg-2, 1/3) - 3;
                }
                case (5), (6) -> {
                    system(fun);
                    return;
                }
                default -> {
                    System.out.println("Введено некорректное значение");
                    return;
                }
            }

        Plot plt = Plot.create();
            Thread plot = new Thread(() -> {
                List<Double> x = null;
                switch (fun){
                    case 0:
                    case 1:
                    case 3:
                        x = NumpyUtils.linspace(-10, 10, 100);
                        break;
                    case 2:
                        x = NumpyUtils.linspace(0.001, 10, 100);
                        break;
                    case 4:
                        x = NumpyUtils.linspace(0.667, 10, 100);
                        break;
                }
                List<Double> y = x.stream().map(function).toList();
                plt.ylim(-10, 10);
                plt.plot().add(x, y);
                try {
                    plt.show();
                } catch (IOException | PythonExecutionException e) {
                    System.out.println("warning");
                    throw new RuntimeException(e);
                }
            });
            plot.start();
            System.out.println("Каким методом будем решать?");
        System.out.println("""
                1. Половинное деление
                2. Хорды
                3. Ньютон
                4. Секущие (К сожалению, пока доступны только с вводом изначального значения)
                5. Простая итерация""");
        int method = Integer.parseInt(reader.readLine());
        System.out.println("Введите точность");
        double epsilon = Double.parseDouble(reader.readLine());
        if(method==2||method==1||method==5 || method==3) {
            System.out.println("Введите интервал (сначала одно число, потом второе)");
            inter[0] = Double.parseDouble(reader.readLine());
            inter[1] = Double.parseDouble(reader.readLine());
        } else {
            System.out.println("Введите приближенное значение корня");
            close = Double.parseDouble(reader.readLine());
        }
        service.readFromConsole(fun, method, close, inter, epsilon);
        System.out.println("Продолжить выполнение программы? (Y/N)");
        if(reader.readLine().equals("Y")) {
            plt.close();
            plot.stop();
            main(null);
        }
        plt.close();
        plot.stop();
    }

    private static void system(int fun) throws IOException {
        BiFunction<Double, Double, Double> functionF;
        BiFunction<Double, Double, Double> functionS;
        List<Double> xs = NumpyUtils.linspace(-10, 10, 100);
        List<Double> y1;
        List<Double> y2;
        List<Double> xRange = NumpyUtils.arange(-10, 10, 0.025);
        List<Double> yRange = NumpyUtils.arange(-10, 10, 0.025);
        NumpyUtils.Grid<Double> grid = NumpyUtils.meshgrid(xRange, yRange);
        Plot plt = Plot.create();
        plt.ylim(-10, 10);
        switch (fun) {
            case (5) -> {
                functionF = (x, y) -> -0.3+0.1*Math.pow(x, 2) + 0.2*Math.pow(y, 2) + x;
                functionS = (x, y) -> -0.7 + 0.2*Math.pow(x, 2) + 0.1 * x * y + y;
            }
            case (6) -> {
                functionF = (x, y) -> Math.pow(x, 2) + Math.pow(y, 2) - 4 + x;
                functionS = (x, y) -> y - 3 * x;
            }
            default -> {
                System.out.println("Введено некорректное значение");
                return;
            }
        }
        Thread thread = new Thread(() -> {
            List<List<Double>> g1 = grid.calcZ(functionF::apply);
            List<List<Double>> g2 = grid.calcZ(functionS::apply);
            plt.contour().add(xRange, yRange, g1).add(List.of(0));
            plt.contour().add(xRange, yRange, g2).add(List.of(0));
            try {
                plt.show();
            } catch (IOException | PythonExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите точность");
        double epsilon = Double.parseDouble(reader.readLine());
            System.out.println("Введите начальное приближение");
            double[] inter = new double[2];
            inter[0] = Double.parseDouble(reader.readLine());
            inter[1] = Double.parseDouble(reader.readLine());
            IOService service = new IOService();
        service.readFromConsole(fun, 6, 0, inter, epsilon);
        System.out.println("Продолжить выполнение программы? (Y/N)");
        if(reader.readLine().equals("Y")) {
            plt.close();
            thread.stop();
            main(null);
        }
        plt.close();
        thread.stop();
    }
}