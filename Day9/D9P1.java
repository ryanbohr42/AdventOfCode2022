package Day9;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

public class D9P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem involves tracking the movement of a tail of a rope as the head moves in a grid
        //according to orders provided by the input.
        //The answer is then how many unique positions the tail visited
        //Problems:
        //  1. No way of knowing how far in any direction the rope will move -> use coords, not a 2D array
        //  2. How to check if tail needs to move -> adjacency check by looking at coords
        //  3. How to update tail's position properly if required
        //  4. Once tail moves, add and track it's new 2D position so that it is only ever counted once
        //     it is first visited -> set of Strings

        File file = new File("Day9\\RopeMovements.txt");
        //File file = new File("Day9\\TestInputDay9.txt");
        Scanner input = new Scanner(file);

        int[] headCoords = new int[]{0, 0};
        int[] tailCoords = new int[]{0, 0};
        Set<String> uniquePos = new HashSet<String>();
        uniquePos.add(Integer.toString(tailCoords[0]) + "," + Integer.toString(tailCoords[1]));

        while(input.hasNext()){
            String[] movement = input.nextLine().split(" ");
            int numSteps = Integer.parseInt(movement[1]);
            while(numSteps > 0){
                updateHead(headCoords, movement);
                if(!checkAdjacency(headCoords, tailCoords)){
                    moveTail(headCoords, tailCoords);
                    uniquePos.add(Integer.toString(tailCoords[0]) + "," + Integer.toString(tailCoords[1]));
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
