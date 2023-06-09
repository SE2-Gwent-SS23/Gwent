package at.moritzmusel.gwent.network.Utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {
    private Utils(){}
    public static byte[] objectToByteArray(Object object){
        byte[] byteArray = new byte[0];
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            byteArray = bos.toByteArray();
        } catch (IOException e) {
            Log.e("Utils - ", e.getMessage());
        }
        return byteArray;
    }

    public static Object byteArrayToObject(byte[] array){
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(array);
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.e("Utils - ", e.getMessage());
        }
        return object;
    }
}