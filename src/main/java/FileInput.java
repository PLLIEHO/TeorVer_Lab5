import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput {
    public void input(String name) throws IOException {
        Path path = Paths.get(name);
        Scanner scanner = new Scanner(path);

        scanner.useDelimiter(System.getProperty("line.separator"));
        List<String> instructions = new ArrayList<>();
        while(scanner.hasNext()){
            instructions.add(scanner.next());
        }
        scanner.close();
        int i = 0;
        IOService service = new IOService();
        while(i<instructions.size()){
            if(Integer.parseInt(instructions.get(i))>4){
                double[] pack = new double[4];
                pack[0] = Integer.parseInt(instructions.get(i));
                for(int j = 0; j<3; j++){
                    pack[j+1] = Double.parseDouble(instructions.get(i+j+1));
                }
                double[] temp = new double[2];
                temp[0] = pack[2];
                temp[1] = pack[3];
                service.readFromConsole((int)pack[0], 6, 0, temp, pack[1]);
                if(instructions.get(i+4).equals("N")){
                    break;
                } else {
                    i = i + 5;
                }
            }
            else {
                //6
                double[] pack = new double[5];
                for(int j = 0; j<5; j++){
                    pack[j] = Double.parseDouble(instructions.get(i+j));
                }
                double[] temp = new double[2];
                temp[0] = pack[3];
                temp[1] = pack[4];
                service.readFromConsole((int)pack[0], (int)pack[1], 0, temp, pack[2]);
                if(instructions.get(i+5).equals("N")){
                    break;
                } else {
                    i = i + 6;
                }
            }
        }

    }

}
