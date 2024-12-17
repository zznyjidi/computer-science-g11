package online;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import global.Settings;

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
            URL url = new URI(
                Settings.scoreServerAddr[0], 
                Settings.scoreServerAddr[1],
                Settings.scoreServerFeature.get("login"),
                null, null
            ).toURL();
        } catch (MalformedURLException e) {
            // Protocol Not Supported
            return -2;
        } catch (URISyntaxException e) {
            // Invalid URI
            return -2;
        }
        return uid;
    }
}
