import methods.Cuts;
import methods.FunctionStorage;
import methods.Hordes;
import methods.Iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOService {

    public void readFromConsole(int keyFunction, int keyMethod, double[] inter, double epsilon) {
        FunctionStorage function = new FunctionStorage();
        String ch = check(keyFunction, inter);
        if(!ch.equals("OK")){
            System.out.println(ch);
            return;
        }
        switch (keyMethod){
            case 2:
                Hordes hordes = new Hordes();
                System.out.println("x: " + hordes.count(inter, function, keyFunction, epsilon));
                break;
            case 4:
                Cuts cuts = new Cuts();
                System.out.println("x: " + cuts.count(inter[1], inter[1]-0.1, function, keyFunction, epsilon));
                break;
            case 5:
                Iterations iterations = new Iterations();
                System.out.println("x: " + iterations.prepare(inter, function, keyFunction, epsilon));
                break;
        }
    }

    private String check(int key, double[] inter){
        FunctionStorage function = new FunctionStorage();
        if(!(function.count(inter[0], key)*function.count(inter[1], key)<0)){
            return "Корней на заданном участке нет.";
        } else if(monotonous(key, inter, function)){
            return "Корней на заданном участке может быть несколько, уточните.";
        } else {
            return "OK";
        }
    }

    private boolean monotonous(int key, double[] inter, FunctionStorage function){
        double first = function.countProizv2(inter[0], key);
        for(double i = inter[0]; i<inter[1]; i=i+0.1){
            if(!(function.countProizv2(i, key)*first>0)){
                return false;
            }
        }
        return true;
    }
}
