package Day14;

public class CaveWallTile {
    
    private boolean empty;
    private int[] coords;

    public CaveWallTile(){
        empty = true;
        coords = new int[]{0,0};
    }

    public CaveWallTile(int[] newCoords){
        empty = true;
        coords = new int[]{newCoords[0], newCoords[1]};
    }

    public boolean isEmpty(){
        return empty;
    }

    public void fill(){
        empty = false;
    }

    public int[] getCoords(){
        return coords;
    }

}
