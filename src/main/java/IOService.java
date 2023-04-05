import methods.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOService {

    public void readFromConsole(int keyFunction, int keyMethod, double close, double[] inter, double epsilon) {
        FunctionStorage function = new FunctionStorage();
        String ch = "temp";
        if(keyMethod!=6){ ch = check(keyFunction, inter, epsilon);};
        switch (keyMethod) {
            case 1 -> {
                if(!ch.equals("OK")){
                    System.out.println(ch);
                    return;
                }
                Halfs halfs = new Halfs();
                System.out.println("x: " + halfs.count(inter[0], inter[1], function, keyFunction, epsilon));
            }
            case 2 -> {
                if(!ch.equals("OK")){
                    System.out.println(ch);
                    return;
                }
                Hordes hordes = new Hordes();
                System.out.println("x: " + hordes.count(inter, function, keyFunction, epsilon));
            }
            case 3 -> {
                if(!ch.equals("OK")){
                    System.out.println(ch);
                    return;
                }
                Newton newton = new Newton();
                System.out.println("x: " + newton.prepare(inter, function, keyFunction, epsilon));
            }
            case 4 -> {
                if(!ch.equals("OK")){
                    System.out.println(ch);
                    return;
                }
                Cuts cuts = new Cuts();
                System.out.println("x: " + cuts.count(close, close - 0.1, function, keyFunction, epsilon));
            }
            case 5 -> {
                if(!ch.equals("OK")){
                    System.out.println(ch);
                    return;
                }
                Iterations iterations = new Iterations();
                System.out.println("x: " + iterations.prepare(inter, function, keyFunction, epsilon));
            }
            case 6 -> {
                IterationsSystem system = new IterationsSystem();
                SystemStorage function1 = new SystemStorage();
                System.out.println("x, y: " + Arrays.toString(system.prepare(inter, function1, keyFunction, epsilon)));
            }
            default -> {
                System.out.println("Ввод не распознан");
            }
        }
    }

    private String check(int key, double[] inter, double epsilon){
        FunctionStorage function = new FunctionStorage();
        if(!(function.count(inter[0], key)*function.count(inter[1], key)<0)){
            return "Корней на заданном участке нет.";
        } else if(!monotonous(key, inter, function, epsilon)){
            return "Корней на заданном участке может быть несколько, уточните.";
        } else {
            return "OK";
        }
    }

    private boolean monotonous(int key, double[] inter, FunctionStorage function, double epsilon){
        if(inter[0]*inter[1]<0){
            return true;
        }
        double first = function.countProizv(inter[0], key);
        for(double i = inter[0]+epsilon; i<inter[1]; i=i+epsilon){
            if(!(function.countProizv(i, key)*first>0)){
                return false;
            }
        }
        return true;
    }
}
