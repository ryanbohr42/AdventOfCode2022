package Day8;
import java.util.Scanner;
import java.io.*;

public class D8P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //For part two we need to find the tree in the forest with the best possible scenic score
        //This is calulated by multiplying together the number of trees visible in each direction from a given tree
        //A solution for this part can be found using the same inspection setup as part one  
        
        File file = new File("Day8\\TreeHeights.txt");
        //File file = new File("Day8\\TestInput.txt");
        Scanner input = new Scanner(file);

        //Reading in input and initializing a 2D array to represent the forest
        String trees = input.nextLine();
        int[][] forest = new int[trees.length()][trees.length()];
        for(int tree = 0; tree < trees.length(); tree++){
            forest[0][tree] = trees.charAt(tree) - 48;
        }

        for(int line = 1; line < forest.length; line++){
            trees = input.nextLine();
            for(int tree = 0; tree < trees.length(); tree++){
                forest[line][tree] = trees.charAt(tree) - 48;
            }
        }

        //Finding the best scenic score
        int bestScenicScore = 0;
        for(int row = 0; row < forest.length; row++){
            for(int col = 0; col < forest.length; col++){
                bestScenicScore = Math.max(bestScenicScore, findBSS(forest, row, col));
            }
        }
        System.out.println(bestScenicScore);

        input.close();
    }

    public static int findBSS(int[][] forest, int row, int col){
        //need to check all four directions
        //left count
        int leftTrees = 0;
        for(int i = col - 1; i >= 0; i--){
            if(forest[row][col] > forest[row][i]){
                leftTrees++;
            }
            else{
                leftTrees++;
                break;
            }
        }

        //top count
        int topTrees = 0;
        for(int i = row - 1; i >= 0; i--){
            if(forest[row][col] > forest[i][col]){
                topTrees++;
            }
            else{
                topTrees++;
                break;
            }
        }

        //right count
        int rightTrees = 0;
        for(int i = col + 1; i < forest.length; i++){
            if(forest[row][col] > forest[row][i]){
                rightTrees++;
            }
            else{
                rightTrees++;
                break;
            }
        }

        //bottom count
        int bottomTrees = 0;
        for(int i = row + 1; i < forest.length; i++){
            if(forest[row][col] > forest[i][col]){
                bottomTrees++;
            }
            else{
                bottomTrees++;
                break;
            }
        }

        return leftTrees*topTrees*rightTrees*bottomTrees;
    }
}
