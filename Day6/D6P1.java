package Day6;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class D6P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem requires us to find the number of characters in a String that need to be read
        //before a sequence of four different characters is detected
        //We'll use a hashmap and a moving window to determine where this first unique sequence is located

        File file = new File("Day6\\ElfSignal.txt");
        Scanner input = new Scanner(file);

        String signal = input.nextLine();
        HashMap<Character, Integer> dupChecker = new HashMap<Character, Integer>();
        int windowSize = 4;
        int uniqueChars = 0;

        //reads in the first four characters to initialize the window and unique char count
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
        //check if first four letters are unique, and if so print out marker
        if(uniqueChars == windowSize){
            System.out.println(signal.substring(0, 4));
        }

        //iterate through the rest of the input adjusting character counts and unique count as necessary
        //until a sequence of four unique characters is found.
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
