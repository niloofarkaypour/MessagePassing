import java.util.List;
import  java.util.ArrayList;
import java.util.Random;

public class calling extends Thread {

    exchange master = new exchange();
    List<String> temp = new ArrayList<>();
    public int counter = 0;

    public calling(List<String> temp) {
        this.temp = temp;
    }
    public void run() {

        synchronized (temp) {
            try {
                sleep(new Random().nextInt(100) + 1);
            } catch (InterruptedException e) {
            }

            for (int i = 1; i < temp.size(); i++) {

                long currentTime = System.nanoTime();

                String introMsg = temp.get(i) + " got intro message from " + temp.get(0) + " [" + currentTime + "]";
                master.introMsgPrint(introMsg);

                String replyMsg = temp.get(0) + " got reply message from " + temp.get(i) + " [" + currentTime + "]";
                master.replyMsgPrint(replyMsg);
            }
            terminateProcess();
        }
    }

    public void terminateProcess(){

        if(counter < temp.size()) {
            counter++;
            synchronized (temp) {

                try {
                    temp.wait(5000);
                    String processMsg = "Process " + temp.get(0) + " has received no calls for 5 second, ending...";
                    master.processTerminateMsgPrint(processMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void terminateMaster(){
        try {
            sleep(10000);
            String masterMsg = "Master has received no replies for 10 seconds, ending...";
            master.masterTerminateMsgPrint(masterMsg);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}