package Day3;
import java.util.Scanner;
import java.util.HashSet;
import java.io.*;

public class D3P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //Similar to part 1, except this part is about finding the only element common to three
        //different lines of input.
        //Solution will be like part 1, except this will need two sets instead of one.
        //Add first line's priority to a hashset, add any matches from the 2nd line to a 2nd set, and then use that
        //set just like in problem one
        //(a-z = 1-26, A-Z = 27-52)
        //add the matching priority and move on to the next group of three

        File file = new File("Day3\\rucksacks.txt");
        Scanner input = new Scanner(file);

        int sum = 0;
        int priority;
        while(input.hasNext()){
            String lineOne  = input.nextLine();
            String lineTwo  = input.nextLine();
            String lineThree  = input.nextLine();
            HashSet<Integer> setOne = new HashSet<Integer>();
            HashSet<Integer> setTwo = new HashSet<Integer>();

            for(int i = 0; i < lineOne.length(); i++){
                //add value to HashSet
                priority = priorityCalc(lineOne.charAt(i));
                setOne.add(priority);
            }

            for(int j = 0; j < lineTwo.length(); j++){
                //calculate priority of element
                priority = priorityCalc(lineTwo.charAt(j));
                //check if set contains priority, adding priority to setTwo if true
                if(setOne.contains(priority)){
                    setTwo.add(priority);
                }
            }

            for(int k = 0; k < lineThree.length(); k++){
                priority = priorityCalc(lineThree.charAt(k));
                if(setTwo.contains(priority)){
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
