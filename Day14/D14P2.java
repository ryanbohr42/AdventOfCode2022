package Day14;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class D14P2 {
    public static void main(String[] args) throws FileNotFoundException{
        
        //File file = new File("Day14\\TestInput.txt");
        File file = new File("Day14\\CaveWall.txt");
        Scanner input = new Scanner(file);
    
        //Finding floor y position
        int floor = 0;
        while(input.hasNext()){
            String[] path = input.nextLine().split(" -> ");
            for(String point : path){
                int yCoord = Integer.parseInt(point.split(",")[1]);
                if(yCoord + 2 > floor){
                    floor = yCoord + 2;
                }
            }
        }
        input.close();

        //cave wall initialization
        List<List<CaveWallTile>> caveWall = new ArrayList<List<CaveWallTile>>();
        for(int x = 0; x < 1200; x++){
            List<CaveWallTile> caveCol = new ArrayList<CaveWallTile>();
            for(int y = 0; y < 200; y++){
                caveCol.add(new CaveWallTile(new int[]{x,y}));
                if(y == floor){
                    caveCol.get(y).fill();
                }
            }
            caveWall.add(caveCol);
        }

        //input handling / rock placement
        input = new Scanner(file);
        while(input.hasNext()){
            String[] rockPath = input.nextLine().split(" -> ");
            if(rockPath.length == 0) break;

            for(int point = 0; point < rockPath.length - 1; point++){
                String[] start = rockPath[point].split(",");
                String[] dest = rockPath[point+1].split(",");
                int[] startCoords = new int[]{Integer.parseInt(start[0]), Integer.parseInt(start[1])};
                int[] destCoords = new int[]{Integer.parseInt(dest[0]), Integer.parseInt(dest[1])};
                drawRocks(caveWall, startCoords, destCoords);
            }
        }
        input.close();

        /*
        for(int i = 0; i < 12; i++){
            for(int j = 490; j < 505; j++){
                System.out.print(caveWall.get(j).get(i).isEmpty() ? "." : "#");
            }
            System.out.println();
        }
        */

        //sand fall sim
        int atRestSand = 0;
        boolean end = false;
        while(!end){
            int[] sandGrain = new int[]{500,0};
            int result = 1;
            if(!caveWall.get(500).get(0).isEmpty()){
                break;
            }
            while(result > 0){
                result = moveGrain(caveWall, sandGrain);
                if(result == -1){
                    end = true;
                }
                if(result == 0){
                    atRestSand++;
                }
            }
        }
        System.out.println(atRestSand);
    }

    public static void drawRocks(List<List<CaveWallTile>> caveWall, int[] startCoords, int[] destCoords){
        //use start and dest coords to fill in cavewall tiles with rocks
        while(startCoords[0] != destCoords[0] || startCoords[1] != destCoords[1]){
            caveWall.get(startCoords[0]).get(startCoords[1]).fill();
            //check if the rockPath is vertical or horizontal
            if(startCoords[0] == destCoords[0]){
                //startCoords[1] += (startCoords[1] > destCoords[1]);
                startCoords[1] += (startCoords[1] < destCoords[1]) ? 1 : -1;
            }
            else{
                startCoords[0] += (startCoords[0] < destCoords[0]) ? 1 : -1;
            }
        }
        caveWall.get(startCoords[0]).get(startCoords[1]).fill();
    }

    public static int moveGrain(List<List<CaveWallTile>> caveWall, int[] sandGrain){
        //tries to move sand piece if possible, returning 0 if not possible, and -1 if the sand falls off the wall

        //at bottom of wall
        if(sandGrain[1] == caveWall.get(0).size() - 1){
            return -1;
        }
        //check for movement, return zero if not possible
        //check directly below
        if(caveWall.get(sandGrain[0]).get(sandGrain[1] + 1).isEmpty()){
            sandGrain[1] += 1;
            return 1;
        }
        //check diagonalleft if possible
        else if(sandGrain[0] >= 0 && caveWall.get(sandGrain[0] - 1).get(sandGrain[1] + 1).isEmpty()){
            sandGrain[0] -= 1;
            sandGrain[1] += 1;
            return 1;
        }
        //check right diagonal if possible
        else if(sandGrain[0] < caveWall.size() - 1 && caveWall.get(sandGrain[0] + 1).get(sandGrain[1] + 1).isEmpty()){
            sandGrain[0] += 1;
            sandGrain[1] += 1;
            return 1;
        }
        //now at rest
        else{
            caveWall.get(sandGrain[0]).get(sandGrain[1]).fill();
            return 0;
        }
    }
}
