package methods;

public class Iterations {
    public Double prepare(double[] inter, FunctionStorage function, int key, double epsilon){
        double maximum = Math.max(Math.abs(function.countProizv(inter[0], key)), Math.abs(function.countProizv(inter[1], key)));
        double lambda = -1/maximum;
        //System.out.println(lambda);
        double pr1 = 1 - lambda* function.countProizv(inter[0], key);
        double pr2 = 1 - lambda* function.countProizv(inter[1], key);
        if(pr1>=1||pr2>=1){
            System.out.println("Корни не сходятся");
            return null;
        }
        return count(inter[0], lambda, function, key, epsilon);
    }
    private double count(double x0, double lambda, FunctionStorage function, int key, double epsilon){
        double x = x0 - lambda*function.count(x0, key);
        double fx = function.count(x, key);
        double abs = Math.abs(x - x0);
        System.out.println("xk " + x0 + " xk+1: " + x + " F(xk+1): " + fx + " |xk+1 - xk|: " + abs);
        if(!(abs<=epsilon)){
            return count(x, lambda, function, key, epsilon);
        } else {
            return x;
        }
    }
}
