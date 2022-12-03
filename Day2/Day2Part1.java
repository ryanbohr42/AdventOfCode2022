package Day2;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class Day2Part1 {
    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("Day2\\ElfStratGuide.txt");
        Scanner input = new Scanner(file);

        //Initialize hashmap to hold all possible results of rock paper scissors for quick lookup
        //results determined from rules provided by Advent of Code 2022 problem definition
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        results.put("A" + "X", 4);
        results.put("A" + "Y", 8);
        results.put("A" + "Z", 3);
        results.put("B" + "X", 1);
        results.put("B" + "Y", 5);
        results.put("B" + "Z", 9);
        results.put("C" + "X", 7);
        results.put("C" + "Y", 2);
        results.put("C" + "Z", 6);

        //loop through the file determining each inidividual result and adding it to a running tally
        int totScore = 0;
        while(input.hasNext()){
            String round = input.nextLine();
            totScore += results.get(round.substring(0,1) + round.substring(2));
        }

        System.out.println(totScore);
        input.close();
    }
}
