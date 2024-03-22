package networkinghttp_get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingHTTP_GET {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");

        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", "Chrome");

        int responseCode = connect.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader in = new InputStreamReader(connect.getInputStream());
            BufferedReader read = new BufferedReader(in);

            StringBuffer str = new StringBuffer();
            String store = null;

            while ((store = read.readLine()) != null) {
                str.append(store);
            }
            read.close();
            System.out.println("Get Response Data: " + str);
        }

    }
}
