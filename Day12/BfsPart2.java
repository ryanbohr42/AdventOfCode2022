package Day12;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BfsPart2 {
    public static void main(String[] args) throws FileNotFoundException{
        
        //Part 2 adds builds on part one by now asking us to finding the shortest possible between the destination and
        //and position with the lowest possible elevation ("a"). This solution adds on to the part one solution, however
        //a faster solution would be to use the destination as the source, modify bfs behavior, and find the nearest "a"
        
        File file = new File("Day12\\HeightMap.txt");
        //File file = new File("Day12\\TestInput.txt");
        Scanner input = new Scanner(file);

        //Part 2 additions:
        Queue<Node> aQueue = new LinkedList<Node>();
        int shortestDist = Integer.MAX_VALUE;

        //map input

        List<List<Node>> heightMap = new ArrayList<List<Node>>();
        Node destination = new Node();
        while(input.hasNext()){
            String line = input.nextLine();
            List<Node> newNodes = new ArrayList<Node>();
            for(int i = 0; i < line.length(); i++){
                newNodes.add(new Node(line.charAt(i) - 97, heightMap.size(), i));
                if(line.charAt(i) == 'S' ||line.charAt(i) == 'a'){
                    aQueue.add(newNodes.get(i));
                    newNodes.get(i).setHeight(0);
                }
                else if(line.charAt(i) == 'E'){
                    destination.setPosition(heightMap.size(), i);
                    newNodes.get(i).setHeight(25);
                }
            }
            heightMap.add(newNodes);
        }
        input.close();

        while(!aQueue.isEmpty()){
            Node start = aQueue.poll();
            //BFS search
            Queue<Node> nodeQueue = new LinkedList<Node>();
            start.setVisited();
            start.setDistance(0);
            nodeQueue.add(start);
            while(!nodeQueue.isEmpty()){
                Node node = nodeQueue.poll();
                if(node.equals(destination)){
                    if(node.getDistance() < shortestDist){
                        shortestDist = node.getDistance();
                    }
                }
                for(Node neighbor: getNeighbors(node, heightMap)){
                    if(!neighbor.getVisited()){
                        neighbor.setVisited();
                        neighbor.setDistance(node.getDistance() + 1);
                        nodeQueue.add(neighbor);
                    }
                }
            }
            resetMap(heightMap);
        }

        System.out.println(shortestDist);
    }

    public static List<Node> getNeighbors(Node node, List<List<Node>> heightMap){
        
        //need to check all four possible directions
        //if the step goes off the map, do not add nonexistant node
        List<Node> neighbors = new ArrayList<Node>();
        if(node.getPosition()[0] - 1 >= 0){
            neighbors.add(heightMap.get(node.getPosition()[0] - 1).get(node.getPosition()[1])); //up
        }
        if(node.getPosition()[0] + 1 < heightMap.size()){
            neighbors.add(heightMap.get(node.getPosition()[0] + 1).get(node.getPosition()[1])); //down
        }
        if(node.getPosition()[1] - 1 >= 0){
            neighbors.add(heightMap.get(node.getPosition()[0]).get(node.getPosition()[1] - 1)); //left
        }
        if(node.getPosition()[1] + 1 < heightMap.get(node.getPosition()[0]).size()){
            neighbors.add(heightMap.get(node.getPosition()[0]).get(node.getPosition()[1] + 1)); //right
        }

        //if the height is not viable, remove neighbor
        for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).getHeight() > node.getHeight()  + 1){
                neighbors.remove(i);
                i--;
            }
        }
        
        return neighbors;
    }

    public static void resetMap(List<List<Node>> heightMap){
        for(List<Node> line : heightMap){
            for(Node node : line){
                node.reset();
            }
        }
    }

    public static boolean inMap(Node node, List<List<Node>> heightMap){
        int[] position = node.getPosition();
        if(position[0] >= 0 && position[0] < heightMap.size() && position[1] >= 0 && position[1] < heightMap.get(position[0]).size()){
            return true;
        }
        return false;
    }

}
