package Day1;
import java.util.Scanner;
import java.io.*;

class Day1{
    public static void main(String[] args) throws FileNotFoundException{
        
        File file = new File("Day1\\ElfCals.txt");
        Scanner input = new Scanner(file);
        
        int mostCals = -1;
        int sum = 0;
        while(input.hasNext()){
            String line = input.nextLine();
            //System.out.println(line);
            if(line.isEmpty()){
               sum = 0; 
            }
            else{
                sum += Integer.parseInt(line);
                if(sum > mostCals)
                    mostCals = sum;
            }
        }

        System.out.println(mostCals);
        input.close();
    }
}