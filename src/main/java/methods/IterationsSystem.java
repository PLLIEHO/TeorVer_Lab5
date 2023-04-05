package methods;

public class IterationsSystem {
    public double[] prepare(double[] x, SystemStorage function, int key, double epsilon){
        double[] proizv = function.countProizv(x, key);
        for(int i = 0; i<proizv.length-x.length; i=i+x.length){
            double sum = 0;
            for(int j=i; j<x.length; j++){
                sum += proizv[j];
            }
            if(!(sum<1)){
                System.out.println("Корни не сходятся.");
                return null;
            }
        }
        return count(x, function, key, epsilon);
    }

    public double[] count(double[] x, SystemStorage function, int key, double epsilon){
        double[] ans = function.count(x, key);
        double abs1 = Math.abs(ans[0]-x[0]);
        double abs2 = Math.abs(ans[1]-x[1]);
        System.out.println("x1 " + ans[0] + " x2: " + ans[1] + " |x'1 - x1|: " + abs1 + " |x'2 - x2|: " + abs2);
        for(int i =0; i<x.length; i++){
            if(ans[i]-x[i]<=epsilon){
                return ans;
            }
        }
        return count(ans, function, key, epsilon);
    }
}
