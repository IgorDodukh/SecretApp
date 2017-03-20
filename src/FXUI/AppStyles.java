package FXUI;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static FXUI.DialogBoxGenerator.warningPopupBox;

/**
 * Created by Igor on 8/20/2016. All rights reserved!
 */

public class AppStyles {
    private static InputStream fileStream;



    public static String resourcesPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "resources" + File.separator;
    public static String picturesResourcePath = resourcesPath + "pic" + File.separator;
    public static String driversResourcePath = resourcesPath + "drivers" + File.separator;
    public static String propertiesResourcePath = resourcesPath + "properties" + File.separator;
    public static String stylesResourcePath = resourcesPath + Controller.getStylesFolderName() + File.separator;

    public static String jsonPath = resourcesPath + "json" + File.separator;

    private void getDialogStyleFile(Dialog dialog) throws IOException {
        try {
            File f = new File(AppStyles.resourcesPath + Controller.getStylesFolderName() + File.separator + "dialogBoxes.css");
            fileStream = new FileInputStream(f.toString());
            if (fileStream != null) {
                DialogPane dialogPane = dialog.getDialogPane();
                URL url = f.toURI().toURL();
                dialogPane.getStylesheets().add(url.toExternalForm());
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
            File f = new File(resourcesPath + Controller.getStylesFolderName() + File.separator + fileName);
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
                URL url = f.toURI().toURL();
                element.getStylesheets().add(url.toExternalForm());
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            DialogBoxGenerator.warningPopupBox(e.getMessage());
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

    static void setMainViewStyle(Pane pane) throws IOException {
        getUIStyleFile(pane, "mainStyle.css");
    }

    static void setMainBackgroundStyle(AnchorPane anchorPane) throws IOException {
        getUIStyleFile(anchorPane, "mainStyle.css");
    }

    static void setToggleButtonStyle(ToggleButton toggleButton) throws IOException {
        getUIStyleFile(toggleButton, "buttons.css");
    }

    void setDialogLogo(Dialog dialog, String logoName) throws MalformedURLException {
        File f = new File(picturesResourcePath + logoName);
        URL url = f.toURI().toURL();
        dialog.getDialogPane().setStyle(
                "-fx-graphic: url(" + url.toExternalForm() + ")");
    }
}
