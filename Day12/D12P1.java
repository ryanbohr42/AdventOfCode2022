package Day12;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class D12P1 {

    public static int shortestPath = Integer.MAX_VALUE;
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("Day12\\HeightMap.txt");
        //File file = new File("Day12\\TestInput.txt");
        Scanner input = new Scanner(file);

        //map input
        List<int[]> heightMap = new ArrayList<int[]>(); //use col in first bracket
        int[] start = new int[]{0,0};
        int[] destination = new int[]{0,0};
        while(input.hasNext()){
            String line = input.nextLine();
            int[] mapLine = new int[line.length()];
            for(int i = 0; i < mapLine.length; i++){
                mapLine[i] = line.charAt(i) - 97;
                if(line.charAt(i) == 'S'){
                    start[0] = heightMap.size();
                    start[1] = i;
                    mapLine[i] = 0;
                }
                else if(line.charAt(i) == 'E'){
                    destination[0] = heightMap.size();
                    destination[1] = i;
                    mapLine[i] = 25;
                }
            }
            heightMap.add(mapLine);
        }

        //Shortest Path calc
        List<int[]> path =  new ArrayList<int[]>();
        path.add(start);
        findShortestPath(heightMap, destination, path);
        System.out.println(shortestPath);

        input.close();
    }

    public static void findShortestPath(List<int[]> heightMap, int[] dest, List<int[]> path){
        //check if curPosition equals dest, and if it does, check if shortpath needs updating
        //add all possible next moveLocations to a list, sans going to previous location
        //  can't think of how shortest path would include going backwards
        //recursively call findShortestPath
        //end early if a path has been found and branch's path is too long
        //  Checking curPosition vs dest can find the min possible steps left
        //  If the minSteps would have this branch still be greater than an already found path, return
        //Not necessarily in this order
        
        //check branch viability
        if(path.size() + minStepsToReach(dest, path.get(path.size() - 1)) >= shortestPath){
            return;
        }

        //check if destination has been reached 
        if(path.get(path.size() - 1)[0] == dest[0] && path.get(path.size() - 1)[1] == dest[1]){
            if(path.size() - 1 < shortestPath){
                shortestPath = path.size() - 1;
                return;
            }
        }

        //find next possible moves
        List<int[]> nextSteps = new ArrayList<int[]>();
        generateSteps(heightMap, path, nextSteps, dest);

        //advance down paths
        for(int[] nextStep : nextSteps){
            path.add(nextStep);
            findShortestPath(heightMap, dest, path);
            path.remove(path.size() - 1);
        }
    }

    public static void generateSteps(List<int[]> heightMap, List<int[]> path, List<int[]> nextSteps, int[] dest){
        //need to check all four possible directions
        //if the step goes off the map, the height is not viable, or the step was already part of the path, do not add
        //last, do not descend in height more than 1 unit, but may want to remove

        int[][] newSteps = new int[4][];
        newSteps[0] = new int[]{path.get(path.size() - 1)[0] - 1, path.get(path.size() - 1)[1]}; //up
        newSteps[1] = new int[]{path.get(path.size() - 1)[0] + 1, path.get(path.size() - 1)[1]}; //down
        newSteps[2] = new int[]{path.get(path.size() - 1)[0], path.get(path.size() - 1)[1] - 1}; //left
        newSteps[3] = new int[]{path.get(path.size() - 1)[0], path.get(path.size() - 1)[1] + 1}; //right

        /*
        for(int[] step : newSteps){
            if(step[0] >= 0 && step[0] < heightMap.size() && step[1] >= 0 && step[1] < heightMap.get(0).length){
                if(heightMap.get(step[0])[step[1]] <= heightMap.get(path.get(path.size() - 1)[0])[path.get(path.size() - 1)[1]] + 1){
                    if(heightMap.get(step[0])[step[1]] >= heightMap.get(path.get(path.size() - 1)[0])[path.get(path.size() - 1)[1]] - 1){
                        if(!repeatStep(step, path)){
                            nextSteps.add(new int[]{step[0], step[1]});
                        }
                    }
                }
            }
        }*/

        for(int[] step : newSteps){
            if(step[0] >= 0 && step[0] < heightMap.size() && step[1] >= 0 && step[1] < heightMap.get(0).length){
                if(heightMap.get(step[0])[step[1]] <= heightMap.get(path.get(path.size() - 1)[0])[path.get(path.size() - 1)[1]] + 1){
                    if(!repeatStep(step, path)){
                            nextSteps.add(new int[]{step[0], step[1]});
                    }
                }
            }
        }

        //need to reorder steps based on how much close they get the path to the destination
        for(int i = 0; i < nextSteps.size(); i++){
            for(int j = i + 1; j < nextSteps.size(); j++){
                if(minStepsToReach(nextSteps.get(i), dest) > minStepsToReach(nextSteps.get(j), dest)){
                    int[] temp = new int[]{nextSteps.get(i)[0], nextSteps.get(i)[1]};
                    nextSteps.get(i)[0] = nextSteps.get(j)[0];
                    nextSteps.get(i)[1] = nextSteps.get(j)[1];
                    nextSteps.get(j)[0] = temp[0];
                    nextSteps.get(j)[1] = temp[1];
                }
            }
        }
        return;
    }

    public static boolean repeatStep(int[] newStep, List<int[]> path){
        for(int[] step : path){
            if(step[0] == newStep[0] && step[1] == newStep[1]){
                return true;
            }
        }
        
        /*
        for(int i = Math.max(0, path.size() - 100); i < path.size(); i++){
            if(path.get(i)[0] == newStep[0] && path.get(i)[1] == newStep[1]){
                return true;
            }
        }
        */
        
        return false;
    }

    public static int minStepsToReach(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}