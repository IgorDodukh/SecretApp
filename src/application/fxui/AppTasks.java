package application.fxui;

import javafx.concurrent.Task;

/**
 * Created by Ihor on 4/25/2017.
 */
public class AppTasks {

    private String updateValue;

    public AppTasks(String updateValue){
        this.updateValue = updateValue;
    }


    Task updateLabel() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    updateMessage(updateValue);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
    }
}
