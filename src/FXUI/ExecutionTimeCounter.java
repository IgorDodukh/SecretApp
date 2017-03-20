package FXUI;

/**
 * Created by Ihor on 7/15/2016. All rights reserved!
 */
class ExecutionTimeCounter {
    private static long start = 0;
    static String executionTime = "";

       public static void startCounter() {
           start = System.currentTimeMillis();
        }

       public static void stopCounter() {
           long stop = System.currentTimeMillis();
           long diffMsec = stop -  start;
           long diffSec = diffMsec / 1000;


           executionTime = String.valueOf(diffMsec) + " msec.";
//           if(diffSec<60){
//               executionTime = String.valueOf(diffSec)  + " sec.";
//           } else {
//               long diffMins = diffSec / 60;
//               executionTime = String.valueOf(diffMins) + " min. " + (diffSec - ((diffMins - 1)*60) - 60) + " sec.";
//           }

        }
}
