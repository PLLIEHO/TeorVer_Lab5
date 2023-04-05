package methods;

import methods.FunctionStorage;

public class Halfs {
    public double count(double a, double b, FunctionStorage function, int key, double epsilon){
        double x = (a + b)/2;
        double fx = function.count(x, key);
        double fa = function.count(a, key);
        double fb = function.count(b, key);
        double abs = Math.abs(b-a);
        System.out.println("a " + a + " b: " + b + " x: " + x + " F(a): " + fa + " F(b): " + fb + " F(x): " + fx + " |b - a|: " + abs);
        if(abs<=epsilon){
            return x;
        }
        if(!(fa*fx>0)){
            return count(a, x, function, key, epsilon);
        } else {
            return count(x, b, function, key, epsilon);
        }
    }
}
