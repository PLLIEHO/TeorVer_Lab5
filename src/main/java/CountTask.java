import methods.Cuts;
import methods.FunctionStorage;
import methods.Hordes;
import methods.Iterations;

public class CountTask {
    double k1 = -2.7;
    double k2 = -1.48;
    double k3 = 19.23;
    double k4 = 6.35;
    double[] inter1 = new double[2];
    double[] inter2 = new double[2];
    double[] inter3 = new double[2];

    public void interval(){
        FunctionStorage storage = new FunctionStorage();
        double a = k1*3;
        double b = k2*2;
        double c = k3;
        double x1;
        double x2;
        double discr = Math.pow(b, 2) - 4*a*c;
        if(discr<0){
            System.out.println("Нет корней");
            return;
        } else if(discr>0){
            x1 = (-b-Math.sqrt(discr))/(2*a);
            x2 = (-b+Math.sqrt(discr))/(2*a);
            if(x2<x1){
                double buf = x1;
                x1 = x2;
                x2 = buf;
            }
            inter2[0] = x1;
            inter2[1] = x2;
            inter1[1] = x1;
            double temp = x1-1;
            while(true){
                if(storage.count(temp, 0)*storage.count(x1, 0)<0){
                    inter1[0] = temp;
                    break;
                }
                temp--;
            }
            inter3[0] = x2;
            temp = x2+1;
            while(true){
                if(storage.count(temp, 0)*storage.count(x2, 0)<0){
                    inter3[1] = temp;
                    break;
                }
                temp++;
            }
            Hordes hordes = new Hordes();
            Cuts cuts = new Cuts();
            Iterations iterations = new Iterations();
            FunctionStorage store = new FunctionStorage();
            System.out.println("Уточнённый правый корень: " + hordes.count(inter3, store, 0, 0.01));
            System.out.println("Уточнённый центральный корень: " + cuts.count(inter2[1], inter2[1]-1, store, 0, 0.01));
            System.out.println("Уточнённый левый корень: " + iterations.prepare(inter1, store, 0, 0.01));
        }
    }


}
