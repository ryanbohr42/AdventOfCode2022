package Day11;
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class D11P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem requires our program to simulate the behavior of eight different monkeys over
        //the course of 20 rounds in which they each act
        
        //File file = new File("Day11\\Monkeys.txt");
        File file = new File("Day11\\TestInput.txt");
        Scanner input = new Scanner(file);

        //Monkey List initialization:
        List<MonkeyP2> monkeys = new LinkedList<MonkeyP2>();
        long[] items;
        boolean opMultiply;
        long opNum;
        boolean opDup;
        long testNum;
        long trueTarget;
        long falseTarget;
        long[] testNums = new long[4];
        for(int monkey = 0; monkey < 4; monkey++){
            //"Monkey #" line
            input.nextLine();
            //get list of item values
            String[] itemValues = input.nextLine().trim().substring(15).split(",");
            items = new long[itemValues.length];
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
            testNums[monkey] = testNum;
            //get test pass target
            String[] tTarget = input.nextLine().split(" ");
            trueTarget = Integer.parseInt(tTarget[tTarget.length - 1]);
            //get test fail target
            String[] fTarget = input.nextLine().split(" ");
            falseTarget = Integer.parseInt(fTarget[fTarget.length - 1]);
            monkeys.add(new MonkeyP2(items, opMultiply, opNum, opDup, testNum, trueTarget, falseTarget));
            if(input.hasNext()){
                input.nextLine();
            }
        }
        
        long lcm = findLCM(testNums);
        for(MonkeyP2 monkey : monkeys){
            monkey.setLCM(lcm);
        }

        //Monkey simulation:
        for(int round = 0; round < 10000; round++){
            System.out.print("Round: " + round + ", ");
            for(MonkeyP2 monkey : monkeys){
                monkey.act(monkeys);
                System.out.print(monkey.getNumInspections() + " ");
            }
            System.out.println();
        }

        //prlong monkey business value
        long[] topinspects = new long[]{0,0};
        for(MonkeyP2 mon : monkeys){
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

    //code to find LCD and GCD from kemmel-dev on git-hub
    public static long findLCM(long[] testNums) 
    {
        long ans = testNums[0];
        for (int i = 1; i < testNums.length; i++)
        {
            ans = testNums[i] * ans / findGCD(testNums[i], ans);
        }
        return ans;
    }

    public static long findGCD(long a, long b) { return b == 0 ? a : findGCD(b, a % b); } 

}
