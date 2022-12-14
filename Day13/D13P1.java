package Day13;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class D13P1 {
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

            if(isRightOrder(lineOne, lineTwo)){
                indexSum += index;
            }
        }
        System.out.println(indexSum);

        /*
        List<Object> test = new ArrayList<Object>();
        Integer walla = Integer.valueOf(8);
        test.add(walla);
        System.out.println(test.get(0).getClass());
        */

        input.close();
    }

    //Handles checking of packets after they are built from text input
    public static boolean isRightOrder(String lineOne, String lineTwo){
        List<Object> packetOne = makeList(lineOne.substring(1, lineOne.length() - 1));
        List<Object> packetTwo = makeList(lineTwo.substring(1, lineTwo.length() - 1));



        return true;
    }

    //builds packet from text input
    public static List<Object> makeList(String line){
        List<Object> newList = new ArrayList<Object>();
        for(int i = 0; i < line.length(); i++){
            char symbol = line.charAt(i);
        }
        return null;
    }

}
