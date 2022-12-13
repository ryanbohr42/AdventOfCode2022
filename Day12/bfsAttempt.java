package Day12;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


public class bfsAttempt {
    public static void main(String[] args) throws FileNotFoundException{

        //For Day 12 we need to find the shortest possible path between two points from a map
        //of positions and their respective heights. Further rules are provided on the AoC site
        //BFS solution to find the shortest path

        File file = new File("Day12\\HeightMap.txt");
        //File file = new File("Day12\\TestInput.txt");
        Scanner input = new Scanner(file);

        //map input
        List<List<Node>> heightMap = new ArrayList<List<Node>>();
        Node start = new Node();
        Node destination = new Node();
        while(input.hasNext()){
            String line = input.nextLine();
            List<Node> newNodes = new ArrayList<Node>();
            for(int i = 0; i < line.length(); i++){
                newNodes.add(new Node(line.charAt(i) - 97, heightMap.size(), i));
                if(line.charAt(i) == 'S'){
                    start.setPosition(heightMap.size(), i);
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

        Queue<Node> nodeQueue = new LinkedList<Node>();
        start.setVisited();
        start.setDistance(0);
        nodeQueue.add(start);
        while(!nodeQueue.isEmpty()){
            Node node = nodeQueue.poll();
            if(node.equals(destination)){
                System.out.println(node.getDistance());
            }
            for(Node neighbor: getNeighbors(node, heightMap)){
                if(!neighbor.getVisited()){
                    neighbor.setVisited();
                    neighbor.setDistance(node.getDistance() + 1);
                    nodeQueue.add(neighbor);
                }
            }
        }
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

    public static boolean inMap(Node node, List<List<Node>> heightMap){
        int[] position = node.getPosition();
        if(position[0] >= 0 && position[0] < heightMap.size() && position[1] >= 0 && position[1] < heightMap.get(position[0]).size()){
            return true;
        }
        return false;
    }

}
