package Day6;
import java.util.Scanner;
import java.util.HashSet;
import java.io.*;

public class SetSol {
 public static void main(String[] args) throws FileNotFoundException{
    //A different solution involving sets
    //Adds all letters in a moving window to a set, and if the size of the set is equal to the
    //size of the window, all the characters in the window must be unique.
    //cleaner code, but slower than first solution

    File file = new File("Day6\\ElfSignal.txt");
    Scanner input = new Scanner(file);
    String signal = input.nextLine();

    int windowSize = 14;
    for(int i = windowSize; i <= signal.length(); i++){
        HashSet<Character> set = new HashSet<Character>();
        for(int k = i -  windowSize; k < i; k++){
                    set.add(signal.charAt(k));
        }
        if(set.size() == windowSize){
            System.out.println(signal.substring(i - (windowSize), i));
            System.out.println(i);
            break;
        } 
    }

    input.close();
 }  
}
