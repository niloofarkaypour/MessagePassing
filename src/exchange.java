import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class exchange{

    public static List<List<String>> new_l = new ArrayList<>();
    public static String calls = "";
    public static List<String> readFileInList(String fileName){

        List<String> lines = Collections.emptyList();
        try{
            lines = Files.readAllLines(Paths.get("C:/Users/Niloofar/IdeaProjects/Java-COMP6411/src/calls.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args){

        List<String> l = readFileInList("C:/Users/Niloofar/IdeaProjects/Java-COMP6411/src/calls.txt");

        for(String data: l){

            List<String> temp = Arrays.asList(data.replaceAll("[.{}\\[\\]]", "").split("\\W+"));
            new_l.add(temp);

        }
        System.out.println("** Calls to be made **");

        for(int i=0;i<new_l.size();i++){

            for(int j=0;j<=i;j++){

                calls = new_l.get(i).get(0) + ": " +new_l.get(i).subList(1,new_l.get(i).size());
            }
            System.out.println(calls);
        }

        System.out.println();

        for(int i=0;i<new_l.size();i++) {
            Thread initial_thread = new Thread(new calling(new_l.get(i)));
            initial_thread.start();
        }
        terminateMaster();

    }
    public static void terminateMaster(){
        try {
            sleep(10000);
            String masterMsg = "Master has received no replies for 10 seconds, ending...";
            masterTerminateMsgPrint(masterMsg);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void introMsgPrint(String msg){
        System.out.println(msg);
    }

    public static void replyMsgPrint(String msg){
        System.out.println(msg);
    }

    public static void processTerminateMsgPrint(String msg){
        System.out.println(msg);
    }

    public  static void masterTerminateMsgPrint(String msg){
        System.out.println(msg);
    }

}
