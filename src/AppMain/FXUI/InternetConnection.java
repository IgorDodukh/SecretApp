package AppMain.FXUI;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Ihor on 8/26/2016. All rights reserved!
 */
class InternetConnection {
    private static String failedHeaderText = "Test was not starter.";
    private static String failedContentText = "Please check your Internet connection.";

    static String getFailedContentText() {
        return failedContentText;
    }

    boolean checkInternetConnection(){
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
