package methods;

public class Newton {
    public Double prepare(double[] inter, FunctionStorage function, int key, double epsilon){
        Double x0 = null;
        double proizv1 = function.countProizv(inter[0], key);
        double proizv2 = function.countProizv2(inter[0], key);
        for(double i = inter[0]; i<=inter[1]; i = i+epsilon){
            if(function.count(i, key)* function.countProizv2(i, key)>0){
                x0 = i;
            }
            if(proizv1* function.countProizv(i, key)<=0||proizv2* function.countProizv2(i, key)<0){
                System.out.println("Не сходится.");
                return null;
            }
        }
        if(x0==null){
            System.out.println("Невозможно подобрать приближенное значение.");
            return null;
        }
        return count(x0, function, key, epsilon);
    }
    public double count(double x0, FunctionStorage function, int key, double epsilon){
        double fx = function.count(x0, key);
        double f1x = function.countProizv(x0, key);
        double x = x0 - (fx)/(f1x);
        double abs = Math.abs(x-x0);
        System.out.println("xk " + x + " F(xk): " + fx + " F'(xk): "+ f1x + " |xk+1 - xk|: " + abs);
        if(abs<=epsilon){
            return x;
        } else {
            return count(x, function, key, epsilon);
        }
    }
}
