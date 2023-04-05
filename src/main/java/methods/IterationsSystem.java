package methods;

import java.util.Arrays;

public class IterationsSystem {
    int iters = 0;
    public double[] prepare(double[] x, SystemStorage function, int key, double epsilon){
        double[] proizv = function.countProizv(x, key);
        System.out.println(Arrays.toString(proizv));
        if(proizv[0]+proizv[1]>=1||proizv[2]+proizv[3]>=1){
            System.out.println("Корни не сходятся");
            return null;
        }
        return count(x, function, key, epsilon);
    }

    public double[] count(double[] x, SystemStorage function, int key, double epsilon){
        double[] ans = function.count(x, key);
        double abs1 = Math.abs(ans[0]-x[0]);
        double abs2 = Math.abs(ans[1]-x[1]);
        System.out.println("x1 " + ans[0] + " x2: " + ans[1] + " |x'1 - x1|: " + abs1 + " |x'2 - x2|: " + abs2);
        iters++;
        if(Math.max(abs1, abs2)<=epsilon){
            System.out.println("Количество итераций: " + iters);
            return ans;
        }
        return count(ans, function, key, epsilon);
    }
}
