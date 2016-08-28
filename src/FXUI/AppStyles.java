package FXUI;

import Settings.GetSystemFiles;
import javafx.scene.control.*;

import java.io.*;

import static FXUI.GeneratePopupBox.warningPopupBox;

/**
 * Created by Igor on 8/20/2016. All rights reserved!
 */

public class AppStyles extends GetSystemFiles {
    private static InputStream fileStream;

    public static String mainPath = "C:\\appFiles";

    public void setDialogCancelButtonStyle(Dialog dialog) {
        ButtonBar buttonBar = (ButtonBar)dialog.getDialogPane().lookup(".button-bar");
        buttonBar.getButtons().forEach(b -> {
            if(b.toString().contains("Cancel")) {
                b.setStyle("-fx-background-color: \n" +
                        "        linear-gradient(from 0% 83% to 0% 100%, #ff5b54 0%, #c12e2a 100%),\n" +
                        "        radial-gradient(center 50% 50%, radius 100%, #ff5b54, #c12e2a);");
            }
            b.setOnMouseEntered(button ->{
                if(b.toString().contains("Cancel")) {
                    b.setStyle("-fx-background-color: \n" +
                            "        linear-gradient(from 0% 83% to 0% 100%, #ff5b54 0%, #8B2220 100%),\n" +
                            "        radial-gradient(center 50% 50%, radius 100%, #ff5b54, #8B2220);");
                }
            });

            b.setOnMouseExited(button ->{
                if(b.toString().contains("Cancel")) {
                    b.setStyle("-fx-background-color: \n" +
                            "        linear-gradient(from 0% 83% to 0% 100%, #ff5b54 0%, #c12e2a 100%),\n" +
                            "        radial-gradient(center 50% 50%, radius 100%, #ff5b54, #c12e2a);");
                }
            });
        });
    }

    public void setDialogStyle(Dialog dialog) throws IOException {

//        GetSystemFiles.getSystemStyles(dialog, "DialogBoxes.css");

        setDialogCancelButtonStyle(dialog);
        try {
            File f = new File(AppStyles.mainPath + "\\styles\\DialogBoxes.css");
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            warningPopupBox(e.getMessage());
        } finally {
            assert fileStream != null;
            fileStream.close();
        }
    }

    public static void setComboBoxStyle(ComboBox comboBox) throws IOException {

//        GetSystemFiles.getSystemStyles(comboBox, "comboBoxes.css");

        try {
            File f = new File(mainPath + "\\styles\\comboBoxes.css");
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                comboBox.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            GeneratePopupBox.warningPopupBox(e.getMessage());
        } finally {
            assert fileStream != null;
            fileStream.close();
        }
    }

    public static void setButtonsStyle(Button button) throws IOException {

//        getSystemFiles.getSystemStyles(button, "buttons.css");


        try {
            File f = new File(mainPath + "\\styles\\buttons.css");
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                button.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            GeneratePopupBox.warningPopupBox(e.getMessage());
        } finally {
            assert fileStream != null;
            fileStream.close();
        }
    }

    public static void setMenuBarStyle(MenuBar menuBar) throws IOException {
//        getSystemFiles.getSystemStyles(menuBar, "MainStyle.css");

        try {
            File f = new File(mainPath + "\\styles\\MainStyle.css");
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                menuBar.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            GeneratePopupBox.warningPopupBox(e.getMessage());
        } finally {
            assert fileStream != null;
            fileStream.close();
        }
    }

    public void setDialogLogo(Dialog dialog, String logoName) {
        dialog.getDialogPane().setStyle(
                "-fx-graphic: url(\"file:/" + mainPath.replace("\\", "/") +  "/pic/" + logoName + "\")");
    }
}
