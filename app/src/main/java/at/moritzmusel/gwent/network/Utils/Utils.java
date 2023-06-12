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
    private static final String TAG = "GameViewActivity";

    /**
     * Convert byte array to hex string
     * @param bytes toConvert
     * @return hexValue
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sbuf = new StringBuilder();
        for(int idx=0; idx < bytes.length; idx++) {
            int intVal = bytes[idx] & 0xff;
            if (intVal < 0x10) sbuf.append("0");
            sbuf.append(Integer.toHexString(intVal).toUpperCase());
        }
        return sbuf.toString();
    }

    /**
     * Get utf8 byte array.
     * @param str which to be converted
     * @return  array of NULL if error was found
     */
    public static byte[] getUTF8Bytes(String str) {
        try { return str.getBytes("UTF-8"); } catch (Exception ex) { return new byte[0]; }
    }

    /**
     * Load UTF8withBOM or any ansi text file.
     * @param filename which to be converted to string
     * @return String value of File
     * @throws IOException if error occurs
     */
    public static String loadFileAsString(String filename) throws IOException {
        final int BUFLEN=1024;
        try (
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(filename), BUFLEN);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFLEN);
                ){
            byte[] bytes = new byte[BUFLEN];
            boolean isUTF8=false;
            int read,count=0;
            while((read=is.read(bytes)) != -1) {
                if (count==0 && bytes[0]==(byte)0xEF && bytes[1]==(byte)0xBB && bytes[2]==(byte)0xBF ) {
                    isUTF8=true;
                    baos.write(bytes, 3, read-3); // drop UTF8 bom marker
                } else {
                    baos.write(bytes, 0, read);
                }
                count+=read;
            }
            return isUTF8 ? new String(baos.toByteArray(), "UTF-8") : new String(baos.toByteArray());
        }
    }

    public static byte[] objectToByteArray(Object object){
        byte[] byteArray = new byte[0];
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            byteArray = bos.toByteArray();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return byteArray;
    }

    public static Object byteArrayToObject(byte[] array){
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(array);
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = (Object) ois.readObject();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return object;
    }
}