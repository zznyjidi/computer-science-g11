package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;

public class HttpRequest {
    static final String BOUNDARY = "*****";
    static final String NEWLINE = "\r\n";
    static final String TWO_HYPHENS = "--";

    public static HttpURLConnection get(URL requestUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        // Set Request Info
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }

    public static HttpURLConnection post(URL requestUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        // Set Request Info
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        return connection;
    }

    public static HttpURLConnection post_json (URL requestUrl, JSONObject json) throws IOException {
        HttpURLConnection connection = post(requestUrl);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        DataOutputStream request = new DataOutputStream(connection.getOutputStream());
        request.writeBytes(json.toString());
        request.flush();
        request.close();
        return connection;
    }

    public static HttpURLConnection post_form(URL requestUrl, Map<String, Object> bodyValues) throws IOException {
        HttpURLConnection connection = post(requestUrl);
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
        InputStream responseStream;
        // Select Reader base on Status
        // https://stackoverflow.com/questions/25011927/how-to-get-response-body-using-httpurlconnection-when-code-other-than-2xx-is-re
        if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
            responseStream = new BufferedInputStream(connection.getInputStream());
        } else {
            responseStream = new BufferedInputStream(connection.getErrorStream());
        }
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
}
