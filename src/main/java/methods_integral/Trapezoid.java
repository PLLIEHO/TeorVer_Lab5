package methods_integral;

public class Trapezoid implements Countable{
    int n = 4;

    public double[] prepare(int function, double[] limits, double epsilon) {
        while(true){
            double h = (limits[1]-limits[0])/n;
            double h2 = (limits[1]-limits[0])/(n*2);
            double I = count(function, h, limits);
            double Ix2 = count(function, h2, limits);
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
        double y0 = FunctionStorage.count(limits[0], function);
        double y1 = FunctionStorage.count(limits[1], function);
        for(double i = limits[0]+h; i<=limits[1]-h; i = i + h){
            I+= FunctionStorage.count(i, function);
        }
        I = h*(((y0+y1)/2)+I);
        return I;
    }
}
