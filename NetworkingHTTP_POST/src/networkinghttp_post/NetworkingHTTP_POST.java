package networkinghttp_post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingHTTP_POST {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/");

        HttpURLConnection connect = (HttpURLConnection) url.openConnection();

        connect.setRequestMethod("POST");

        connect.setDoOutput(true);

        OutputStream outputStr = connect.getOutputStream();

        String postMessage = "Hello World, GUB Dept. of CSE";

        outputStr.write(postMessage.getBytes());

        int responseCode = connect.getResponseCode();

        System.out.println("Response is: " + connect.HTTP_CREATED);

        if (responseCode == connect.HTTP_CREATED) {
            System.out.println("Response code: " + responseCode);
            System.out.println("Response Message: " + connect.getResponseMessage());
        } else {
            System.out.println("Nothing");
        }

        InputStreamReader in = new InputStreamReader(connect.getInputStream());

        BufferedReader buffer = new BufferedReader(in);
        StringBuffer str = new StringBuffer();

        String store = null;

        while ((store = buffer.readLine()) != null) {
            str.append(store);
            str.append(System.lineSeparator());
        }

        buffer.close();

        System.out.println("Message from server: " + str);
    }

}
