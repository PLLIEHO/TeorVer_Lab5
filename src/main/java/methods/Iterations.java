package methods;

public class Iterations {
    public double prepare(double[] inter, FunctionStorage function, int key, double epsilon){
        double maximum = Math.max(Math.abs(function.countProizv(inter[0], key)), Math.abs(function.countProizv(inter[1], key)));
        double lambda = -1/maximum;
        System.out.println(lambda);
        return count(inter[0], lambda, function, key, epsilon);
    }
    private double count(double x0, double lambda, FunctionStorage function, int key, double epsilon){
        double x = x0 - lambda*function.count(x0, key);
        double fx = function.count(x, key);
        double abs = Math.abs(x - x0);
        System.out.println("xk " + x0 + " xk+1: " + x + " F(xk+1): " + fx + " |xk+1 - xk|: " + abs);
        if(!(fx<=epsilon)){
            return count(x, lambda, function, key, epsilon);
        } else {
            return x;
        }
    }
}
