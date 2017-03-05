package FXUI;

/**
 * Created by Ihor on 7/15/2016. All rights reserved!
 */
class ExecutionTimeCounter {
    private static long start = 0;
    static String executionTime = "";

       static void startCounter() {
           start = System.currentTimeMillis();
        }

       static void stopCounter() {
           long stop = System.currentTimeMillis();
           long diffMs = stop -  start;
           long diffSec = diffMs / 1000;

           if(diffSec<60){
               executionTime = String.valueOf(diffSec)  + " sec.";
           } else {
               long diffMin = diffSec / 60;
               executionTime = String.valueOf(diffMin) + " min. " + (diffSec - ((diffMin - 1)*60) - 60) + " sec.";
           }
        }
}
