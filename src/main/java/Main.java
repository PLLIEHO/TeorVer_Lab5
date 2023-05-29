import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import com.github.sh0nk.matplotlib4j.builder.HistBuilder;
import edu.princeton.cs.introcs.StdDraw;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, PythonExecutionException {
        double[] arr = {-1.73, 1.66, 0.62, 1.63, 0.42, -0.63, -1.64, -1.38, -1.68, 1.42, -0.73, -0.80, 1.52, 1.04, -1.21, -1.53, -1.10, -0.66, 0.82, 0.72};
        double[] alma = arr;
        //Variation line
        Arrays.sort(arr);
        int n = arr.length;

        //Xmax & Xmin
        double Xmin = arr[0];
        double Xmax = arr[n-1];

        //Range
        double w = Xmax-Xmin;

        //Sterges formula, h - interval length
        double h = (w)/(1+(Math.log(n)/Math.log(2)));

        //forming table
        List<Double> xi = new ArrayList<>();
        List<Double> xi1 = new ArrayList<>();
        List<Double> middles = new ArrayList<>();
        List<Integer> ni = new ArrayList<>();
        List<Double> Wi = new ArrayList<>();
        List<Double> Wi_h = new ArrayList<>();

        double counter = Xmin;
        while(counter<Xmax){
            xi.add(counter);
            double next = counter +h;
            xi1.add(next);

            middles.add((next+counter)/2);

            int niToAdd = 0;
            for (double v : arr) {
                if (v >= counter && v <= next) {
                    niToAdd++;
                }
            }
            ni.add(niToAdd);

            double WiToAdd = (double) niToAdd/n;
            Wi.add(WiToAdd);
            Wi_h.add(WiToAdd/h);
            counter+=h;
        }

        //Table output
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(xi.toArray()));
//        System.out.println(Arrays.toString(xi1.toArray()));
//        System.out.println(Arrays.toString(middles.toArray()));
//        System.out.println(Arrays.toString(ni.toArray()));
//        System.out.println(Arrays.toString(Wi.toArray()));
//        System.out.println(Arrays.toString(Wi_h.toArray()));

        //Forming function
        List<Double> F = new ArrayList<>();
        F.add(0.0);
        for(int i = 0; i<xi.size(); i++){
            int tempN = 0;
            for(int j = 0; j<=i; j++){
                tempN += ni.get(j);
            }
            //System.out.println(tempN);
            double FtoAdd = (double) tempN/n;
            F.add(FtoAdd);
        }
        F.add(1.0);
//        System.out.println();
//        System.out.println(Arrays.toString(F.toArray()));


        //Forming function plot
        Thread plot = new Thread(() -> {
            Plot plt = Plot.create();
            List<Double> FforPlot = new ArrayList<>();
            FforPlot.addAll(F);
            FforPlot.addAll(F);
//            FforPlot.add(1.0);
//            FforPlot.add(1.0);
            Collections.sort(FforPlot);
            List<Double> XforPlot = new ArrayList<>();
            XforPlot.add(xi.get(0)-h);
            XforPlot.add(xi.get(0));
            for(int k = 1; k<xi.size(); k++){
                XforPlot.add(xi.get(k-1));
                XforPlot.add(xi.get(k));
            }
            XforPlot.add(xi.get(xi.size()-1));
            XforPlot.add(xi.get(xi.size()-1)+h);
            XforPlot.add(xi.get(xi.size()-1)+h+h);
            XforPlot.add(xi.get(xi.size()-1)+h+h+h);
            plt.plot().add(XforPlot, FforPlot);
            plt.ylim(-0.01, 2);
            plt.title("Function (F(xi))");
            try {
                plt.show();
            } catch (IOException | PythonExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        plot.start();

        //Forming frequency polygon
        Thread freq = new Thread(()->{
            Plot plt = Plot.create();
            plt.plot().add(middles, ni);
            plt.ylim(0, 5);
            plt.title("Frequency polygon (ni (x'i))");
            try{
                plt.show();
            } catch (IOException | PythonExecutionException e){
                throw new RuntimeException(e);
            }
        });
        freq.start();

        //Forming Wi/h histogram
        Thread hist = new Thread(()->{
            Plot plt = Plot.create();
            List<Double> XforHist = new ArrayList<>();
            List<Double> temp1 = new ArrayList<>();
            List<Double> temp2 = new ArrayList<>();
            for(int y = 0; y<xi.size(); y++){
                temp1.add(xi.get(y)-(h/2));
                temp2.add(xi1.get(y)-(h/2));
            }
            XforHist.addAll(temp1);
            XforHist.addAll(temp2);
            Collections.sort(XforHist);

            List<Double> WforHist = new ArrayList<>();
            for (Double aDouble : Wi_h) {
                WforHist.add(aDouble);
                WforHist.add(aDouble);
            }
            plt.plot().add(XforHist, WforHist);
            plt.ylim(0, 10);
            plt.title("Histogram (Wi/h (xi)) with h/2 shift");
            plt.ylim(-0.01, 1);
            try{
                plt.show();
            } catch (IOException | PythonExecutionException e){
                throw new RuntimeException(e);
            }
        });
        hist.start();

        //Finding middle and midsqr
        double tempMiddle = 0.0;
        double tempMidsqr = 0.0;
        for(int m = 0; m< middles.size(); m++){
            tempMiddle += middles.get(m)*ni.get(m);
            tempMidsqr += Math.pow(middles.get(m), 2)*ni.get(m);
        }
        double mid = tempMiddle/n;
        double mdlsqr = (tempMidsqr/n) - Math.pow(mid, 2);

        //output
        System.out.println();
        System.out.println();
        System.out.println("Начальный ряд: " + Arrays.toString(alma));
        System.out.println("Вариационный ряд: " + Arrays.toString(arr));
        System.out.println("Экстремальные значения: min = " + Xmin + "; max = " + Xmax);
        System.out.println("Размах: " + w);
        System.out.println("Длина интервала, найденная по формуле Стерджеса: " + h);
        System.out.println();
        System.out.println("Таблица интервалов и промежуточных значений");
        System.out.println("Xi | X(i+1) | X'i | ni | Wi | W/h");
        for(int l = 0; l< xi.size(); l++){
            System.out.println(xi.get(l) + " | " + xi1.get(l) + " | " + middles.get(l) + " | " + ni.get(l) + " | " + Wi.get(l) + " | " + Wi_h.get(l));
        }
        System.out.println();
        System.out.println("Таблица значений эмпирической функции");
        System.out.println("При x < " + xi.get(0) + " F = " + F.get(0));
        for(int o = 1; o < F.size()-1; o++){
            System.out.println("При " + xi.get(o-1) + " < x < " + xi1.get(o-1) + " F = " + F.get(o));
        }
        System.out.println("При x > " + xi1.get(xi1.size()-1) + " F = " + 1);
        System.out.println();
        System.out.println("Найденное выборочное среднее: " + mid);
        System.out.println("Найденное среднеквадратичное отклонение: " + mdlsqr);
        System.out.println();
        System.out.println();
    }
}