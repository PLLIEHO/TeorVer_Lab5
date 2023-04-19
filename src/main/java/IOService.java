import methods_integral.Simpson;
import methods_integral.Trapezoid;
import methods_integral.rectangles.Center;
import methods_integral.rectangles.Left;
import methods_integral.rectangles.Right;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class IOService {

    public void readFromConsole(int keyFunction, int keyMethod, double[] limits, double epsilon, int signs) {
        double scale = Math.pow(10, signs);
        switch (keyMethod) {
            case 1 -> {
                Left func = new Left();
                double[] value = func.prepare(keyFunction, limits, epsilon);
                double precided = Math.ceil(value[0]*scale)/scale;
                System.out.println("I= " + precided + " n= " + (int)(value[1]));
            }
            case 2 -> {
                Right func = new Right();
                double[] value = func.prepare(keyFunction, limits, epsilon);
                double precided = Math.ceil(value[0]*scale)/scale;
                System.out.println("I= " + precided + " n= " + (int)(value[1]));
            }
            case 3 -> {
                Center func = new Center();
                double[] value = func.prepare(keyFunction, limits, epsilon);
                double precided = Math.ceil(value[0]*scale)/scale;
                System.out.println("I= " + precided + " n= " + (int)(value[1]));
            }
            case 4 -> {
                Trapezoid func = new Trapezoid();
                double[] value = func.prepare(keyFunction, limits, epsilon);
                double precided = Math.ceil(value[0]*scale)/scale;
                System.out.println("I= " + precided + " n= " + (int)(value[1]));
            }
            case 5 -> {
                Simpson func = new Simpson();
                double[] value = func.prepare(keyFunction, limits, epsilon);
                double precided = Math.ceil(value[0]*scale)/scale;
                System.out.println("I= " + precided + " n= " + (int)(value[1]));
            }
            default -> {
                System.out.println("Ввод не распознан");
            }
        }
    }

}
