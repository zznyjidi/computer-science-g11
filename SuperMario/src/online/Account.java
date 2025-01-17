package online;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;

import global.Settings;
import network.HttpRequest;

public class Account {

    // Info
    private int uid;
    private String username;
    private String nickname;

    // Login Status
    private String authToken;
    private boolean loggedIn;

    public Account(String username) {
        this.username = username;
    }

    // Getters
    public int getUid() {
        return uid;
    }
    public String getUsername() {
        return username;
    }
    public String getNickname() {
        return nickname;
    }
    public String getAuthToken() {
        return authToken;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Login to Server
     * @param password account's password
     * @return account uid (-1 for Wrong Info, -2 for Error Communicating with the Server)
     */
    public int login(String password) {
        HttpURLConnection connection;
        try {
            // Connect to Server
            URL url = new URI(
                Settings.scoreServerAddr[0],
                Settings.scoreServerAddr[1],
                Settings.scoreServerFeature.get("user-login"),
                null, null).toURL();
            connection = HttpRequest.post_form(url, Map.ofEntries(
                Map.entry("username", username),
                Map.entry("password", password)));
        } catch (MalformedURLException | URISyntaxException e) {
            // Protocol Not Supported / Invalid URL
            e.printStackTrace();
            System.err.println("Wrong Server Config! Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        } catch (IOException e) {
            // Network Error
            e.printStackTrace();
            System.err.println("Failed to Connect to Server! Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        }
        try {
            String responds = HttpRequest.getRespond(connection);
            JSONObject respondJson = new JSONObject(responds);
            if (connection.getResponseCode() == 200) {
                this.uid = respondJson.getInt("uid");
                this.nickname = respondJson.getString("nickname");
                this.authToken = "";
                this.loggedIn = true;
            } else {
                // Server Error / Wrong Info
                System.err.println("Failed to Login: " + respondJson.getString("message"));
                return -1;
            }
        } catch (IOException e) {
            // Network Error
            e.printStackTrace();
            System.err.println("Failed to Connect to Server! Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        }
        return uid;
    }

    @Override
    public String toString() {
        return "Account [uid=" + uid + ", username=" + username + ", loggedIn=" + loggedIn + "]";
    }

    /**
     * Get Account Info in json format
     * @return account info 
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("uid", this.uid);
        if (nickname != null && !this.nickname.equals("")) {
            json.put("nickname", this.nickname);
        } else {
            json.put("nickname", this.username);
        }
        return json;
    }

    /**
     * Submit a replay to server
     * @param replay replay json file
     * @return replay uid (-1 for failed submission)
     */
    public int submitScore(JSONObject replay) {
        if (!loggedIn) {
            return -1;
        }
        try {
            URL url = new URI(
                Settings.scoreServerAddr[0],
                Settings.scoreServerAddr[1],
                Settings.scoreServerFeature.get("score-submit"),
                "uid=" + uid, null).toURL();
            HttpURLConnection connection = HttpRequest.post_json(url, replay);
            JSONObject respond = new JSONObject(HttpRequest.getRespond(connection));
            replay.getJSONObject("info").put("replay_uid", respond.getInt("replay_uid"));
            return respond.getInt("replay_uid");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
        }
        return -1;
    }
}
