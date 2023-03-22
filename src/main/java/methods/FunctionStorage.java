package methods;

public class FunctionStorage {
    public double count(double x, int key){
        Double ans = null;
        switch (key){
            case 0:
                double k1 = -2.7;
                double k2 = -1.48;
                double k3 = 19.23;
                double k4 = 6.35;
                ans = k1*Math.pow(x, 3) + k2*Math.pow(x, 2) + k3*x + k4;
                break;
            case 1:
                k1 = 1;
                k2 = -0.2;
                k3 = 0.5;
                k4 = 1.5;
                ans = k1*Math.pow(x, 3) + k2*Math.pow(x, 2) + k3*x + k4;
                break;
            case 2:
                k1 = 1;
                k2 = -0.5;
                ans = k1*Math.sqrt(x) + k2*x;
                break;
            case 3:
                ans = x-Math.cos(x);
                break;
            case 4:
                ans = Math.sqrt(x+3) + Math.pow(3*x-2, 1/3) - 3;
                break;
            default:
                break;
        }
        return ans;
    }
    public double countProizv(double x, int key){
        Double ans = null;
        switch (key){
            case 0:
                double k1 = -2.7;
                double k2 = -1.48;
                double k3 = 19.23;
                ans = k1*3*Math.pow(x, 2) + k2*2*x + k3;
                break;
            case 1:
                k1 = 1;
                k2 = -0.2;
                k3 = 0.5;
                ans = k1*3*Math.pow(x, 2) + k2*2*x + k3;
                break;
            case 2:
                ans = 1/(2*Math.sqrt(x)) - 0.5;
                break;
            case 3:
                ans = 1+Math.sin(x);
                break;
            case 4:
                ans = (1/Math.pow(3*x-2, 2/3)) + (1/(2*Math.sqrt(x+3)));
                break;
        }
        return ans;
    }

    public double countProizv2(double x, int key){
        Double ans = null;
        switch (key){
            case 0:
                double k1 = -2.7;
                double k2 = -1.48;
                ans = k1*3*2*x + k2*2;
                break;
            case 1:
                k1 = 1;
                k2 = -0.2;
                ans = k1*3*2*x + k2*2;
                break;
            case 2:
                ans = -1/(4*Math.pow(x, 3/2));
                break;
            case 3:
                ans = Math.cos(x);
                break;
            case 4:
                ans = (2/Math.pow(3*x-2, 5/3)) + (1/(4*Math.pow(x+3, 3/2)));
                break;
        }
        return ans;
    }
}
