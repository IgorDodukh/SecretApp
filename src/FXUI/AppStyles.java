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

    public static String resourcesPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources" + File.separator;
//    public static String resourcesPath = "C:\\Users\\Ihor\\IdeaProjects\\SecretApp\\src\\resources\\";
    public static String picturesResourcePath = resourcesPath + "pic" + File.separator;
    public static String driversResourcePath = resourcesPath + "drivers" + File.separator;
    public static String propertiesResourcePath = resourcesPath + "properties" + File.separator;
    public static String stylesResourcePath = resourcesPath + "styles" + File.separator;
    private static String dialogBoxStyle = stylesResourcePath + "dialogBoxes.css";

    private void getDialogStyleFile(Dialog dialog) throws IOException {
        System.out.println(resourcesPath);
        try {
            File f = new File(dialogBoxStyle);
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
            File f = new File(stylesResourcePath + fileName);
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

    void setDialogStyle(Dialog dialog) throws IOException {
        setDialogCancelButtonStyle(dialog);
        getDialogStyleFile(dialog);
    }

    static void setComboBoxStyle(ComboBox comboBox) throws IOException {
        getUIStyleFile(comboBox, "comboBoxes.css");
    }

    static void setButtonsStyle(Button button) throws IOException {
        getUIStyleFile(button, "buttons.css");
    }

    static void setMenuBarStyle(MenuBar menuBar) throws IOException {
        getUIStyleFile(menuBar, "mainStyle.css");
    }

    void setDialogLogo(Dialog dialog, String logoName) {
        dialog.getDialogPane().setStyle(
                "-fx-graphic: url(\"file:/" + picturesResourcePath.replace("\\", "/") + logoName + "\")");
    }
}
