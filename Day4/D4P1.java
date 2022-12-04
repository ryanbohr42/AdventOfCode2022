package Day4;
import java.util.Scanner;
import java.io.*;

public class D4P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //For part one we need to count the number of pairs that feature complete overlap in sections
        //ex. for pair 1-93,2-11, 2-11 is completely contained by 1-93, so this pair is counted

        File file = new File("Day4\\SectionList.txt");
        Scanner input = new Scanner(file);

        int overlapPairs = 0;
        while(input.hasNext()){
            //split line of input into elf pairs
            String[] pairs = input.nextLine().split(",");
            String[] elfOne = pairs[0].split("-");
            String[] elfTwo = pairs[1].split("-");

            //if statement to check for condition described above
            if((Integer.parseInt(elfOne[0]) <= Integer.parseInt(elfTwo[0]) && Integer.parseInt(elfOne[1]) >= Integer.parseInt(elfTwo[1]))
             || (Integer.parseInt(elfTwo[0]) <= Integer.parseInt(elfOne[0]) && Integer.parseInt(elfTwo[1]) >= Integer.parseInt(elfOne[1]))){
                overlapPairs++;
             }
        }

        System.out.println(overlapPairs);
        input.close();
    }
}
