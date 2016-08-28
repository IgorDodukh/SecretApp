package FXUI;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Ihor on 8/26/2016. All rights reserved!
 */
public class InternetConnection {
    private static String failedHeaderText = "Test was not starter.";
    private static String failedContentText = "Please check your internet connection.";

    public static String getFailedHeaderText() {
        return failedHeaderText;
    }
    
    public static void setFailedHeaderText(String failedHeaderText) {
        InternetConnection.failedHeaderText = failedHeaderText;
    }

    public static String getFailedContentText() {
        return failedContentText;
    }

    public static void setFailedContentText(String failedContentText) {
        InternetConnection.failedContentText = failedContentText;
    }

    public boolean checkInternetConnection(){
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try {
            sock.connect(address, 3000);
            if(sock.isConnected()){
                status=true;
            }
        } catch(Exception e){
            System.out.println("Test Connection in catch exception: " + e.getMessage());
        } finally{
            try {
                sock.close();
            } catch(Exception e){
                System.out.println("Test Connection in finally exception: " + e.getMessage());
            }
        }
        return status;
    }
}
