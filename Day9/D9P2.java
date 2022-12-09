package Day9;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

public class D9P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //Part two adds to the complexity of the problem by simulating a rope with 10 knots instead
        //of just the two in part one
        //Will need an array of knots that each update in sequence when the head of the tail moves

        File file = new File("Day9\\RopeMovements.txt");
        //File file = new File("Day9\\TestInputDay9.txt");
        //File file = new File("Day9\\TestInputD9P2.txt");
        Scanner input = new Scanner(file);

        int[][] knots = new int[10][];
        for(int i = 0; i < 10; i++){
            knots[i] = new int[]{0,0};
        }
        Set<String> uniquePos = new HashSet<String>();
        uniquePos.add(Integer.toString(knots[9][0]) + "," + Integer.toString(knots[9][1]));

        while(input.hasNext()){
            String[] movement = input.nextLine().split(" ");
            int numSteps = Integer.parseInt(movement[1]);
            while(numSteps > 0){
                updateHead(knots[0], movement);
                for(int knot = 0; knot < knots.length - 1; knot++){
                    if(!checkAdjacency(knots[knot], knots[knot+1])){
                        moveTail(knots[knot], knots[knot+1]);
                        uniquePos.add(Integer.toString(knots[9][0]) + "," + Integer.toString(knots[9][1]));
                    }
                }
                numSteps--;
            }
        }

        System.out.println(uniquePos.size());
        input.close();
    }

    public static void updateHead(int[] coords, String[] move){
        String direction = move[0];
        switch(direction){
            case "U":
                coords[1] = coords[1] + 1;
                break;
            case "D":
                coords[1] = coords[1] - 1;
                break;
            case "L":
                coords[0] = coords[0] - 1;
                break;
            case "R":
                coords[0] = coords[0] + 1;
                break;
            default:
                break;
        }
    }

    public static boolean checkAdjacency(int[] headCoords, int[] tailCoords){
        if(Math.abs(headCoords[0] - tailCoords[0]) > 1 || Math.abs(headCoords[1] - tailCoords[1]) > 1){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static void moveTail(int[] headCoords, int[] tailCoords){
        //check if head and tail have the same row (y coord) for horizontal move
        if(tailCoords[1] == headCoords[1]){
            if(headCoords[0] > tailCoords[0]){ //need to move right
                tailCoords[0] += 1;
            }
            else{ //otherwise, need to move left
                tailCoords[0] -= 1;
            }
                return;
        }
        //check if head and tail have the same column (x coord) for vertical move
        else if(tailCoords[0] == headCoords[0]){
            if(headCoords[1] > tailCoords[1]){ //need to move right
                tailCoords[1] += 1;
            }
            else{ //otherwise, need to move left
                tailCoords[1] -= 1;
            }
            return;
        }
        //diagonal move required
        else{
            if(headCoords[0] > tailCoords[0]){
                if(headCoords[1] > tailCoords[1]){
                    tailCoords[0] += 1;
                    tailCoords[1] += 1;
                }
                else{
                    tailCoords[0] += 1;
                    tailCoords[1] -= 1;
                }
            }
            else{
                if(headCoords[1] > tailCoords[1]){
                    tailCoords[0] -= 1;
                    tailCoords[1] += 1;
                }
                else{
                    tailCoords[0] -= 1;
                    tailCoords[1] -= 1;
                }
            }
        }
        return;
    }

}
