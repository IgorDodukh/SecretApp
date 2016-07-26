package FXUI;

/**
 * Created by Ihor on 7/15/2016.
 */
public class ExecutionTimeCounter {
    static long start = 0;
    static long stop = 0;
    static String executionTime = "";

       public static void startCounter() {
           start = System.currentTimeMillis();
        }

       public static void stopCounter() {
           stop = System.currentTimeMillis();
           long diffMsec = stop -  start;
           long diffSec = diffMsec / 1000;

           if(diffSec<60){
               executionTime = String.valueOf(diffSec)  + " sec.";
           } else {
               long diffMins = diffSec / 60;
               executionTime = String.valueOf(diffMins) + " min. " + (diffSec - ((diffMins - 1)*60) - 60) + " sec.";
           }
        }
}
