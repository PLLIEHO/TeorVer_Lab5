package methods;

public class Hordes {
    double prevX = 0;
    public double count(double[] inter, FunctionStorage function, int key, double epsilon){
        double fa = function.count(inter[0], key);
        double fb = function.count(inter[1], key);
        double x = inter[0] - (inter[1]-inter[0])*fa/(fb-fa);
        double fx = function.count(x, key);
        double abs = Math.abs(x - prevX);
        prevX = x;
        System.out.println("a " + inter[0] + " b: " + inter[1] + " x: " + x + " F(a): " + fa + " F(b): " + fb + " F(x): " + fx + " |xk+1 - xk|: " + abs);
        if (!(Math.abs(fx) <= epsilon)) {
            double[] newInter = new double[2];
            if (inter[0] * x < 0) {
                newInter[0] = inter[0];
                newInter[1] = x;
            } else {
                newInter[1] = inter[1];
                newInter[0] = x;
            }
            return count(newInter, function, key, epsilon);
        } else {
            return x;
        }
    }
}
