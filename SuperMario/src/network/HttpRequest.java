package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class HttpRequest {
    static final String BOUNDARY = "*****";
    static final String NEWLINE = "\r\n";
    static final String TWO_HYPHENS = "--";

    public static HttpURLConnection post(URL requestUrl, Map<String, Object> bodyValues) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        // Set Request Info
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty(
            "Content-Type", "multipart/form-data;boundary=" + HttpRequest.BOUNDARY);
        // Send Request
        // https://blog.morizyun.com/blog/android-httpurlconnection-post-multipart/index.html
        DataOutputStream request = new DataOutputStream(connection.getOutputStream());
        // Map to Request Body
        // https://www.baeldung.com/java-hashmap-different-value-types
        bodyValues.forEach((key, value) -> {
            try {
                request.writeBytes(HttpRequest.TWO_HYPHENS + HttpRequest.BOUNDARY + HttpRequest.NEWLINE);
                request.writeBytes(String.format(
                    "Content-Disposition: form-data; name=\"%s\"%s", 
                    key, HttpRequest.NEWLINE
                ));
                request.writeBytes("Content-Type: text/plain; charset=UTF-8" + HttpRequest.NEWLINE);
                request.writeBytes(HttpRequest.NEWLINE);
                request.writeBytes(value + HttpRequest.NEWLINE);
                request.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // End Request Body
        request.writeBytes(HttpRequest.TWO_HYPHENS + HttpRequest.BOUNDARY + HttpRequest.TWO_HYPHENS + HttpRequest.NEWLINE);
        request.flush();
        request.close();

        // Return Connection
        return connection;
    }

    public static String getRespond(HttpURLConnection connection) throws IOException {
        // Read Respond from connection
        InputStream responseStream = new BufferedInputStream(connection.getInputStream());
        BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = responseStreamReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        responseStreamReader.close();
        connection.disconnect();

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            String respond = getRespond(HttpRequest.post(new URI("http://127.0.0.1:8080/return/403").toURL(), Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key2", "value2")
            )));
            System.out.println(respond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
