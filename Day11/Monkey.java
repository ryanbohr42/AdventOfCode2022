package Day11;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class Monkey {
    
    private int numInspections;
    private Queue<Integer> items;
    private boolean opMultiply; //true if op involves multiplication, false is addition
    private int opNum;
    private boolean opDup; //true if op is to reuse old value
    private int testNum;
    private int trueTarget;
    private int falseTarget;

    public Monkey(){
        numInspections = 0;
        items = new LinkedList<Integer>();
        opMultiply = false;
        opNum = 0;
        opDup = false;
        testNum = 0;
        trueTarget = 0;
        falseTarget = 0;
    }

    public Monkey(int[] newItems, boolean opType, int newOpNum, boolean isDup, int newTestNum, int tTarget, int fTarget){
        items = new LinkedList<Integer>();
        for(int x : newItems){
            items.add(x);
        }
        opMultiply = opType;
        opNum = newOpNum;
        opDup = isDup;
        testNum = newTestNum;
        trueTarget = tTarget;
        falseTarget = fTarget;
    }

    public int getNumInspections(){
        return numInspections;
    }

    public Queue<Integer> getItems(){
        return items;
    }

    public int manageWorry(int num){
        return num / 3;
    }

    public void act(List<Monkey> monkeys){
        while(items.size() > 0){
            int item = items.poll();
            if(opDup){
                if(opMultiply){
                    item *= item;
                }
                else{
                    item += item;
                }
            }
            else{
                if(opMultiply){
                    item *= opNum;
                }
                else{
                    item += opNum;
                }
            }
            item = manageWorry(item);
            if(item % testNum == 0){
                monkeys.get(trueTarget).getItems().add(item);
            }
            else{
                monkeys.get(falseTarget).getItems().add(item);
            }
            numInspections++;
        }
    }

    public String toString(){
        return String.valueOf(items.size()) + " " + String.valueOf(opMultiply) + " " + String.valueOf(opNum) + " " + 
        String.valueOf(testNum) + " " + String.valueOf(trueTarget) + " " + String.valueOf(falseTarget); 
    }
}
