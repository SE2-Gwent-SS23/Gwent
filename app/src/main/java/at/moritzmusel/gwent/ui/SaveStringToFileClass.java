package at.moritzmusel.gwent.ui;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SaveStringToFileClass {
    public static void saveData(Context context, String filename, String data) throws IOException {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }
    }

    public static String getSavedData(Context context, String filename) throws IOException {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(filename);
            Scanner scanner = new Scanner(fileInputStream);
            scanner.useDelimiter("\\A");

            String streamString = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            return streamString;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        return null;
    }
}
