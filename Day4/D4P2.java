package Day4;
import java.util.Scanner;
import java.io.*;

public class D4P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //For part two we need to count the number of pairs that overlap anywhere at all
        //ex. for pair 72-92,48-88, the left limit of elf one overlaps the right limit of elf two, so it is counted 

        File file = new File("Day4\\SectionList.txt");
        Scanner input = new Scanner(file);

        int overlapPairs = 0;
        while(input.hasNext()){
            //split line of input into elf pairs
            String[] pairs = input.nextLine().split(",");
            String[] elfOne = pairs[0].split("-");
            String[] elfTwo = pairs[1].split("-");
            
            //if statement to check for condition described above
            if(Integer.parseInt(elfOne[0]) <= Integer.parseInt(elfTwo[1]) && 
                Integer.parseInt(elfOne[1]) >= Integer.parseInt(elfTwo[0])){
                overlapPairs++;
            }
        }

        System.out.println(overlapPairs);
        input.close();
    }
}
