package Day8;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.io.*;

public class D8P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //This problem wants us to count the number of trees in a grid that are visible from some point on the
        //outside of the grid in an vertical or horizontal way
        //Brute force way: go to a tree, check each direction for visibility, move on when/if found
        //  Grab all the tree heights, add them to a set, get the values, sort them, see if inspected
        //  tree is taller than any the set.
        //More efficient: inspect trees from outside in keeping track of the tallest tree in any direction
        //  from inspected tree, possibly reducing the number of full direction inpections required 
        
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

        //Counting the number of visible trees -> set method
        int visibleTrees = 4*forest.length - 4; //trees on outside edge are immediately visible
        for(int row = 1; row < forest.length - 1; row++){
            for(int col = 1; col < forest.length - 1; col++){
                if(checkVisibilityBrute(forest, row, col)){
                    visibleTrees++;
                }
            }
        }
        System.out.println(visibleTrees);

        input.close();
    }

    public static boolean checkVisibilityBrute(int[][] forest, int row, int col){
        //need to check all four directions
        //left check
        Set<Integer> blockingTrees = new HashSet<Integer>();
        for(int i = 0; i < col; i++){
            blockingTrees.add(forest[row][i]);
        }
        Integer[] heights = blockingTrees.toArray(new Integer[blockingTrees.size()]);
        Arrays.sort(heights);
        if(heights[heights.length - 1] < forest[row][col]){
            return true;
        }

        //top check
        blockingTrees = new HashSet<Integer>();
        for(int i = 0; i < row; i++){
            blockingTrees.add(forest[i][col]);
        }
        heights = blockingTrees.toArray(new Integer[blockingTrees.size()]);
        Arrays.sort(heights);
        if(heights[heights.length - 1] < forest[row][col]){
            return true;
        }

        //right check
        blockingTrees = new HashSet<Integer>();
        for(int i = forest.length - 1; i > col; i--){
            blockingTrees.add(forest[row][i]);
        }
        heights = blockingTrees.toArray(new Integer[blockingTrees.size()]);
        Arrays.sort(heights);
        if(heights[heights.length - 1] < forest[row][col]){
            return true;
        }

        //bottom check
        blockingTrees = new HashSet<Integer>();
        for(int i = forest.length - 1; i > row; i--){
            blockingTrees.add(forest[i][col]);
        }
        heights = blockingTrees.toArray(new Integer[blockingTrees.size()]);
        Arrays.sort(heights);
        if(heights[heights.length - 1] < forest[row][col]){
            return true;
        }

        return false;
    }
}
