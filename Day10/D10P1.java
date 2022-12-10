package Day10;
import java.util.Scanner;
import java.io.*;

public class D10P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem involves simulating operations on a register that only happen
        //on discrete cycles. The value of this register is read at specific cycles to
        //produce the answer.
        //Read at each cycle = 40x + 20, where x is a whole number up to cycle 220
        
        File file = new File("Day10\\RegOps.txt");
        //File file = new File("Day10\\TestInputDay10.txt");
        Scanner input = new Scanner(file);

        int sigStrSum = 0; //the sum of all read signal strengths (cycle * regX) -> the answer
        int regX = 1; //value of the register
        int cycle = 0; //which cycle is currently running
        int busyCycles = 0; //how many cycles need to complete before action completed and new op needed
    
        while(input.hasNext()){
            String[] instruction = input.nextLine().split(" ");
            busyCycles += numBusyCycles(instruction);
            for(int i = busyCycles; i >= 0; i--){
                if((cycle - 20) % 40 == 0){
                    sigStrSum += cycle * regX;
                }
                busyCycles--;
                cycle++;
            }
            regX += completeInstruction(instruction);
        }

        /*
        
        busyCycles += numBusyCycles(instruction);
        while(busyCycles >= 0){
            if((cycle - 20) % 40 == 0){
                sigStrSum += cycle * regX;
            }
            if(busyCycles == 0){
                regX += completeInstruction(instruction);
                if(input.hasNext()){
                    instruction = input.nextLine().split(" ");
                }
                busyCycles += numBusyCycles(instruction);
            }
            busyCycles--;
            cycle++;
            System.out.println("Cycle:" + cycle + " " + regX);
        }
        */
        //System.out.println(regX);
        System.out.println(sigStrSum);
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