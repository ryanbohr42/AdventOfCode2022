package Day13;
import java.util.Scanner;
import java.io.*;

public class D13t2 {
    public static void main(String[] args) throws FileNotFoundException{

        //File file = new File("Day13\\PacketPairs.txt");
        File file = new File("Day13\\TestInput.txt");
        Scanner input = new Scanner(file);

        
        int index = 1;
        int indexSum = 0;
        String lineOne;
        String lineTwo;
        while(input.hasNext()){
            lineOne = input.nextLine();
            if(lineOne.isEmpty()){
                index++;
                lineOne = input.nextLine();
            }
            lineTwo = input.nextLine();
            
            boolean rightOrder = true;
            int[] listDepth = new int[]{0,0};
            for(int i = 0; i < Math.min(lineOne.length(), lineTwo.length()); i++){
                char[] lineChars = new char[]{lineOne.charAt(i), lineTwo.charAt(i)};
                for(int j = 0; j < 2; j++){
                    switch(lineChars[j]){
                        case '[':
                            listDepth[j]++;
                            break;
                        case ']':
                            listDepth[j]--;
                            break;
                    }
                }
                
            }
            if(rightOrder){
                indexSum += index;
            }
        }
        System.out.println(indexSum);

        input.close();
    }
}
