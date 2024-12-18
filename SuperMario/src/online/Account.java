package online;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import global.Settings;
import network.HttpRequest;

public class Account {
    private int uid;
    private String username;
    private String authToken;

    private boolean loggedIn;

    public Account(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }
    public String getUsername() {
        return username;
    }
    public String getAuthToken() {
        return authToken;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int login(String password) {
        try {
            // Connect to Server
            URL url = new URI(
                Settings.scoreServerAddr[0], 
                Settings.scoreServerAddr[1],
                Settings.scoreServerFeature.get("user-login"),
                null, null
            ).toURL();
            HttpURLConnection connection = HttpRequest.post(url, Map.ofEntries(
                Map.entry("username", username),
                Map.entry("password", password)
            ));
        } catch (MalformedURLException e) {
            // Protocol Not Supported
            return -2;
        } catch (URISyntaxException e) {
            // Invalid URI
            return -2;
        } catch (IOException e) {
            // Wrong Info
            return -1;
        }
        return uid;
    }
}
