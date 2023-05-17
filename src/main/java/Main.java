import com.github.sh0nk.matplotlib4j.NumpyUtils;
import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import methods_approx.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //double[] arr_x = {1.1, 2.3, 3.7, 4.5, 5.4, 6.8, 7.5};
        //double[] arr_y = {2.73, 5.12, 7.74, 8.91, 10.59, 12.75, 13.43};
        IOService service = new IOService();
        System.out.println("Введите 0, если хотите вводить с консоли. Введите имя файла, если хотите прочитать файл.");
        Scanner sc = new Scanner(System.in);
        List<Double[]> arrs = new ArrayList<>();
        String line = sc.nextLine();
        int number = 0;
        try {
            number = Integer.parseInt(line);
            arrs = consoleReading();
        } catch (NumberFormatException e){
            arrs = fileReading(line);
        }


        Double[] arr_xD = arrs.get(0);
        Double[] arr_yD = arrs.get(1);
        if(arr_yD.length!=arr_xD.length){
            System.out.println("Количество точек для x и y неодинаково.");
            main(args);
        }
        if(arr_yD.length<2){
            System.out.println("Точек слишком мало для аппроксимации.");
            main(args);
        }
        double[] arr_x = new double[arr_xD.length];
        double[] arr_y = new double[arr_yD.length];
        for(int j = 0; j<arr_xD.length; j++){
            arr_x[j] = arr_xD[j];
            arr_y[j] = arr_yD[j];
        }

        service.readFromConsole(arr_x, arr_y);
    }

    private static List<Double[]> consoleReading(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите таблицу построчно, разделяя элементы пробелом.");


        List<Double[]> answer = new ArrayList<>();
        int counter = 0;
        while(counter<2) {
            List<Double> matrixLine = new ArrayList<>();
            String number = sc.nextLine();
            String delimeter = " ";
            String[] subStr = number.split(delimeter);
            try {
                for (int i = 0; i < subStr.length; i++) {
                    matrixLine.add(Double.parseDouble(subStr[i]));
                }
            } catch (NumberFormatException e){
                System.out.println("Формат ввода не распознан. Повторите.");
                consoleReading();
            }
            Double[] tempArray = matrixLine.toArray(new Double[0]);
            answer.add(tempArray);
            counter++;
        }
        return  answer;
    }

    private static List<Double[]> fileReading(String line) throws FileNotFoundException {
        File text = new File(line);
        Scanner sc = new Scanner(text);

        List<Double[]> answer = new ArrayList<>();
        int counter = 0;
        while(counter<2) {
            List<Double> matrixLine = new ArrayList<>();
            String number = sc.nextLine();
            String delimeter = " ";
            String[] subStr = number.split(delimeter);
            try {
                for (int i = 0; i < subStr.length; i++) {
                    matrixLine.add(Double.parseDouble(subStr[i]));
                }
            } catch (NumberFormatException e){
                System.out.println("Формат ввода не распознан. Повторите.");
                System.exit(1);
            }
            Double[] tempArray = matrixLine.toArray(new Double[0]);
            answer.add(tempArray);
            counter++;
        }
        return  answer;
    }
}