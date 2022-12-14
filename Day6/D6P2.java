package Day6;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class D6P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //Part 2 is the same as part one except, instead of a sequence of four unique character, the
        //sequence is now 14 characters long
        //This just requires a change in the window size from 4 to 14

        File file = new File("Day6\\ElfSignal.txt");
        Scanner input = new Scanner(file);

        String signal = input.nextLine();
        HashMap<Character, Integer> dupChecker = new HashMap<Character, Integer>();
        int windowSize = 14;
        int uniqueChars = 0;

        //reads in the first 14 characters to initialize the window and unique char count
        for(int i = 0; i < windowSize; i++){
            char letter = signal.charAt(i);
            dupChecker.put(letter, dupChecker.getOrDefault(letter, 0) + 1);
            //if a character is not already a key in the map, or had a value of 0, it must be new to the window
            //and therefore unique
            if(dupChecker.get(letter) == 1){
                uniqueChars++;
            }
            //if a char is in the window twice it is no longer unique.
            //only necessary to decrement unique count when the character is present twiec and not three or four
            //times because count is for uniqueness, and being present more than twice does not make
            //a char less unique.
            else if(dupChecker.get(letter) == 2){
                uniqueChars--;
            }
        }
        //check if first 14 letters are unique, and if so print out marker
        if(uniqueChars == windowSize){
            System.out.println(signal.substring(0, windowSize));
        }

        //iterate through the rest of the input adjusting character counts and unique count as necessary
        //until a sequence of 14 unique characters is found.
        for(int i = windowSize; i < signal.length(); i++){
            char newLetter = signal.charAt(i);
            char lostLetter = signal.charAt(i - windowSize);

            //check to see how char removed from window impacts counts
            dupChecker.put(lostLetter, dupChecker.get(lostLetter) - 1);
            //if dropping letter reduces value to 1, it means that the instance of that char still in the window
            //is now unique
            if(dupChecker.get(lostLetter) == 1){
                uniqueChars++;
            }
            //otherwise, window just loses a unique letter
            else if(dupChecker.get(lostLetter) == 0){
                uniqueChars--;
            }

            //check new letter, with counts changing with the same behavior as when the window is initialized
            dupChecker.put(newLetter, dupChecker.getOrDefault(newLetter, 0) + 1);
            if(dupChecker.get(newLetter) == 1){
                uniqueChars++;
            }
            else if(dupChecker.get(newLetter) == 2){
                uniqueChars--;
            }

            if(uniqueChars == windowSize){
                System.err.println(signal.substring(i - (windowSize - 1), i + 1));
                System.out.println(i + 1); // add one to i to account for 0 indexing
                break;
            }
        }

        input.close();
    }
}
