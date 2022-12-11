package Day10;
import java.util.Scanner;
import java.io.*;

public class D10P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //Part 2 builds on part one by using the changing value of the register
        //to produce a print out that contains eight capital letters
        
        File file = new File("Day10\\RegOps.txt");
        //File file = new File("Day10\\TestInputDay10.txt");
        Scanner input = new Scanner(file);

        int regX = 1; //value of the register
        int cycle = 0; //which cycle is currently running
        int busyCycles = 0; //how many cycles need to complete before action completed and new op needed
    
        while(input.hasNext()){
            String[] instruction = input.nextLine().split(" ");
            busyCycles += numBusyCycles(instruction);
            for(int i = busyCycles; i > 0; i--){
                if(cycle % 40 == 0){
                    System.out.println();
                }
                if((cycle%40) >= regX - 1 && (cycle%40) <= regX + 1){
                    System.out.print("#");
                }
                else{
                    System.out.print(" ");
                }
                busyCycles--;
                cycle++;
            }
            regX += completeInstruction(instruction);
        }

        input.close();
    }

    public static int numBusyCycles(String[] inst){
        if(inst[0].equals("noop")){
            return 1;
        }
        else{
            return 2;
        }
    }

    public static int completeInstruction(String[] inst){
        if(inst[0].equals("noop")){
            return 0;
        }
        else{
            return Integer.parseInt(inst[1]);
        }
    }
}
