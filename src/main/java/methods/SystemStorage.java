package methods;

public class SystemStorage {
    public double[] count(double[] x, int key){
        double[] ans = new double[2];
        switch (key){
            case 5:
                ans[0] = 0.3-0.1*Math.pow(x[0], 2) - 0.2*Math.pow(x[1], 2);
                ans[1] = 0.7 - 0.2*Math.pow(x[0], 2) - 0.1*x[0]*x[1];
                break;
            case 6:
                ans[0] = -Math.pow(x[0], 2) - Math.pow(x[1], 2) + 4;
                ans[1] = 3*x[0];
                break;
            default:
                break;
        }
        return ans;
    }
    public double[] countProizv(double[] x, int key){
        double[] ans = new double[4];
        switch (key) {
            case 5:
                ans[0] = -0.2 * x[0];
                ans[1] = -0.4 * x[1];
                ans[2] = -0.4 * x[0] - 0.1 * x[1];
                ans[3] = -0.1 * x[0];
                break;
            case 6:
                ans[0] = -2*x[0];
                ans[1] = -2*x[1];
                ans[2] = 3;
                ans[3] = 0;
                break;
        }
        return ans;
    }
}
