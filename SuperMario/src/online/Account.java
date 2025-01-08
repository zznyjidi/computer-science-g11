package online;

import java.io.IOException;
import java.net.ConnectException;
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
    private int uid;
    private String username;
    private String nickname;

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
    public String getNickname() {
        return nickname;
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
                null, null).toURL();
            HttpURLConnection connection = HttpRequest.post_form(url, Map.ofEntries(
                Map.entry("username", username),
                Map.entry("password", password)));
            String responds = HttpRequest.getRespond(connection);
            JSONObject respondJson = new JSONObject(responds);
            this.uid = respondJson.getInt("uid");
            this.nickname = respondJson.getString("nickname");
            this.authToken = "";
            this.loggedIn = true;
        } catch (MalformedURLException e) {
            // Protocol Not Supported
            e.printStackTrace();
            System.err.println("Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        } catch (URISyntaxException e) {
            // Invalid URI
            e.printStackTrace();
            System.err.println("Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        } catch (ConnectException e) {
            // Network Error
            e.printStackTrace();
            System.err.println("Server Addr: " + Settings.scoreServerAddr[0] + "://" + Settings.scoreServerAddr[1]);
            return -2;
        } catch (IOException e) {
            // Wrong Info
            return -1;
        }
        return uid;
    }

    @Override
    public String toString() {
        return "Account [uid=" + uid + ", username=" + username + ", loggedIn=" + loggedIn + "]";
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("uid", this.uid);
        json.put("nickname", this.nickname);
        return json;
    }

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
