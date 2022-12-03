package Day1;
import java.util.Scanner;
import java.io.*;

class Day1Part2{
    public static void main(String[] args) throws FileNotFoundException{
        
        File file = new File("Day1\\ElfCals.txt");
        Scanner input = new Scanner(file);
        
        int[] mostCals = new int[3];
        int sum = 0;
        while(input.hasNext()){
            String line = input.nextLine();
            //System.out.println(line);
            if(line.isEmpty()){
               sum = 0; 
            }
            else{
                sum += Integer.parseInt(line);
                checkMostCals(mostCals, sum);
            }
        }

        System.out.println(mostCals[0] + mostCals[1] + mostCals[2]);
        input.close();
    }

    public static void checkMostCals(int[] mostCals, int sum){
        if(sum >= mostCals[0]){
            
            mostCals[0] = sum;
            return;
        }
        else if(sum >= mostCals[1]){
            mostCals[2] = mostCals[1];
            mostCals[1] = sum;
            return;
        }
        else if(sum > mostCals[2]){
            mostCals[2] = sum;
            return;
        }
        return;
    }
}