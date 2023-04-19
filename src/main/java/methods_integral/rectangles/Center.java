package methods_integral.rectangles;

import methods_integral.FunctionStorage;
import methods_integral.Countable;

public class Center implements Countable {
    int n = 4;

    public double[] prepare(int function, double[] limits, double epsilon) {
        while(true){
            double h = (limits[1]-limits[0])/n;
            double h2 = (limits[1]-limits[0])/(n*2);
            double I = count(function, h, limits)*h;
            double Ix2 = count(function, h2, limits)*h2;
            if(Math.abs(Ix2 - I) < epsilon){
                double[] ans = new double[2];
                ans[0] = Ix2;
                ans[1] = (double)n;
                return ans;
            }
            else {
                n = n*2;
            }
        }
    }
    private Double count(int function, double h, double[] limits){
        double I = 0;
        Double memory_cell = null;
        for(double i = limits[0]; i<=limits[1]; i = i + h){
            if(memory_cell!=null){
                I+=FunctionStorage.count((memory_cell+i)/2, function);
                memory_cell = i;
            }
            else {
                memory_cell = i;
            }
        }
        return I;
    }
}
