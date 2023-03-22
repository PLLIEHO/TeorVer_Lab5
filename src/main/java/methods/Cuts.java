package methods;

public class Cuts {
    public double count(double x0, double x1, FunctionStorage function, int key, double epsilon){
        double fx0 = function.count(x0, key);
        double fx1 = function.count(x1, key);
        double x = x1 - (x1-x0)*fx1/(fx1 - fx0);
        double fx = function.count(x, key);
        double abs = Math.abs(x-x1);
        System.out.println("xk-1 " + x0 + " xk: " + x1 + " xk+1: " + x + " F(xk+1): " + fx + " |xk+1 - xk|: " + abs);
        if(!(Math.abs(fx)<=epsilon)){
            return count(x1, x, function, key, epsilon);
        } else {
            return x;
        }
    }
}
