package FXUI;

import javafx.scene.control.*;

import java.io.File;

/**
 * Created by Igor on 8/20/2016. All rights reserved!
 */

public class AppStyles {
    public static String mainPath = "C:\\appFiles";

    public void setDialogStyle(Dialog dialog) {
        try {
            File f = new File(mainPath + "\\styles\\DialogBoxes.css");
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
    }

    public static void setComboBoxStyle(ComboBox comboBox) {
        try {
            File f = new File(mainPath + "\\styles\\comboBoxes.css");
            comboBox.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
    }

    public static void setButtonsStyle(Button button) {
        try {
            File f = new File(mainPath + "\\styles\\buttons.css");
            button.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
    }

    public static void setMenuBarStyle(MenuBar menuBar) {
        try {
            File f = new File(mainPath + "\\styles\\MainStyle.css");
            menuBar.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        } catch (Exception e){
            System.out.println(e.getClass().toString() + "\n" +  e.getLocalizedMessage());
        }
    }

    public void setDialogLogo(Dialog dialog, String logoName) {
        dialog.getDialogPane().setStyle(
                "-fx-graphic: url(\"file:/" + mainPath.replace("\\", "/") +  "/pic/" + logoName + "\")");
    }
}
