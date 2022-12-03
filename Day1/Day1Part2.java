package Day1;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

class Day1Part2{
    public static void main(String[] args) throws FileNotFoundException{
        
        File file = new File("Day1\\ElfCals.txt");
        Scanner input = new Scanner(file);

        //loop through the same elf file as part 1, this time though when each elf is counted try
        //to add its total to an array that holds the largest three calorie counts
        int[] mostCals = new int[3];
        Arrays.fill(mostCals, 0);
        int sum = 0;
        while(input.hasNext()){
            String line = input.nextLine();
            if(line.isEmpty()){
               sum = 0; 
            }
            else{
                sum += Integer.parseInt(line);
                checkMostCals(mostCals, sum); //function that updates the calorie array
            }
        }

        System.out.println(mostCals[0] + mostCals[1] + mostCals[2]);
        input.close();
    }

    //Takes in the calorie count array and a potential new total and checks to see if the new total can be
    //put anywhere into the array, shifting old values if necessary
    public static void checkMostCals(int[] mostCals, int sum){
        if(sum >= mostCals[0]){
            mostCals[2] = mostCals[1];
            mostCals[1] = mostCals[0];
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