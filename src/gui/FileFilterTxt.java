/*
 *
 * TODO lepsze zapisywanie w TXT
 */
package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class FileFilterTxt extends FileFilter {

   //akceptuje pliki tylko z roszerzeniem txts
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension =f.getName();
        int i=extension.lastIndexOf(".");
        String txt=extension.substring(i+1,extension.length());

        if (extension != null)
        {
            if (txt.equals("txt"))
            {
                    return true;
            } else
            {
                return false;
            }
        }

        return false;
    }

    //opis filtru w oknie open/save
    public String getDescription() {
        return "Text file";
    }

}