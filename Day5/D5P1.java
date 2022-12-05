package Day5;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.io.*;


public class D5P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem involves reordering stacks of crates according to given instructions until a final
        //arrangement is reached, and then reading of the top of each stack to get a message.
        //We'll use an ArrayList of Stacks to solve the problem

        File file = new File("Day5\\CrateStacks.txt");
        Scanner input = new Scanner(file);
        
        ArrayList<Stack<String>> stacks = new ArrayList<Stack<String>>();

        //Part one: stack initialization
        for(int i = 0; i < 9; i++){
            String[] stackInput = input.nextLine().split(",");
            Stack<String> newStack = new Stack<String>();
            for(String crate : stackInput){
                newStack.push(crate);
            }
            stacks.add(newStack);
        }
        
        //Part Two: read instructions and modify stacks
        //instructions always look like: "move 3 from 3 to 7"
        //first number is number of pops - position 0
        //second number which stack to pop from - position 3
        //third number is stack to pop to - position 5
        //need to subtract 1 from each stack position because instructions do not base position off 0
        while(input.hasNext()){
            String[] instruction = input.nextLine().split(" ");
            for(int pops = Integer.parseInt(instruction[1]); pops > 0; pops--){
                String crate = stacks.get(Integer.parseInt(instruction[3]) - 1).pop();
                stacks.get(Integer.parseInt(instruction[5]) - 1).push(crate);
            }
        }

        //print out the message
        for(Stack<String> stack : stacks){
            System.out.print(stack.peek());
        }

        input.close();
    }
}
