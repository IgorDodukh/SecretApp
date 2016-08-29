package FXUI;

import Settings.GetSystemFiles;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.*;

import static FXUI.GeneratePopupBox.warningPopupBox;

/**
 * Created by Igor on 8/20/2016. All rights reserved!
 */

public class AppStyles extends GetSystemFiles {
    private static InputStream fileStream;

    public static String mainPath = "C:\\appFiles";

    private void getDialogStyleFile(Dialog dialog) throws IOException {
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

    private static void getUIStyleFile(Parent element, String fileName) throws IOException {
        try {
            File f = new File(mainPath + "\\styles\\" + fileName);
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                element.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
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

    private void setDialogCancelButtonStyle(Dialog dialog) {
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
        setDialogCancelButtonStyle(dialog);
        getDialogStyleFile(dialog);
    }

    public static void setComboBoxStyle(ComboBox comboBox) throws IOException {
        getUIStyleFile(comboBox, "comboBoxes.css");
    }

    public static void setButtonsStyle(Button button) throws IOException {
        getUIStyleFile(button, "buttons.css");
    }

    public static void setMenuBarStyle(MenuBar menuBar) throws IOException {
        getUIStyleFile(menuBar, "MainStyle.css");
    }

    public void setDialogLogo(Dialog dialog, String logoName) {
        dialog.getDialogPane().setStyle(
                "-fx-graphic: url(\"file:/" + mainPath.replace("\\", "/") +  "/pic/" + logoName + "\")");
    }
}
