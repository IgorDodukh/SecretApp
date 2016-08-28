package Settings;

import FXUI.AppStyles;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.util.Arrays;

import static FXUI.GeneratePopupBox.warningPopupBox;

/**
 * Created by Ihor on 8/26/2016. All rights reserved!
 */
public class GetSystemFiles {
    private static InputStream fileStream;

    public static void getSystemStyles(ComboBox dialog, String fileName) throws IOException {
        try {
            File f = new File(AppStyles.mainPath + "\\styles\\" + fileName);
            fileStream = new FileInputStream(f.toString());

            if (fileStream != null) {
//                DialogPane dialogPane = dialog.getDialogPane();
                dialog.getScene().getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                System.out.println("--Filename found: " + f.toString());
            } else {
                throw new FileNotFoundException("CSS file '" + f.toString() + "' not found in the classpath");
            }
        } catch (Exception e){
            System.out.println("File NOT  found: " + Arrays.toString(e.getStackTrace()));
            warningPopupBox(e.getMessage());
        } finally {
            assert fileStream != null;
            fileStream.close();
        }
    }
}
