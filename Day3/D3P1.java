package Day3;
import java.util.Scanner;
import java.util.HashSet;
import java.io.*;

public class D3P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //Problem boils down to needing to find the duplicate element present in both halves of a line of input.
        //The duplicate element then determines what to add to a running total
        //Idea: Read through the first half of each line and add each character's "priority" 
        //(a-z = 1-26, A-Z = 27-52) to a hashset, and check the second half's char against it,
        //add the priority that is already contained in the set to the tally, and move on to the next line

        File file = new File("Day3\\rucksacks.txt");
        Scanner input = new Scanner(file);

        int sum = 0;
        int priority;
        while(input.hasNext()){
            String line  = input.nextLine();
            HashSet<Integer> priorities = new HashSet<Integer>();
            for(int i = 0; i < line.length() / 2; i++){
                //add value to HashSet
                priority = priorityCalc(line.charAt(i));
                priorities.add(priority);
            }
            for(int j = line.length() / 2; j < line.length(); j++){
                //calculate priority of element
                priority = priorityCalc(line.charAt(j));
                //check if set contains priority, adding priority and ending current loop if true
                if(priorities.contains(priority)){
                    sum += priority;
                    break;
                }
            }
        }

        System.out.println(sum);
        input.close();
    }

    //function to convert a read char into it's given priority
    //constants are the decimal offsets used to convert the input's ascii decimal value to the priority
    //ex. 'a' - 96 = 1 because 'a' has a decimal ascii value of 97
    public static int priorityCalc(char element){
        if(Character.isUpperCase(element)){
            return element - 38;
        }
        else{
            return element - 96;
        }
    }
}
