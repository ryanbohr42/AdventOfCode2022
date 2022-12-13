package Day12;

public class Node {
    
    private int distance;
    private boolean visited;
    private int height;
    private int[] position;

    public Node(){
        distance = Integer.MAX_VALUE;
        visited = false;
        height = 0;
        position = new int[]{0,0};
    }

    public Node(int xHeight, int row, int col){
        distance = Integer.MAX_VALUE;
        visited = false;
        height = xHeight;
        position = new int[]{row, col};
    }

    public int getDistance(){
        return distance;
    }

    public void setDistance(int xDistance){
        distance = xDistance;
    }

    public boolean getVisited(){
        return visited;
    }

    public void setVisited(){
        visited = true;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int xHeight){
        height = xHeight;
    }

    public int[] getPosition(){
        return position;
    }

    public void setPosition(int row, int col){
        position[0] = row;
        position[1] = col;
    }

    public void reset(){
        distance = Integer.MAX_VALUE;
        visited = false;
    }

    public boolean equals(Node otherNode){
        if(otherNode.getPosition()[0] == this.position[0] && otherNode.getPosition()[1] == this.position[1]){
            return true;
        }
        return false;
    }

}
