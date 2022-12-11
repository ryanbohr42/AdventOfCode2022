package Day11;
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class D11P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem requires our program to simulate the behavior of eight different monkeys over
        //the course of 20 rounds in which they each act
        
        File file = new File("Day11\\Monkeys.txt");
        Scanner input = new Scanner(file);

        //Monkey List initialization:
        List<Monkey> monkeys = new LinkedList<Monkey>();
        int[] items;
        boolean opMultiply;
        int opNum;
        boolean opDup;
        int testNum;
        int trueTarget;
        int falseTarget;
        for(int monkey = 0; monkey < 8; monkey++){
            //"Monkey #" line
            input.nextLine();
            //get list of item values
            String[] itemValues = input.nextLine().trim().substring(15).split(",");
            items = new int[itemValues.length];
            for(int i = 0; i < items.length; i++){
                items[i] = Integer.parseInt(itemValues[i].trim());
            }
            //get operation type and number
            String[] operation = input.nextLine().split(" ");
            if(operation[operation.length  - 2].equals("*")){
                opMultiply = true;
            }
            else{
                opMultiply = false;
            }
            if(operation[operation.length - 1].equals("old")){
                opNum = 0;
                opDup = true;
            }
            else{
                opNum = Integer.parseInt(operation[operation.length - 1]);
                opDup = false;
            }
            //get test number
            String[] test = input.nextLine().split(" ");
            testNum = Integer.parseInt(test[test.length - 1]);
            //get test pass target
            String[] tTarget = input.nextLine().split(" ");
            trueTarget = Integer.parseInt(tTarget[tTarget.length - 1]);
            //get test fail target
            String[] fTarget = input.nextLine().split(" ");
            falseTarget = Integer.parseInt(fTarget[fTarget.length - 1]);
            monkeys.add(new Monkey(items, opMultiply, opNum, opDup, testNum, trueTarget, falseTarget));
            if(input.hasNext()){
                input.nextLine();
            }
        }
        /*
        for(Monkey x : monkeys){
            System.out.println(x.toString());
        }
        */

        //Monkey simulation:
        for(int round = 0; round < 20; round++){
            for(Monkey monkey : monkeys){
                monkey.act(monkeys);
            }
        }

        //print monkey business value
        int[] topinspects = new int[]{0,0};
        for(Monkey mon : monkeys){
            if(mon.getNumInspections() > topinspects[0]){
                topinspects[1] = topinspects[0];
                topinspects[0] = mon.getNumInspections();
            }
            else if(mon.getNumInspections() > topinspects[1]){
                topinspects[1] = mon.getNumInspections();
            }
        }
        System.out.println(topinspects[0] * topinspects[1]);

        input.close();
    }
}
