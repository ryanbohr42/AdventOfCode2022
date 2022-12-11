package Day11;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class MonkeyP2 {
    private long numInspections;
    private Queue<Long> items;
    private boolean opMultiply; //true if op involves multiplication, false is addition
    private long opNum;
    private boolean opDup; //true if op is to reuse old value
    private long testNum;
    private long trueTarget;
    private long falseTarget;
    private long lcm;

    public MonkeyP2(){
        numInspections = 0;
        items = new LinkedList<Long>();
        opMultiply = false;
        opNum = 0;
        opDup = false;
        testNum = 0;
        trueTarget = 0;
        falseTarget = 0;
    }

    public MonkeyP2(long[] newItems, boolean opType, long newOpNum, boolean isDup, long newTestNum, long tTarget, long fTarget){
        items = new LinkedList<Long>();
        for(long x : newItems){
            items.add(x);
        }
        opMultiply = opType;
        opNum = newOpNum;
        opDup = isDup;
        testNum = newTestNum;
        trueTarget = tTarget;
        falseTarget = fTarget;
    }

    public long getNumInspections(){
        return numInspections;
    }

    public void setLCM(long num){
        lcm = num;
    }

    public Queue<Long> getItems(){
        return items;
    }

    public long manageWorry(long num){
        return num % lcm;
        //return num / 3;
    }

    public void act(List<MonkeyP2> monkeys){
        while(items.size() > 0){
            long item = items.poll();
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
                monkeys.get((int)trueTarget).getItems().add(item);
            }
            else{
                monkeys.get((int)falseTarget).getItems().add(item);
            }
            numInspections++;
        }
    }

    public String toString(){
        return String.valueOf(items.size()) + " " + String.valueOf(opMultiply) + " " + String.valueOf(opNum) + " " + 
        String.valueOf(testNum) + " " + String.valueOf(trueTarget) + " " + String.valueOf(falseTarget); 
    }
}
