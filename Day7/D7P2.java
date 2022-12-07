package Day7;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class D7P2 {
    public static void main(String[] args) throws FileNotFoundException{
        //Part two relies on the same file system as part one, but what the system is used for is different
        //This time we need to find the total sizes of each directory, and then report the size of the smallest
        //directory where dirSize + free space >= 30000000.
        //Free space is determined by finding the size of the root directory, and then subtracting that from
        //the given number of 70000000.
        
        File file = new File("Day7\\ElfFileSystem.txt");
        //File file = new File("Day7\\testInput.txt");
        Scanner input = new Scanner(file);

        //Part One: file system replication
        DirSummary root = new DirSummary("/", null);
        DirSummary curDirectory = root;
        while(input.hasNext()){
            //System.out.println(curDirectory.getName());
            String[] logLine = input.nextLine().split(" ");
            //System.out.println(logLine[0] + " " + logLine[1]);
            switch(logLine[0]){
                case "$": //indicates a command
                    if(logLine[1].equals("cd")){
                        switch(logLine[2]){
                            case "/": //means that curDir should be sent back to root
                                curDirectory = root;
                                break;
                            case "..": //sends curDir one level up the tree
                                curDirectory = curDirectory.getParent();
                                break;
                            default: //if neither of the previous cases trigger, the argument is for a specific dir
                                curDirectory = curDirectory.getDirList().get(logLine[2]);
                                break;
                        }
                    }
                    break;
                case "dir": //indicates a listed directory
                    curDirectory.getDirList().putIfAbsent(logLine[1], new DirSummary(logLine[1], curDirectory));
                    break;
                default: //only other option is an integer file size
                    curDirectory.getFiles().putIfAbsent(logLine[1], Integer.parseInt(logLine[0]));
                    break;
            }
        }
        //System.out.println(root.fileSum());

        //Part Two: Finding the size of the best directory to delete, as explained above
        ArrayList<Integer> dirSizes = new ArrayList<Integer>();
        findDirSizes(root, dirSizes);
        int rootSize = root.fileSum();
        int freeSpace = 70000000 - rootSize;
        dirSizes.add(rootSize);
        Collections.sort(dirSizes);
        
        for(Integer size: dirSizes){
            if(size + freeSpace >= 30000000){
                System.out.println(size);
                break;
            }
        }

        input.close();
    }

    public static void findDirSizes(DirSummary dir, ArrayList<Integer> sizeList){
        for(DirSummary subDir : dir.getDirList().values()){
            findDirSizes(subDir, sizeList);
            sizeList.add(subDir.fileSum());
        }
    }
}