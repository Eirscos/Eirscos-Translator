package com.georlegacy.crypto.eirscos.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class APIUtil {

    public static String getKey(String key) {
        InputStream inputStream = null;
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.615283.net/api/ternary/crypto/eirscos/key.php?secret=" + key).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                buffer.append(nextLine);
            }
            return buffer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
