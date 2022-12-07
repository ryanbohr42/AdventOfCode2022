package Day7;
import java.util.Scanner;
import java.io.*;

public class D7P1 {
    public static void main(String[] args) throws FileNotFoundException{
        //Problem involves interpreting a log of file system commands to find the sum of the sizes
        //of all the directories in the file system that have total data sizes of up to 100000.
        //First we'll read through the commands to build a replica of the file system.
        //Then we'll traverse this replica recursively to find the sum.
        
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
        System.out.println(root.fileSum());

        //Part Two: Finding the sum of all directories that have a total size of <= 100000
        int ans = findDirSizeSum(root);
        int rootSize = root.fileSum();
        if(rootSize <= 100000){
            ans += rootSize;
        }
        System.out.println(ans);

        input.close();
    }

    public static int findDirSizeSum(DirSummary dir){
        int sum = 0;
        for(DirSummary subDir : dir.getDirList().values()){
            sum += findDirSizeSum(subDir);
            int dirSize = subDir.fileSum();
            if(dirSize <= 100000){
                sum += dirSize;
            }
        }
        return sum;
    }
}