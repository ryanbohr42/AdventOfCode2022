package Day7;
import java.util.Map;
import java.util.HashMap;

public class DirSummary {
    
    private String name;
    private Map<String, Integer> files;
    private Map<String, DirSummary> dirList;
    private DirSummary parent;

    public DirSummary(){
        name = "";
        files = new HashMap<String, Integer>();
        dirList = new HashMap<String, DirSummary>();
        parent = null;
    }

    public DirSummary(String newName, DirSummary newParent){
        name = newName;
        files = new HashMap<String, Integer>();
        dirList = new HashMap<String, DirSummary>();
        parent = newParent;
    }

    public String getName(){
        return name;
    }

    public Map<String, Integer> getFiles(){
        return files;
    }

    public int fileSum(){
        int sum = 0;
        
        for(Integer fileSize: files.values()){
            sum += fileSize;
        }

        for(DirSummary dir : dirList.values()){
            sum += dir.fileSum();
        }
        return sum;
    }

    public Map<String, DirSummary> getDirList(){
        return dirList;
    }

    public DirSummary getParent(){
        return parent;
    }

}